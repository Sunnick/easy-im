package com.sunnick.easyim.handler;

import com.alibaba.fastjson.JSON;
import com.sunnick.easyim.packet.*;
import com.sunnick.easyim.protocol.Packet;
import com.sunnick.easyim.protocol.PacketCodeC;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import static com.sunnick.easyim.protocol.Command.*;
import static com.sunnick.easyim.protocol.Command.CREATE_GROUP_REQUEST;
import static com.sunnick.easyim.protocol.Command.CREATE_GROUP_RESPONSE;

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
        handlerMap.putIfAbsent(MESSAGE_RESPONSE,MessageResponseHandler.getInstance());
        handlerMap.putIfAbsent(CREATE_GROUP_RESPONSE,CreateGroupResponseHandler.getInstance());
        handlerMap.putIfAbsent(GROUP_MESSAGE_RESPONSE,GroupMessageResponseHandler.getInstance());
        handlerMap.putIfAbsent(DEFAULT_ERROR, DefaultErrorHandler.getInstance());
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Packet packet) throws Exception {
        logger.info("收到服务器响应：{}",JSON.toJSONString(packet));
        SimpleChannelInboundHandler handler =  handlerMap.get(packet.getCommand());
        if(handler != null ){
            handler.channelRead(channelHandlerContext,packet);
        }else{
            logger.info("未找到响应指令，请确认指令是否正确！");
        }
    }
}
