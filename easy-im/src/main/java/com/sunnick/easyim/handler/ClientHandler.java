package com.sunnick.easyim.handler;

import com.alibaba.fastjson.JSON;
import com.sunnick.easyim.packet.LoginRequestPacket;
import com.sunnick.easyim.protocol.PacketCodeC;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

/**
 * Created by Sunnick on 2019/1/13/013.
 */
public class ClientHandler extends ChannelInboundHandlerAdapter {

    private static Logger logger = LoggerFactory.getLogger(ClientHandler.class);

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        //发起登录
        LoginRequestPacket packet = getLoginRequestPacket();
        logger.info("ready to login!-->" + JSON.toJSONString(packet));
        ByteBuf buf = PacketCodeC.getInstance().encode(ctx.alloc(),packet);
        ctx.channel().writeAndFlush(buf);
    }


    public LoginRequestPacket getLoginRequestPacket() {
        LoginRequestPacket packet = new LoginRequestPacket();
        packet.setUserId(UUID.randomUUID().toString());
        packet.setUserName("Sunnick");
        packet.setPassword("pwd");
        return packet;
    }
}
