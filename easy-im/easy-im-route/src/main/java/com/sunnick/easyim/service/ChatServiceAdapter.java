package com.sunnick.easyim.service;

import com.sunnick.easyim.entity.*;

/**
 * Created by Sunnick on 2019/2/11/011.
 */
public class ChatServiceAdapter implements ChatService {
    @Override
    public ChatResponse p2pChat(P2PChatRequest request) {

        return null;
    }

    @Override
    public void broadcast(BroadcastRequest request) {

    }

    @Override
    public void groupChat(GroupChatRequest request) {

    }

    @Override
    public void createGroup(CreateGroupRequest request) {

    }

    @Override
    public void joinGroup(JoinGroupRequest request) {

    }

    @Override
    public void quitGroup(QuitGroupRequest request) {

    }
}
