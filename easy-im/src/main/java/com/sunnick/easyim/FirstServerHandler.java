package com.sunnick.easyim;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.Charset;
import java.util.Date;

/**
 * Created by Sunnick on 2019/1/12/012.
 */
public class FirstServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf) msg;
        System.out.println(new Date() + ":reveive data->" + buf.toString(Charset.forName("utf-8")));
        ctx.writeAndFlush(getByteBuf(ctx));
    }

    private ByteBuf getByteBuf(ChannelHandlerContext ctx){
        ByteBuf buf = ctx.alloc().buffer();
        buf.writeBytes("welcome to easyim with me!".getBytes(Charset.forName("utf-8")));
        return buf;
    }
}
