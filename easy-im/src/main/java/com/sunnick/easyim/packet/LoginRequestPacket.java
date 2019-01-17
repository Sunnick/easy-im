package com.sunnick.easyim.packet;

import com.sunnick.easyim.protocol.Command;
import com.sunnick.easyim.protocol.Packet;

/**
 * Created by Sunnick on 2019/1/13/013.
 * 登录请求包
 */

public class LoginRequestPacket extends Packet {

    private String userId;
    private String userName;
    private String password;

    @Override
    public Byte getCommand() {
        return Command.LOGIN_REQUEST;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LoginRequestPacket(String userId, String userName, String password) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
    }
    public LoginRequestPacket(){

    }
}
