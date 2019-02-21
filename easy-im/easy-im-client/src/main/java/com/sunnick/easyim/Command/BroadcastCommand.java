package com.sunnick.easyim.Command;

import com.alibaba.fastjson.JSON;
import com.sunnick.easyim.Client;
import com.sunnick.easyim.constans.Constans;
import com.sunnick.easyim.entity.BroadcastRequest;
import com.sunnick.easyim.entity.ChatResponse;
import com.sunnick.easyim.packet.MessageRequestPacket;
import com.sunnick.easyim.util.ChannelUtil;
import io.netty.channel.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Sunnick on 2019/1/18/018.
 * 广播 broadcast::msg
 */
public class BroadcastCommand extends AbstractCommand implements ConsoleCommand {

    public BroadcastCommand(){
        super();
    }

    private static Logger logger = LoggerFactory.getLogger(P2PCommand.class);

    public void exec(Channel channel,String string) {
        String[] strs = string.split("::");
        if(strs.length < 2){
            logger.info("广播请按如下格式发送：broadcast::msg");
        }else {
//            MessageRequestPacket packet = buildRequestPacket(strs[1]);
//            ChannelUtil.writeAndFlush(channel,packet);
            BroadcastRequest request = buildBroadcastRequest(strs[1]);
            logger.info("发送地址为：" + Constans.HTTP_SCHEME + getClientRunner().getRouteHost() + ":" + getClientRunner().getRoutePort() + "/broadcast");
            ChatResponse response = getRestTemplate().postForObject(
                    Constans.HTTP_SCHEME + getClientRunner().getRouteHost() + ":" + getClientRunner().getRoutePort() + "/broadcast",
                    request,ChatResponse.class);

            logger.info("收到服务器返回：{}", JSON.toJSONString(response));

        }
    }

    private BroadcastRequest buildBroadcastRequest(String msg){
        BroadcastRequest request = new BroadcastRequest();
        request.setFromUserId(Client.userid);
        request.setMsg(msg);
        return  request;
    }

    private MessageRequestPacket buildRequestPacket(String msg) {
        MessageRequestPacket request = new MessageRequestPacket();
        request.setMessage(msg);
        return request;
    }
}
