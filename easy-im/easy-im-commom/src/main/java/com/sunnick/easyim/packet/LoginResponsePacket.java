package com.sunnick.easyim.packet;

import com.sunnick.easyim.protocol.Command;

/**
 * Created by Sunnick on 2019/1/13/013.
 *
 * 登录响应packet
 */
public class LoginResponsePacket extends BaseResponsePacket {
    public Byte getCommand() {
        return Command.LOGIN_RESPONSE;
    }
}
