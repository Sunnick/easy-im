package com.sunnick.easyim.util;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Sunnick on 2019/1/20/020.
 */
public class GroupUtil {

    private static Logger logger = LoggerFactory.getLogger(GroupUtil.class);

    /**
     * 服务端用来保存群聊关系
     */
    private static final Map<String,ChannelGroup> groupMap = new ConcurrentHashMap<>();

    /**
     * @param ctx
     * @param users
     * @return groupId
     */
    public static String createGroup(ChannelHandlerContext ctx, List<String> users){
        String groupId = null;
        ChannelGroup group = new DefaultChannelGroup(ctx.executor());
        for(String userId : users){
            Channel channel = SessionUtil.getChannelByUserId(userId);
            if(channel == null){
                logger.info("该userid并没有登录，无法发起群聊：{}",userId);
                continue;
            }
            group.add(channel);
        }
        if (group.isEmpty()){
            return groupId;
        }
        // TODO groupId生成算法需要改进
        groupId = UUID.randomUUID().toString().split("-")[0];
        groupMap.putIfAbsent(groupId,group);
        return groupId;
    }


    public static ChannelGroup getChannelGroup(String groupId) {
        return groupMap.get(groupId);
    }
}
