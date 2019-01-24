package com.sunnick.easyim.packet;

import com.sunnick.easyim.protocol.Packet;

import static com.sunnick.easyim.protocol.Command.GROUP_MESSAGE_REQUEST;

/**
 * Created by Sunnick on 2019/1/22/022.
 */
public class GroupMessageRequestPacket extends Packet {

    private String groupId;
    private String groupMsg;

    @Override
    public Byte getCommand() {
        return GROUP_MESSAGE_REQUEST;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getGroupMsg() {
        return groupMsg;
    }

    public void setGroupMsg(String groupMsg) {
        this.groupMsg = groupMsg;
    }
}
