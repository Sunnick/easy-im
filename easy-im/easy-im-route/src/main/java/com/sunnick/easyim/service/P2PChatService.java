package com.sunnick.easyim.service;

import com.sunnick.easyim.constans.Constans;
import com.sunnick.easyim.entity.ChatResponse;
import com.sunnick.easyim.entity.IMLoginRequest;
import com.sunnick.easyim.entity.P2PChatRequest;
import com.sunnick.easyim.local.LocalLoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Sunnick on 2019/2/11/011.
 */
@Service
public class P2PChatService extends ChatServiceAdapter {

    private static Logger logger = LoggerFactory.getLogger(P2PChatService.class);

    @Autowired
    LoginService loginService;

    @Autowired
    @Qualifier("restTemplate")
    RestTemplate restTemplate;
    /**
     * 1.找到目标服务器地址
     * 2.向目标服务器发聊天请求
     */
    @Override
    public ChatResponse p2pChat(P2PChatRequest request) {

        //找到目标服务器地址
        IMLoginRequest loginInfo = loginService.getLoginInfo(request.getToUserId());
        //向目标服务器发聊天请求
        ChatResponse response = restTemplate.postForObject(
                Constans.HTTP_SCHEME + loginInfo.getServerHost() + ":" + loginInfo.getHttpPort() + "/p2pChat",
                request,ChatResponse.class);

        return response;
    }
}
