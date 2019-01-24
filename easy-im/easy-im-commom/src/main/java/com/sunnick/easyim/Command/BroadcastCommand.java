package com.sunnick.easyim.Command;

import com.sunnick.easyim.packet.MessageRequestPacket;
import com.sunnick.easyim.util.ChannelUtil;
import io.netty.channel.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Sunnick on 2019/1/18/018.
 * 广播 broadcast::msg
 */
public class BroadcastCommand implements ConsoleCommand {

    private static Logger logger = LoggerFactory.getLogger(P2PCommand.class);

    public void exec(Channel channel,String string) {
        String[] strs = string.split("::");
        if(strs.length < 2){
            logger.info("广播请按如下格式发送：broadcast::msg");
        }else {
            MessageRequestPacket packet = buildRequestPacket(strs[1]);
            ChannelUtil.writeAndFlush(channel,packet);
        }
    }

    private MessageRequestPacket buildRequestPacket(String msg) {
        MessageRequestPacket request = new MessageRequestPacket();
        request.setMessage(msg);
        return request;
    }
}
