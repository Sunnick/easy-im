package com.sunnick.easyim.handler;

import com.alibaba.fastjson.JSON;
import com.sunnick.easyim.packet.LoginRequestPacket;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Sunnick on 2019/1/24/024.
 */
public class LoginHandler extends ChannelInboundHandlerAdapter {
    private static Logger logger = LoggerFactory.getLogger(LoginHandler.class);

    private String userId;
    private String userName;

    public LoginHandler(String userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        //发起登录
        LoginRequestPacket packet = getLoginRequestPacket();
        logger.info("ready to login!-->" + JSON.toJSONString(packet));
        ctx.channel().writeAndFlush(packet);
    }

    public LoginRequestPacket getLoginRequestPacket() {
        LoginRequestPacket packet = new LoginRequestPacket();
        packet.setUserId(this.userId);
        packet.setUserName(this.userName);
        return packet;
    }
}
