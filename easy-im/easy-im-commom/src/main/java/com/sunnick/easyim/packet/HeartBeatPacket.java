package com.sunnick.easyim.packet;

import com.sunnick.easyim.protocol.Command;
import com.sunnick.easyim.protocol.Packet;

/**
 * Created by Sunnick on 2019/1/27/027.
 */
public class HeartBeatPacket extends Packet {

    private String msg = "ping-pong";

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public Byte getCommand() {
        return Command.HEART_BEAT;
    }
}
