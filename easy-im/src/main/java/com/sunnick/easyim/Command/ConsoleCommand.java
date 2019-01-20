package com.sunnick.easyim.Command;

import io.netty.channel.Channel;

/**
 * Created by Sunnick on 2019/1/18/018.
 * 命令行指令接口
 * 单聊 sendToUser::userId::msg
 * 群聊 sendToGroup::groupId::msg
 * 发起群聊 createGroup::userId1,userId2,userId3...
 * 退出群聊 quitGroup::groupId
 * 加入群聊 joinGroup::groupId
 * 查询所有在线用户 getAllUsers
 * 查询群聊中在线用户 getGroupUsers::groupId
 * 广播 broadcast::msg
 */
public interface ConsoleCommand {
    void exec(Channel channel, String string);
}
