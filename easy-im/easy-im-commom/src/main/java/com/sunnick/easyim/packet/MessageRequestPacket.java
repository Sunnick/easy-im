package com.sunnick.easyim.packet;

import com.sunnick.easyim.protocol.Packet;

import static com.sunnick.easyim.protocol.Command.MESSAGE_REQUEST;

/**
 * Created by Sunnick on 2019/1/13/013.
 *
 * 发送消息的packet
 */
public class MessageRequestPacket extends Packet {

    /**
     * 消息内容
     */
    private String message;


    /**
     * 消息接受者
     */
    private String toUserId;


    public String getToUserId() {
        return toUserId;
    }

    public void setToUserId(String toUserId) {
        this.toUserId = toUserId;
    }

    @Override
    public Byte getCommand() {
        return MESSAGE_REQUEST;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
