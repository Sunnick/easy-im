package com.sunnick.easyim.controller;

import com.alibaba.fastjson.JSON;
import com.sunnick.easyim.entity.IMLoginRequest;
import com.sunnick.easyim.entity.IMLoginResponse;
import com.sunnick.easyim.service.LoginService;
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
 */
@Controller
public class LoginController {
    private static Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    LoginService loginService;

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public IMLoginResponse login(@RequestBody IMLoginRequest request){
        logger.info("收到客户端登录请求：{}", JSON.toJSONString(request));
        IMLoginResponse response = new IMLoginResponse();
        if(loginService.isLogin(request.getUserid())){
            response.setCode("2001");
            response.setMsg("请勿重复登录！");
            return response;
        }
        loginService.login(request);
        logger.info("登录返回给客户端:{}",JSON.toJSONString(response));
        return response;
    }
}
