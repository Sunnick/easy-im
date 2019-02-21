package com.sunnick.easyim.service;

import com.sunnick.easyim.constans.Constans;
import com.sunnick.easyim.entity.BroadcastRequest;
import com.sunnick.easyim.entity.ChatResponse;
import com.sunnick.easyim.entity.IMLoginRequest;
import com.sunnick.easyim.entity.P2PChatRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.client.AsyncRestTemplate;

import java.util.Map;

/**
 * Created by Sunnick on 2019/2/21/021.
 */
@Service
public class BroadcastService extends ChatServiceAdapter {

    private static Logger logger = LoggerFactory.getLogger(BroadcastService.class);

    @Autowired
    AsyncRestTemplate asyncRestTemplate;

    @Autowired
    LoginService loginService;

    /**
     * 因广播涉及用户较多，不能采用同步阻塞方式，应采用异步非阻塞方式
     */
    @Override
    public void broadcast(BroadcastRequest request) {
        //查找出所有在线人员
        Map<String,IMLoginRequest> allUser = loginService.getAllLoginUser();
        //向所有人员发送消息
        for (Map.Entry<String,IMLoginRequest> entry: allUser.entrySet()) {
            doBroadcast(request,entry);
        }
    }

    /**
     * @param request 消息内容
     * @param destUser 目标用户
     */
    private void doBroadcast(BroadcastRequest request,Map.Entry<String,IMLoginRequest> destUser) {
        //找到目标服务器地址
        IMLoginRequest loginInfo = loginService.getLoginInfo(destUser.getKey());
        P2PChatRequest p2pReq = new P2PChatRequest();
        p2pReq.setMsg(request.getMsg());
        p2pReq.setToUserId(destUser.getKey());
        p2pReq.setFromUserId(request.getFromUserId());
        //向目标服务器发聊天请求
        ListenableFuture<ResponseEntity<ChatResponse>> future = asyncRestTemplate.postForEntity(
                Constans.HTTP_SCHEME + loginInfo.getServerHost() + ":" + loginInfo.getHttpPort() + "/p2pChat",
                new HttpEntity<P2PChatRequest>(p2pReq),ChatResponse.class);
        future.addCallback(new ListenableFutureCallback<ResponseEntity<ChatResponse>>(){
            @Override
            public void onSuccess(ResponseEntity<ChatResponse> response) {
                logger.info("广播消息已发送至用户{}",destUser.getKey());
            }

            @Override
            public void onFailure(Throwable throwable) {
                logger.info("广播消息发送至用户{}失败",destUser.getKey());
            }
        });
    }
}
