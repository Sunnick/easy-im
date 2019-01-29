package com.sunnick.easyim.handler;

import com.sunnick.easyim.packet.GroupMessageRequestPacket;
import com.sunnick.easyim.packet.GroupMessageResponsePacket;
import com.sunnick.easyim.util.GroupUtil;
import com.sunnick.easyim.util.SessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.group.ChannelGroup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Sunnick on 2019/1/22/022.
 */
public class GroupMessageRequestHandler extends EasyImChannelInBoundHandler<GroupMessageRequestPacket> {

    private static GroupMessageRequestHandler instance = new GroupMessageRequestHandler();
    private GroupMessageRequestHandler(){}
    public static GroupMessageRequestHandler getInstance(){
        return instance;
    }

    private static Logger logger = LoggerFactory.getLogger(GroupMessageRequestHandler.class);


    @Override
    protected void handleResponse(ChannelHandlerContext ctx, GroupMessageRequestPacket packet) {
        ChannelGroup channelGroup = GroupUtil.getChannelGroup(packet.getGroupId());
        if(channelGroup == null){
            logger.info("群聊不存在!");
            return;
        }
        GroupMessageResponsePacket response = new GroupMessageResponsePacket();
        response.setGroupMsg(packet.getGroupMsg());
        response.setGroupId(packet.getGroupId());
        response.setFromUserName(SessionUtil.getSessionByChannel(ctx.channel()).getUserName());
        for(Channel channel : channelGroup){
            channel.writeAndFlush(response);
        }
    }
}
