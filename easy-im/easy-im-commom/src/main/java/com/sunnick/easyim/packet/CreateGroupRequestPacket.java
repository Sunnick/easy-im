package com.sunnick.easyim.packet;

import com.sunnick.easyim.protocol.Command;
import com.sunnick.easyim.protocol.Packet;

import java.util.List;

/**
 * Created by Sunnick on 2019/1/20/020.
 *
 * 创建群聊
 */
public class CreateGroupRequestPacket extends Packet {
    @Override
    public Byte getCommand() {
        return Command.CREATE_GROUP_REQUEST;
    }

    private List<String> users ;

    public List<String> getUsers() {
        return users;
    }

    public void setUsers(List<String> users) {
        this.users = users;
    }
}
