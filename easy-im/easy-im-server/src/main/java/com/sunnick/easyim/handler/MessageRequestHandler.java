package com.sunnick.easyim.handler;

import com.alibaba.fastjson.JSON;
import com.sunnick.easyim.packet.DefaultErrorPacket;
import com.sunnick.easyim.packet.MessageRequestPacket;
import com.sunnick.easyim.util.Session;
import com.sunnick.easyim.util.SessionUtil;
import com.sunnick.easyim.packet.MessageResponsePacket;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * Created by Sunnick on 2019/1/13/013.
 */
public class MessageRequestHandler extends EasyImChannelInBoundHandler<MessageRequestPacket> {

    private static Logger logger = LoggerFactory.getLogger(MessageRequestHandler.class);

    public static MessageRequestHandler getInstance(){
        return instance;
    }

    private MessageRequestHandler(){}

    private static MessageRequestHandler instance = new MessageRequestHandler();
    /**
     * 处理消息请求
     */
    private void handlMessage(ChannelHandlerContext ctx,MessageRequestPacket request) {
        logger.info("收到客户端消息：{}", request.getMessage());
        //拿到消息发送方的userId
        Session session = SessionUtil.getSessionByChannel(ctx.channel());
        logger.info("发送方为：{}" ,JSON.toJSONString(session));
        String fromUserId = session.getUserId();
        String fromUserName = session.getUserName();

        //构造发送报文
        MessageResponsePacket response = new MessageResponsePacket();
        response.setFromUserId(fromUserId);
        response.setFromUserName(fromUserName);
        response.setMessage(request.getMessage());


        //拿到接收方的信息
        String toUserId = request.getToUserId();
        //toUserId不为空，则私聊；为空则发广播
        if(!StringUtils.isEmpty(toUserId)){
            p2pChat(ctx,toUserId,response);
        }else{
            broadcast(ctx,response);
        }


    }

    /**
     * 给所有人群发消息
     */
    private void broadcast(ChannelHandlerContext ctx, MessageResponsePacket response) {
        Channel channel = null;
        //获取所有channel，遍历
        Map<String,Channel> sessions = SessionUtil.getAllSession();
        for (Map.Entry<String,Channel> entry : sessions.entrySet()){
            channel = entry.getValue();
            logger.info("发送给客户端{}：{}" ,entry.getKey(), JSON.toJSONString(response));
            writeMessage(ctx, response, channel);
        }
    }

    /**
     * 点对点私聊
     */
    private void p2pChat(ChannelHandlerContext ctx, String toUserId, MessageResponsePacket response) {
        Channel toUserChannel = SessionUtil.getChannelByUserId(toUserId);
        logger.info("发送给客户端{}：{}" ,toUserId, JSON.toJSONString(response));
        writeMessage(ctx,response, toUserChannel);
    }

    private void writeMessage(ChannelHandlerContext ctx, MessageResponsePacket response, Channel toUserChannel) {
        //写数据
        if (toUserChannel!= null && SessionUtil.hasLogin(toUserChannel) ) {
            toUserChannel.writeAndFlush(response);
        }else{
            DefaultErrorPacket error = new DefaultErrorPacket();
            error.setCode("3001");
            error.setMsg("该用户未登录，无法向他发送消息!");
            logger.info("返回给客户端：{}",JSON.toJSONString(error));
            ctx.writeAndFlush(error);
        }
    }

    @Override
    protected void handleResponse(ChannelHandlerContext ctx, MessageRequestPacket messageRequestPacket) {
        handlMessage(ctx,messageRequestPacket);
    }

}
