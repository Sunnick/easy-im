package com.sunnick.easyim.Command;

import com.alibaba.fastjson.JSON;
import com.sunnick.easyim.Client;
import com.sunnick.easyim.constans.Constans;
import com.sunnick.easyim.entity.ChatResponse;
import com.sunnick.easyim.entity.P2PChatRequest;
import com.sunnick.easyim.packet.MessageRequestPacket;
import io.netty.channel.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Sunnick on 2019/1/18/018.
 * 私聊 sendToUser::userId::msg
 */
public class P2PCommand extends AbstractCommand implements ConsoleCommand{

    public P2PCommand(){
        super();
    }

    private static Logger logger = LoggerFactory.getLogger(P2PCommand.class);


    public void exec(Channel channel,String string) {
        String[] strs = string.split("::");
        if(strs.length < 3){
            logger.info("私聊请按如下格式发送：sendToUser::userId::msg");
        }else {
//            MessageRequestPacket packet = buildMessageRequestPacket(strs[1],strs[2]);
//            ChannelUtil.writeAndFlush(channel,packet);

            P2PChatRequest request = buildP2PChatRequest(strs[1],strs[2]);
            logger.info("发送地址为：" + Constans.HTTP_SCHEME + getClientRunner().getRouteHost() + ":" + getClientRunner().getRoutePort() + "/p2pChat");
            ChatResponse response = getRestTemplate().postForObject(
                    Constans.HTTP_SCHEME + getClientRunner().getRouteHost() + ":" + getClientRunner().getRoutePort() + "/p2pChat",
                    request,ChatResponse.class);

            logger.info("收到服务器返回：{}",JSON.toJSONString(response));
        }
    }

    private P2PChatRequest buildP2PChatRequest(String userId,String msg){
        P2PChatRequest request = new P2PChatRequest();
        request.setFromUserId(Client.userid);
        request.setToUserId(userId);
        request.setMsg(msg);
        logger.info("发送消息给{}：{}",userId,msg);
        return request;
    }


    private MessageRequestPacket buildMessageRequestPacket(String userId,String msg) {
        MessageRequestPacket request = new MessageRequestPacket();
            logger.info("发送消息给{}：{}",userId,msg);
            request.setToUserId(userId);
            request.setMessage(msg);
        return request;
    }
}
