package com.sunnick.easyim.util;

import com.sunnick.easyim.protocol.Packet;
import com.sunnick.easyim.protocol.PacketCodeC;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;

/**
 * Created by Sunnick on 2019/1/18/018.
 */
public class ChannelUtil {
    public static void writeAndFlush(Channel channel, Packet packet){
        ByteBuf buf = PacketCodeC.getInstance().encode(packet);
        channel.writeAndFlush(buf);
    }
    public static void writeAndFlushWithCtx(ChannelHandlerContext ctx, Packet packet){
        ByteBuf buf = PacketCodeC.getInstance().encode(packet);
        ctx.writeAndFlush(buf);
    }
}
