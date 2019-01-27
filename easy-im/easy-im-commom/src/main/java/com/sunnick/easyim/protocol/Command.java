package com.sunnick.easyim.protocol;

/**
 * Created by Sunnick on 2019/1/13/013.
 *
 * 指令集
 */
public interface Command {
    /**
     * 心跳包
     */
    final Byte HEART_BEAT = 0;
    /**
     * 登录请求
     */
    final Byte LOGIN_REQUEST = 1;
    /**
     * 登录响应
     */
    final Byte LOGIN_RESPONSE = 2;

    /**
     * 消息请求
     */
    final Byte MESSAGE_REQUEST = 3;
    /**
     * 消息响应
     */
    final Byte MESSAGE_RESPONSE = 4;
    /**
     * 创建群聊
     */
    final Byte CREATE_GROUP_REQUEST = 5;

    /**
     * 创建群聊响应
     */
    final Byte CREATE_GROUP_RESPONSE = 6;

    /**
     * 发送群聊
     */
    final Byte GROUP_MESSAGE_REQUEST = 7;

    /**
     * 创建群聊响应
     */
    final Byte GROUP_MESSAGE_RESPONSE = 8;

    /**
     * 默认错误
     */
    final Byte DEFAULT_ERROR = 127;
}
