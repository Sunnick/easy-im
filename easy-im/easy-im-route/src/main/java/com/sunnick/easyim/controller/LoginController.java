package com.sunnick.easyim.controller;

import com.alibaba.fastjson.JSON;
import com.sunnick.easyim.entity.IMLoginRequest;
import com.sunnick.easyim.entity.IMLoginResponse;
import com.sunnick.easyim.entity.IMLogoutRequest;
import com.sunnick.easyim.entity.IMLogoutResponse;
import com.sunnick.easyim.service.LoginService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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

    @RequestMapping(value = "/logout/{userid}",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public IMLogoutResponse logout(@PathVariable String  userid){
        logger.info("收到客户端登出请求：{}", userid);
        IMLogoutResponse response = new IMLogoutResponse();
        if(!StringUtils.isEmpty(userid) && loginService.isLogin(userid)){
            loginService.logout(userid);
        }
        logger.info("登出返回给客户端:{}",JSON.toJSONString(response));
        return response;
    }

    @RequestMapping(value = "/getAllUser",method = {RequestMethod.GET})
    @ResponseBody
    public Map<String,IMLoginRequest> getAllUser(){
        return loginService.getAllLoginUser();
    }
}
