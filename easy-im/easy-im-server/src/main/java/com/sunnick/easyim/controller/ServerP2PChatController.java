package com.sunnick.easyim.controller;

import com.sunnick.easyim.entity.ChatResponse;
import com.sunnick.easyim.entity.P2PChatRequest;
import com.sunnick.easyim.packet.MessageResponsePacket;
import com.sunnick.easyim.util.ChannelUtil;
import com.sunnick.easyim.util.Session;
import com.sunnick.easyim.util.SessionUtil;
import io.netty.channel.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Sunnick on 2019/2/19/019.
 */
@Controller
public class ServerP2PChatController {
    private static Logger logger = LoggerFactory.getLogger(ServerP2PChatController.class);

    /**
     * 根据userid找到channel
     * 向channel发送消息
     */
    @RequestMapping(value = "/p2pChat")
    @ResponseBody
    public ChatResponse p2pChat(@RequestBody P2PChatRequest request) {
        ChatResponse response = new ChatResponse();
        String userId = request.getToUserId();
        Channel channel = SessionUtil.getChannelByUserId(userId);
        if (channel == null) {
            response.setCode("4001");
            response.setMsg(userId + "没有登录，无法向其发送即时消息！");
            return response;
        }
        Session session = SessionUtil.getSessionByChannel(channel);
        MessageResponsePacket responsePacket = new MessageResponsePacket();
        responsePacket.setFromUserName(session.getUserName());
        responsePacket.setFromUserId(userId);
        responsePacket.setMessage(request.getMsg());

        //向客户端写入数据
        ChannelUtil.writeAndFlush(channel,responsePacket);

        return response;
    }
}
