package com.sunnick.easyim.controller;

import com.alibaba.fastjson.JSON;
import com.sunnick.easyim.entity.ChatResponse;
import com.sunnick.easyim.entity.IMLoginRequest;
import com.sunnick.easyim.entity.P2PChatRequest;
import com.sunnick.easyim.service.LoginService;
import com.sunnick.easyim.service.P2PChatService;
import com.sunnick.easyim.util.CheckUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Sunnick on 2019/2/10/010.
 *
 * 聊天controller
 */
@Controller
public class P2PChatController {
    private static Logger logger = LoggerFactory.getLogger(P2PChatController.class);

    @Autowired
    P2PChatService chatService;

    @Autowired
    LoginService loginService;

    @RequestMapping(value = "/p2pChat",method = RequestMethod.POST)
    @ResponseBody
    public ChatResponse p2pChat(@RequestBody P2PChatRequest request){
        logger.info("收到p2p请求：{}", JSON.toJSONString(request));
        ChatResponse response = new ChatResponse();
        if(request == null || !CheckUtil.isAllNotEmpty(request.getFromUserId(),request.getToUserId(),request.getMsg())){
            response.setCode("3001");
            response.setMsg("必输项校验为空");
            return response;
        }
        if (!loginService.isLogin(request.getFromUserId())){
            response.setCode("3002");
            response.setMsg("请您先登录！");
            return response;
        }
        if (!loginService.isLogin(request.getToUserId())){
            response.setCode("3003");
            response.setMsg("对方还未登录，无法发送消息！");
            return response;
        }
        //获取到登录信息
        IMLoginRequest loginInfo = loginService.getLoginInfo(request.getToUserId());
        P2PChatRequest p2PChatRequest = new P2PChatRequest();
        p2PChatRequest.setFromUserId(request.getFromUserId());
        p2PChatRequest.setToUserId(request.getToUserId());
        p2PChatRequest.setMsg(request.getMsg());
        response = chatService.p2pChat(p2PChatRequest);
        return response;
    }


    @RequestMapping(value = "/groupChat")
    @ResponseBody
    public ChatResponse groupChat(){
        return null;
    }

    @RequestMapping(value = "/createGroup")
    @ResponseBody
    public ChatResponse createGroup(){
        return null;
    }
}
