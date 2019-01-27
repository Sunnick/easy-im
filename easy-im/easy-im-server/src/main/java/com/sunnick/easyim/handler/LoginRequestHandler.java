package com.sunnick.easyim.handler;

import com.sunnick.easyim.packet.LoginRequestPacket;
import com.sunnick.easyim.packet.LoginResponsePacket;
import com.sunnick.easyim.util.LoginUtil;
import com.sunnick.easyim.util.Session;
import com.sunnick.easyim.util.SessionUtil;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Sunnick on 2019/1/13/013.
 */
@ChannelHandler.Sharable
public class LoginRequestHandler extends EasyImChannelInBoundHandler<LoginRequestPacket> {

    private static Logger logger = LoggerFactory.getLogger(LoginRequestHandler.class);

    public static LoginRequestHandler getInstance(){
        return instance;
    }

    private LoginRequestHandler(){}

    private static LoginRequestHandler instance = new LoginRequestHandler();

    /**
     * 处理登录请求
     */
    private LoginResponsePacket login(ChannelHandlerContext ctx,LoginRequestPacket packet) {
        LoginResponsePacket response = new LoginResponsePacket();
        if(valid(packet)){
            //login success
            response.setCode("0000");
            response.setMsg("登陆成功！");
            logger.info("[{}],登录成功！,id为{}",packet.getUserName(),packet.getUserId());
            LoginUtil.markAsLogin(ctx.channel());
            SessionUtil.bindSession(new Session(packet.getUserId(),packet.getUserName()),ctx.channel());
        }else{
            //login failed
            response.setCode("1001");
            response.setMsg("登陆失败！");
        }
        return response;
    }


    /**
     *  校验登录
     *
     */
    private boolean valid(LoginRequestPacket loginPacket) {
        return true;
    }

    @Override
    protected void handleResponse(ChannelHandlerContext ctx, LoginRequestPacket packet) {
        LoginResponsePacket response = login(ctx,packet);
        ctx.channel().writeAndFlush(response);
    }


}
