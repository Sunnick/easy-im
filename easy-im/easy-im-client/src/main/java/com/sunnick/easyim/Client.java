package com.sunnick.easyim;

import com.sunnick.easyim.handler.*;
import com.sunnick.easyim.util.Scan;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.internal.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Sunnick on 2019/1/12/012.
 */
public class Client {

    private static Logger logger = LoggerFactory.getLogger(Client.class);
    private static String userid = "155";
    private static String username = "zhangsan";
    private static String host = "127.0.0.1";
    private static int port = 8888;


    public static void main(String[] strings){
        userid = StringUtil.isNullOrEmpty(System.getProperty("userid")) ? userid : System.getProperty("userid");
        username = StringUtil.isNullOrEmpty(System.getProperty("username")) ? username : System.getProperty("username");
        host = StringUtil.isNullOrEmpty(System.getProperty("host")) ? host : System.getProperty("host");
        port = StringUtil.isNullOrEmpty(System.getProperty("port")) ? port : Integer.parseInt(System.getProperty("port"));
        start();
    }

    public static void start() {
        NioEventLoopGroup worker = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(worker).channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel channel) throws Exception {
                        channel.pipeline().addLast(new ServerIdleHandler());
                        channel.pipeline().addLast(new MagicNumValidator());
                        channel.pipeline().addLast(PacketCodecHandler.getInstance());
                        channel.pipeline().addLast(new ClientIdleHandler());
                        channel.pipeline().addLast(new LoginHandler(userid,username));
                        channel.pipeline().addLast(LoginResponseHandler.getInstance());
                        channel.pipeline().addLast(ClientHandler.getInstance());
                    }
                });
        ChannelFuture future = bootstrap.connect(host,port).addListener(new ChannelFutureListener() {
            public void operationComplete(ChannelFuture channelFuture) throws Exception {
                if(channelFuture.isSuccess()){
                    logger.info("connect to server success!");
                    //启动控制台输入
                    startScanConsole(channelFuture.channel());
                }else{
                    logger.info("failed to connect the server! ");
                    System.exit(0);
                }
            }
        });
        try {
            future.channel().closeFuture().sync();
            logger.info("与服务端断开连接！");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 启动控制台输入
     */
    private static void startScanConsole(Channel channel){
        Scan scan = new Scan(channel);
        Thread thread = new Thread(scan);
        thread.setName("scan-thread");
        thread.start();
    }
}
