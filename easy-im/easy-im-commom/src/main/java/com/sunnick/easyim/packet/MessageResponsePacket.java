package com.sunnick.easyim.packet;

import com.sunnick.easyim.protocol.Command;

/**
 * Created by Sunnick on 2019/1/13/013.
 * 消息响应
 */
public class MessageResponsePacket extends BaseResponsePacket {

    /**
     * 响应内容
     */
    private String message;

    /**
     * 消息来源
     */
    private String fromUserId;
    private String fromUserName;

    public String getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(String fromUserId) {
        this.fromUserId = fromUserId;
    }

    public String getFromUserName() {
        return fromUserName;
    }

    public void setFromUserName(String fromUserName) {
        this.fromUserName = fromUserName;
    }

    @Override
    public Byte getCommand() {
        return Command.MESSAGE_RESPONSE;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
