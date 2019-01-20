package com.sunnick.easyim.handler;

import com.alibaba.fastjson.JSON;
import com.sunnick.easyim.packet.LoginRequestPacket;
import com.sunnick.easyim.packet.LoginResponsePacket;
import com.sunnick.easyim.protocol.Packet;
import com.sunnick.easyim.util.LoginUtil;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Sunnick on 2019/1/13/013.
 * 处理登录响应
 */
@ChannelHandler.Sharable
public class LoginResponseHandler extends SimpleChannelInboundHandler<LoginResponsePacket> {

    private static Logger logger = LoggerFactory.getLogger(LoginResponseHandler.class);

    public static LoginResponseHandler getInstance(){
        return instance;
    }

    private LoginResponseHandler(){}

    private static LoginResponseHandler instance = new LoginResponseHandler();

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        //发起登录
        LoginRequestPacket packet = getLoginRequestPacket();
        logger.info("ready to login!-->" + JSON.toJSONString(packet));
        ctx.channel().writeAndFlush(packet);
    }

    public LoginRequestPacket getLoginRequestPacket() {
        LoginRequestPacket packet = new LoginRequestPacket();
//        packet.setUserId("119");
//        packet.setUserName("喷水车");

        packet.setUserId("120");
        packet.setUserName("红十字");
//
//        packet.setUserId("110");
//        packet.setUserName("黑猫警长");
        packet.setPassword("pwd");
        return packet;
    }

    /**
     * 登录成功响应
     */
    private void loginResponse(ChannelHandlerContext ctx,Packet packet) {
        LoginResponsePacket response = (LoginResponsePacket) packet;
        if (response.success()){
            logger.info("login success!");
            LoginUtil.markAsLogin(ctx.channel());
        }else {
            logger.info("login failed!-->" + JSON.toJSONString(response));
        }
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, LoginResponsePacket packet) throws Exception {
        loginResponse(channelHandlerContext,packet);
    }
}
