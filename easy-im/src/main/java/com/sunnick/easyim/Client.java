package com.sunnick.easyim;

import com.sunnick.easyim.Command.CommandManager;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Sunnick on 2019/1/12/012.
 */
public class Client {

    private static Logger logger = LoggerFactory.getLogger(Client.class);


    public static void main(String[] strings){
        NioEventLoopGroup worker = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(worker).channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel channel) throws Exception {
                        channel.pipeline().addLast(new MagicNumValidator());
                        channel.pipeline().addLast(PacketCodecHandler.getInstance());
                        channel.pipeline().addLast(LoginResponseHandler.getInstance());
                        channel.pipeline().addLast(ClientHandler.getInstance());

//                        channel.pipeline().addLast(MessageResponseHandler.getInstance());
//                        channel.pipeline().addLast(CreateGroupResponseHandler.getInstance());
                    }
                });
        ChannelFuture future = bootstrap.connect("127.0.0.1",8888).addListener(new ChannelFutureListener() {
            public void operationComplete(ChannelFuture channelFuture) throws Exception {
                if(channelFuture.isSuccess()){
                    logger.info("connect to server success!");
                    //启动控制台输入
                    startScanConsole(channelFuture.channel());
                }else{
                    logger.info("failed to connect the server! ");
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
