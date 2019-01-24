package com.sunnick.easyim.packet;

import static com.sunnick.easyim.protocol.Command.LOGIN_RESPONSE;

/**
 * Created by Sunnick on 2019/1/13/013.
 *
 * 登录响应packet
 */
public class LoginResponsePacket extends BaseResponsePacket {
    public Byte getCommand() {
        return LOGIN_RESPONSE;
    }
}
