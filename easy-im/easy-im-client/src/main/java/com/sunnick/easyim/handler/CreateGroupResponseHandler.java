package com.sunnick.easyim.handler;

import com.alibaba.fastjson.JSON;
import com.sunnick.easyim.packet.CreateGroupResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Sunnick on 2019/1/20/020.
 */
public class CreateGroupResponseHandler extends EasyImChannelInBoundHandler<CreateGroupResponsePacket> {

    private static Logger logger = LoggerFactory.getLogger(CreateGroupResponseHandler.class);

    public static CreateGroupResponseHandler getInstance(){
        return instance;
    }

    private CreateGroupResponseHandler(){}

    private static CreateGroupResponseHandler instance = new CreateGroupResponseHandler();


    @Override
    protected void handleResponse(ChannelHandlerContext ctx, CreateGroupResponsePacket packet) {
        if (!packet.success()){
            logger.info(JSON.toJSONString(packet));
            return;
        }
        logger.info("群聊创建成功，群id为：{}",packet.getGroupId());
        logger.info("群成员为：{}",packet.getUserNames());
    }
}
