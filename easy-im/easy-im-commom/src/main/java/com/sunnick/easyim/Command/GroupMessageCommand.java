package com.sunnick.easyim.Command;

import com.sunnick.easyim.util.ChannelUtil;
import com.sunnick.easyim.packet.GroupMessageRequestPacket;
import io.netty.channel.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Sunnick on 2019/1/22/022.
 * 群聊 sendToGroup::groupId::msg
 */
public class GroupMessageCommand implements ConsoleCommand {

    private static Logger logger = LoggerFactory.getLogger(GroupMessageCommand.class);

    @Override
    public void exec(Channel channel, String string) {
        String[] strs = string.split("::");
        if(strs.length < 3){
            logger.info("群聊请输入如下命令 sendToGroup::groupId::msg");
            return;
        }
        GroupMessageRequestPacket packet = BuildRequestPacket(strs[1],strs[2]);
        ChannelUtil.writeAndFlush(channel,packet);
    }

    private GroupMessageRequestPacket BuildRequestPacket(String groupId,String groupMsg) {
        GroupMessageRequestPacket packet = new GroupMessageRequestPacket();
        packet.setGroupId(groupId);
        packet.setGroupMsg(groupMsg);
        return packet;
    }
}
