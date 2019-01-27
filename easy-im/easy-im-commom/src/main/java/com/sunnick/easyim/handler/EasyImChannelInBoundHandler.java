package com.sunnick.easyim.handler;

import com.sunnick.easyim.callback.EasyImCallback;
import com.sunnick.easyim.protocol.Packet;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Sunnick on 2019/1/27/027.
 * 业务handler的基类。
 */
public abstract class EasyImChannelInBoundHandler<T> extends SimpleChannelInboundHandler<T> {
    /**
     * 用来存放回调列表
     */
    protected static List<EasyImCallback> callbackList = new ArrayList<EasyImCallback>();

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, T t) throws Exception {
        handleResponse(ctx, t);
        dealCallbacks(ctx, t);
    }

    protected  void dealCallbacks(ChannelHandlerContext ctx, T t){
        for (EasyImCallback callback : callbackList){
            callback.callback(ctx,t);
        }
    }

    public static void addCalbBack(EasyImCallback callback){
        callbackList.add(callback);
    }
    public static void removeCallback(EasyImCallback callback){
        callbackList.remove(callback);
    }

    protected abstract void handleResponse(ChannelHandlerContext ctx, T t);
}
