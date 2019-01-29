package com.sunnick.easyim.handler;

import com.sunnick.easyim.util.Session;
import com.sunnick.easyim.util.LoginUtil;
import com.sunnick.easyim.util.SessionUtil;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Sunnick on 2019/1/14/014.
 *
 * 登录校验
 */
@ChannelHandler.Sharable
public class AuthHandler extends ChannelInboundHandlerAdapter {

    private static Logger logger = LoggerFactory.getLogger(AuthHandler.class);

    public static AuthHandler getInstance(){
        return instance;
    }

    private AuthHandler(){}

    private static AuthHandler instance = new AuthHandler();

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //如果已经登录，就可以继续走，没登录直接断开连接
        if(SessionUtil.hasLogin(ctx.channel())){
            //如果登录了，只要连接没断，就无需再校验
            ctx.channel().pipeline().remove(this);
            super.channelRead(ctx, msg);
        }else{
            ctx.channel().close();
        }
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        if(LoginUtil.hasLogin(ctx.channel())){
            logger.info("登录验证已通过，后续无需再验证!");
        }else{
            logger.info("登录验证未通过，连接断开...");
        }
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Session session = SessionUtil.getSessionByChannel(ctx.channel());
        if (session != null ) {
            SessionUtil.unBindSession(session,ctx.channel());
            logger.info("{}下线啦!",session.getUserId());
        }
       ctx.channel().close();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        //异常时断开连接
        Session session = SessionUtil.getSessionByChannel(ctx.channel());
        if (session != null ) {
            SessionUtil.unBindSession(session,ctx.channel());
            logger.info("{}下线啦!",session.getUserId());
        }
        ctx.channel().close();
    }
}
