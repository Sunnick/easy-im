package com.sunnick.easyim.callback;

import com.sunnick.easyim.protocol.Packet;
import io.netty.channel.ChannelHandlerContext;

/**
 * Created by Sunnick on 2019/1/27/027.
 * 事件回调
 */
public interface EasyImCallback<T> {
    void callback(ChannelHandlerContext ctx, T t);
}
