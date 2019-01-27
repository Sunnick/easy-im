package com.sunnick.easyim.handler;

import com.sunnick.easyim.packet.DefaultErrorPacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Sunnick on 2019/1/22/022.
 */
public class DefaultErrorHandler extends EasyImChannelInBoundHandler<DefaultErrorPacket> {

    private static DefaultErrorHandler instance = new DefaultErrorHandler();
    public static DefaultErrorHandler getInstance(){
        return instance;
    }
    private DefaultErrorHandler(){}

    private static Logger logger = LoggerFactory.getLogger(DefaultErrorHandler.class);

    @Override
    protected void handleResponse(ChannelHandlerContext ctx, DefaultErrorPacket packet) {
        logger.info("{}---{}",packet.getCode(),packet.getMsg());
    }
}
