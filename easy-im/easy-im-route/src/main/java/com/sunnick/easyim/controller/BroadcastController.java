package com.sunnick.easyim.controller;

import com.alibaba.fastjson.JSON;
import com.sunnick.easyim.entity.BroadcastRequest;
import com.sunnick.easyim.entity.ChatResponse;
import com.sunnick.easyim.entity.IMLoginRequest;
import com.sunnick.easyim.entity.P2PChatRequest;
import com.sunnick.easyim.service.BroadcastService;
import com.sunnick.easyim.service.LoginService;
import com.sunnick.easyim.service.P2PChatService;
import com.sunnick.easyim.util.CheckUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * Created by Sunnick on 2019/2/21/021.
 *
 * 发送广播消息
 */
@Controller
public class BroadcastController {
    private static Logger logger = LoggerFactory.getLogger(P2PChatController.class);

    @Autowired
    LoginService loginService;

    @Autowired
    BroadcastService broadcastService;

    @RequestMapping(value = "/broadcast")
    @ResponseBody
    public ChatResponse broadcast(@RequestBody BroadcastRequest request){
        logger.info("收到广播请求：{}", JSON.toJSONString(request));
        ChatResponse response = new ChatResponse();
        if(request == null || !CheckUtil.isAllNotEmpty(request.getFromUserId(),request.getMsg())){
            response.setCode("3001");
            response.setMsg("必输项校验为空");
            return response;
        }
        if (!loginService.isLogin(request.getFromUserId())){
            response.setCode("3002");
            response.setMsg("请您先登录！");
            return response;
        }
        broadcastService.broadcast(request);
        return response;
    }
}
