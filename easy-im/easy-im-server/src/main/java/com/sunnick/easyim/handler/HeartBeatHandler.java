package com.sunnick.easyim.handler;

import com.alibaba.fastjson.JSON;
import com.sunnick.easyim.packet.HeartBeatPacket;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Sunnick on 2019/1/27/027.
 */
@ChannelHandler.Sharable
public class HeartBeatHandler extends SimpleChannelInboundHandler<HeartBeatPacket> {

    private static Logger logger = LoggerFactory.getLogger(HeartBeatHandler.class);

    private static HeartBeatHandler instance = new HeartBeatHandler();
    private HeartBeatHandler(){}

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HeartBeatPacket heartBeatPacket) throws Exception {
        logger.info("收到心跳包：{}", JSON.toJSONString(heartBeatPacket));
        ctx.writeAndFlush(heartBeatPacket);
    }

    public static HeartBeatHandler getInstance() {
        return instance;
    }
}
