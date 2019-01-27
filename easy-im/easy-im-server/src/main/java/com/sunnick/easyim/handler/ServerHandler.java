package com.sunnick.easyim.handler;

import com.alibaba.fastjson.JSON;
import com.sunnick.easyim.protocol.Packet;
import com.sunnick.easyim.util.Session;
import com.sunnick.easyim.util.SessionUtil;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static com.sunnick.easyim.protocol.Command.*;


/**
 * Created by Sunnick on 2019/1/13/013.
 */
@ChannelHandler.Sharable
public class ServerHandler extends SimpleChannelInboundHandler<Packet> {

    private static Logger logger = LoggerFactory.getLogger(ServerHandler.class);

    private ServerHandler(){}

    private static ServerHandler instance = new ServerHandler();

    public static ServerHandler getInstance(){
        return instance;
    }

    private static Map<Byte,SimpleChannelInboundHandler<? extends Packet>> handlerMap = new ConcurrentHashMap<>();
    static{
        handlerMap.putIfAbsent(MESSAGE_REQUEST,MessageRequestHandler.getInstance());
        handlerMap.putIfAbsent(CREATE_GROUP_REQUEST,CreateGroupRequestHandler.getInstance());
        handlerMap.putIfAbsent(GROUP_MESSAGE_REQUEST,GroupMessageRequestHandler.getInstance());
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Packet packet) throws Exception {
        logger.info("收到客户端请求：{}", JSON.toJSONString(packet));
        SimpleChannelInboundHandler handler =  handlerMap.get(packet.getCommand());
        if(handler != null ){
            handler.channelRead(channelHandlerContext,packet);
        }else{
            logger.info("未找到响应指令，请确认指令是否正确！");
        }
    }
}
