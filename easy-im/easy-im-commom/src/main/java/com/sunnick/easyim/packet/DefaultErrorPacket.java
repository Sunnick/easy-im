package com.sunnick.easyim.packet;

import com.sunnick.easyim.protocol.Command;

/**
 * Created by Sunnick on 2019/1/22/022.
 *
 * 错误报文
 */
public class DefaultErrorPacket extends BaseResponsePacket{

    @Override
    public Byte getCommand() {
        return Command.DEFAULT_ERROR;
    }
}
