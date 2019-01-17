package com.sunnick.easyim.protocol;

/**
 * Created by Sunnick on 2019/1/13/013.
 *
 * 通信过程的对象，所有需要通过网络传输的对象，都继承自Packet
 */

public abstract class Packet {
    /**
     * 协议版本号
     */
    private Byte version = 1;

    /**
     * 操作指令
     */
    public abstract Byte getCommand();

    public Byte getVersion() {
        return version;
    }

    public void setVersion(Byte version) {
        this.version = version;
    }
}
