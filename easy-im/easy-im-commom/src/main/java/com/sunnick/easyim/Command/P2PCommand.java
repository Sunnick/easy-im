package com.sunnick.easyim.Command;

import com.sunnick.easyim.packet.MessageRequestPacket;
import com.sunnick.easyim.util.ChannelUtil;
import io.netty.channel.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Sunnick on 2019/1/18/018.
 * 私聊 sendToUser::userId::msg
 */
public class P2PCommand implements ConsoleCommand{
    private static Logger logger = LoggerFactory.getLogger(P2PCommand.class);

    public void exec(Channel channel,String string) {
        String[] strs = string.split("::");
        if(strs.length < 3){
            logger.info("私聊请按如下格式发送：sendToUser::userId::msg");
        }else {
            MessageRequestPacket packet = buildMessageRequestPacket(strs[1],strs[2]);
            ChannelUtil.writeAndFlush(channel,packet);
        }
    }


    private MessageRequestPacket buildMessageRequestPacket(String userId,String msg) {
        MessageRequestPacket request = new MessageRequestPacket();
            logger.info("发送消息给{}：{}",userId,msg);
            request.setToUserId(userId);
            request.setMessage(msg);
        return request;
    }
}
