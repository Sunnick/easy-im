package com.sunnick.easyim.handler;

import com.sunnick.easyim.packet.HeartBeatPacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.IdleStateHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Sunnick on 2019/1/27/027.
 */
public class ClientIdleHandler extends IdleStateHandler {

    private static Logger logger = LoggerFactory.getLogger(ClientIdleHandler.class);

    private static final int HEART_BEAT_TIME = 50;

    public ClientIdleHandler() {
        super(0, 0, HEART_BEAT_TIME);
    }

    @Override
    protected void channelIdle(ChannelHandlerContext ctx, IdleStateEvent evt) throws Exception {
        logger.info("发送心跳....");
        ctx.writeAndFlush(new HeartBeatPacket());
    }


}
