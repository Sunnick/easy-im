package com.sunnick.easyim.handler;

import com.sunnick.easyim.protocol.Packet;
import com.sunnick.easyim.protocol.PacketCodeC;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by Sunnick on 2019/1/13/013.
 */
public class PacketDecoder extends ByteToMessageDecoder {

    private static Logger logger = LoggerFactory.getLogger(PacketDecoder.class);

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List list) throws Exception {
        Packet packet = PacketCodeC.getInstance().decode(byteBuf);
        list.add(packet);
    }
}
