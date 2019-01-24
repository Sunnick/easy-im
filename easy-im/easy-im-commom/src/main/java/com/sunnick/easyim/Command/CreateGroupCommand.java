package com.sunnick.easyim.Command;

import com.sunnick.easyim.packet.CreateGroupRequestPacket;
import com.sunnick.easyim.util.ChannelUtil;
import io.netty.channel.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sunnick on 2019/1/20/020.
 * 发起群聊 createGroup::userId1,userId2,userId3...
 */
public class CreateGroupCommand implements ConsoleCommand {

    private static Logger logger = LoggerFactory.getLogger(CreateGroupCommand.class);

    @Override
    public void exec(Channel channel, String string) {

        String[] strs = string.split("::");
        if(strs.length < 2){
            logger.info("创建群聊请使用 createGroup::userId1,userId2,userId3... 命令！");
            return;
        }
        CreateGroupRequestPacket packet = buildPacket(strs[1]);
        ChannelUtil.writeAndFlush(channel,packet);
    }

    private CreateGroupRequestPacket buildPacket(String usersString) {
        CreateGroupRequestPacket packet = new CreateGroupRequestPacket();
        String[] users = usersString.split(",");
        List<String> userList = new ArrayList<String>();
        for (String str : users){
            userList.add(str);
        }
        packet.setUsers(userList);

        return packet;
    }
}
