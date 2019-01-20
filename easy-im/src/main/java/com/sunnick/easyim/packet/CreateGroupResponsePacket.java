package com.sunnick.easyim.packet;

import java.util.List;

import static com.sunnick.easyim.protocol.Command.CREATE_GROUP_RESPONSE;

/**
 * Created by Sunnick on 2019/1/20/020.
 */
public class CreateGroupResponsePacket extends BaseResponsePacket {

    private String groupId;

    private List<String> userNames;

    public List<String> getUserNames() {
        return userNames;
    }

    public void setUserNames(List<String> userNames) {
        this.userNames = userNames;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    @Override
    public Byte getCommand() {
        return CREATE_GROUP_RESPONSE;
    }
}
