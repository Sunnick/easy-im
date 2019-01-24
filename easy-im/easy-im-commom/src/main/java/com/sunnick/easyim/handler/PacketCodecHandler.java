package com.sunnick.easyim.handler;

import com.sunnick.easyim.protocol.Packet;
import com.sunnick.easyim.protocol.PacketCodeC;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageCodec;

import java.util.List;

/**
 * Created by Sunnick on 2019/1/18/018.
 */
@ChannelHandler.Sharable
public class PacketCodecHandler extends MessageToMessageCodec<ByteBuf,Packet> {

    private PacketCodecHandler(){}

    private static PacketCodecHandler instance = new PacketCodecHandler();

    public static PacketCodecHandler getInstance(){
        return instance;
    }

    protected void encode(ChannelHandlerContext ctx, Packet packet, List<Object> list) throws Exception {
        ByteBuf byteBuf = ctx.channel().alloc().ioBuffer();
        PacketCodeC.getInstance().encode(byteBuf,packet);
        list.add(byteBuf);

    }

    protected void decode(ChannelHandlerContext ctx, ByteBuf buf, List<Object> list) throws Exception {
        list.add(PacketCodeC.getInstance().decode(buf));
    }
}
