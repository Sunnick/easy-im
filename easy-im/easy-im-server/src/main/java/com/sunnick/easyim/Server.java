package com.sunnick.easyim;

import com.sunnick.easyim.handler.*;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.internal.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Sunnick on 2019/1/12/012.
 */

@ChannelHandler.Sharable
public class Server {

    private static Logger logger = LoggerFactory.getLogger(Server.class);

    private static int port = 8888;

    public static void main(String[] strings){


        port = StringUtil.isNullOrEmpty(System.getProperty("port")) ? port : Integer.parseInt(System.getProperty("port"));

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
        bootstrap.bind(port);
        logger.info("server started! using port " + port);
    }
}
