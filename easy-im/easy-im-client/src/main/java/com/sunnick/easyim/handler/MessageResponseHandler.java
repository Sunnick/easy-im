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
public class MessageResponseHandler extends EasyImChannelInBoundHandler<MessageResponsePacket> {

    private static Logger logger = LoggerFactory.getLogger(MessageResponseHandler.class);

    public static MessageResponseHandler getInstance(){
        return instance;
    }

    private MessageResponseHandler(){}

    private static MessageResponseHandler instance = new MessageResponseHandler();

    /**
     * 处理消息请求
     */
    private void handlMessage(MessageResponsePacket response) {
        logger.info("收到{}的消息：{}" ,response.getFromUserId(),response.getMessage());
    }

    @Override
    protected void handleResponse(ChannelHandlerContext ctx, MessageResponsePacket messageResponsePacket) {
        handlMessage(messageResponsePacket);
    }
}
