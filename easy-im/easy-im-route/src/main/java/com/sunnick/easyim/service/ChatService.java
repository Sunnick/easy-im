package com.sunnick.easyim.service;

import com.sunnick.easyim.entity.*;

/**
 * Created by Sunnick on 2019/2/10/010.
 */
public interface ChatService {
    ChatResponse p2pChat(P2PChatRequest request);
    void broadcast(BroadcastRequest request);
    void groupChat(GroupChatRequest request);
    void createGroup(CreateGroupRequest request);
    void joinGroup(JoinGroupRequest request);
    void quitGroup(QuitGroupRequest request);
}
