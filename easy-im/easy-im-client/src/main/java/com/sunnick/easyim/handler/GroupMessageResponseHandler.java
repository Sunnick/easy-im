package com.sunnick.easyim.handler;

import com.sunnick.easyim.packet.GroupMessageResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Sunnick on 2019/1/22/022.
 */
public class GroupMessageResponseHandler extends EasyImChannelInBoundHandler<GroupMessageResponsePacket> {

    private static GroupMessageResponseHandler instance = new GroupMessageResponseHandler();
    private GroupMessageResponseHandler(){}
    public static GroupMessageResponseHandler getInstance(){
        return instance;
    }

    private static Logger logger = LoggerFactory.getLogger(GroupMessageResponseHandler.class);

    @Override
    protected void handleResponse(ChannelHandlerContext ctx, GroupMessageResponsePacket packet) {
        logger.info("收到群聊{}的消息：{}-->{}",packet.getGroupId(),packet.getFromUserName(),packet.getGroupMsg());
    }
}
