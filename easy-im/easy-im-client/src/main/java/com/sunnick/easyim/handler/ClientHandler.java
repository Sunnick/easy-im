package com.sunnick.easyim.handler;

import com.alibaba.fastjson.JSON;
import com.sunnick.easyim.Client;
import com.sunnick.easyim.protocol.Command;
import com.sunnick.easyim.protocol.Packet;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Sunnick on 2019/1/13/013.
 */

@ChannelHandler.Sharable
public class ClientHandler extends SimpleChannelInboundHandler<Packet> {

    private static Logger logger = LoggerFactory.getLogger(ClientHandler.class);

    private ClientHandler(){}

    private static ClientHandler instance = new ClientHandler();

    public static ClientHandler getInstance(){
        return instance;
    }

    private static Map<Byte,SimpleChannelInboundHandler<? extends Packet>> handlerMap = new ConcurrentHashMap<>();
    static{
        handlerMap.putIfAbsent(Command.MESSAGE_RESPONSE,MessageResponseHandler.getInstance());
        handlerMap.putIfAbsent(Command.CREATE_GROUP_RESPONSE,CreateGroupResponseHandler.getInstance());
        handlerMap.putIfAbsent(Command.GROUP_MESSAGE_RESPONSE,GroupMessageResponseHandler.getInstance());
        handlerMap.putIfAbsent(Command.DEFAULT_ERROR, DefaultErrorHandler.getInstance());
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Packet packet) throws Exception {
        logger.info("收到服务器响应：{}",JSON.toJSONString(packet));
        SimpleChannelInboundHandler handler =  handlerMap.get(packet.getCommand());
        if(handler != null ){
            handler.channelRead(channelHandlerContext,packet);
        }else if (packet.getCommand() == Command.HEART_BEAT){
            logger.info("收到心跳响应：{}",JSON.toJSONString(packet));
        }else{
            logger.info("未找到响应指令，请确认指令是否正确！");
        }
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
       logger.info("与服务端连接断开，即将重连..");
        Client.start();

    }
}
