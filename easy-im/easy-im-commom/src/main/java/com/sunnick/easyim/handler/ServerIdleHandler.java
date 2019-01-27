package com.sunnick.easyim.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.IdleStateHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Sunnick on 2019/1/27/027.
 * 心跳检测，150s没收到心跳包的话，断开连接
 */
public class ServerIdleHandler extends IdleStateHandler {
    private static Logger logger = LoggerFactory.getLogger(ServerIdleHandler.class);

    private static int HERT_BEAT_TIME = 150;

    public ServerIdleHandler() {
        super(0, 0, HERT_BEAT_TIME);
    }

    @Override
    protected void channelIdle(ChannelHandlerContext ctx, IdleStateEvent evt) throws Exception {
        logger.info("{}内没有收到心跳，关闭连接...",HERT_BEAT_TIME);
        ctx.channel().close();
    }
}
