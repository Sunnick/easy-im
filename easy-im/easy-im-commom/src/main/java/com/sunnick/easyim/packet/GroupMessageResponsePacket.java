package com.sunnick.easyim.packet;

import static com.sunnick.easyim.protocol.Command.GROUP_MESSAGE_RESPONSE;

/**
 * Created by Sunnick on 2019/1/22/022.
 */
public class GroupMessageResponsePacket extends BaseResponsePacket {

    private String groupId;
    private String fromUserName;
    private String groupMsg;

    @Override
    public Byte getCommand() {
        return GROUP_MESSAGE_RESPONSE;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getFromUserName() {
        return fromUserName;
    }

    public void setFromUserName(String fromUserName) {
        this.fromUserName = fromUserName;
    }

    public String getGroupMsg() {
        return groupMsg;
    }

    public void setGroupMsg(String groupMsg) {
        this.groupMsg = groupMsg;
    }
}
