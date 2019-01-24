package com.sunnick.easyim.util;

import io.netty.channel.Channel;
import io.netty.util.AttributeKey;

/**
 * Created by Sunnick on 2019/1/13/013.
 */
public interface Attributes {
    /**
     * 登录状态，使用channel.attr()方法保存
     */
    AttributeKey<Boolean> LOGIN = AttributeKey.newInstance("login");
    AttributeKey<Session> SESSION = AttributeKey.newInstance("session");
}
