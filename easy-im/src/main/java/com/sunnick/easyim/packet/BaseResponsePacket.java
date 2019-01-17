package com.sunnick.easyim.packet;

import com.sunnick.easyim.protocol.Packet;

/**
 * Created by Sunnick on 2019/1/13/013.
 */
public abstract class BaseResponsePacket extends Packet {
    /**
     * 返回码,0000表示成功
     */
    private String code;
    /**
     * 返回消息
     */
    private String msg;

    /**
     * 判断是否操作成功，0000表示成功
     */
    public boolean success(){
        return "0000".equals(code);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
