package com.sunnick.easyim;

import com.sunnick.easyim.handler.*;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

/**
 * Created by Sunnick on 2019/1/12/012.
 */

@ChannelHandler.Sharable
public class Server {

    private static Logger logger = LoggerFactory.getLogger(Server.class);

    private static int port = 8888;

    public static void main(String[] strings){
        port = StringUtils.isEmpty(System.getProperty("port")) ? port : Integer.parseInt(System.getProperty("port"));
        start();
    }

    public static void start() {
        NioEventLoopGroup boss = new NioEventLoopGroup();
        NioEventLoopGroup worker = new NioEventLoopGroup();
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(boss,worker).channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel nioSocketChannel) throws Exception {
                        nioSocketChannel.pipeline().addLast(new ServerIdleHandler());
                        nioSocketChannel.pipeline().addLast(new MagicNumValidator());
                        nioSocketChannel.pipeline().addLast(PacketCodecHandler.getInstance());
                        nioSocketChannel.pipeline().addLast(LoginRequestHandler.getInstance());
                        nioSocketChannel.pipeline().addLast(HeartBeatHandler.getInstance());
                        nioSocketChannel.pipeline().addLast(AuthHandler.getInstance());
                        nioSocketChannel.pipeline().addLast(ServerHandler.getInstance());
                    }
                });

        ChannelFuture future = bootstrap.bind(port);
        future.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture channelFuture) throws Exception {
                if (channelFuture.isSuccess()){
                    logger.info("server started! using port {} " , port);
                }else {
                    logger.info("server start failed! using port {} " , port);
                    channelFuture.cause().printStackTrace();
                    System.exit(0);
                }
            }
        });
    }
}
