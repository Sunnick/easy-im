package com.sunnick.easyim.util;

import com.sunnick.easyim.packet.LoginRequestPacket;
import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;
import io.netty.util.Attribute;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Sunnick on 2019/1/16/016.
 */
public class SessionUtil {
    private static final Map<String,Channel> sessionMap = new ConcurrentHashMap<String, Channel>() ;


    public static void bindSession(Session  session, Channel channel){
        sessionMap.putIfAbsent(session.getUserId(),channel);
        channel.attr(Attributes.SESSION).set(session);
    }

    public static void unBindSession(Session  session, Channel channel){
        sessionMap.remove(session.getUserId());
        channel.attr(Attributes.SESSION).remove();
    }

    public static Channel getChannelBySession(Session session){
        return getChannelByUserId(session.getUserId());
    }

    public static Map<String,Channel> getAllSession(){
        return sessionMap;
    }

    public static Channel getChannelByUserId(String id){
        return sessionMap.get(id);
    }

    public static Session getSessionByChannel(Channel channel){
        return channel.attr(Attributes.SESSION).get();
    }

    public static boolean hasLogin(Channel channel){
        Attribute<Session> login =  channel.attr(Attributes.SESSION);
        //只要标志位不为空，即表示登录过
        if (login.get() != null)
            return true;
        return false;
    }
}
