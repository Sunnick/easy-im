package com.sunnick.easyim.handler;

import com.sunnick.easyim.packet.MessageResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Sunnick on 2019/1/13/013.
 * 处理消息响应
 */
public class MessageResponseHandler extends SimpleChannelInboundHandler<MessageResponsePacket> {

    private static Logger logger = LoggerFactory.getLogger(MessageResponseHandler.class);

    /**
     * 处理消息请求
     */
    private void handlMessage(MessageResponsePacket response) {
        logger.info("收到{}的消息：{}" ,response.getFromUserId(),response.getMessage());
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, MessageResponsePacket packet) throws Exception {
        handlMessage((MessageResponsePacket)packet);
    }
}
