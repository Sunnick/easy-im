package com.sunnick.easyim.packet;

import static com.sunnick.easyim.protocol.Command.DEFAULT_ERROR;

/**
 * Created by Sunnick on 2019/1/22/022.
 *
 * 错误报文
 */
public class DefaultErrorPacket extends BaseResponsePacket{

    @Override
    public Byte getCommand() {
        return DEFAULT_ERROR;
    }
}
