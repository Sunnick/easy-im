package com.sunnick.easyim.controller;

import com.alibaba.fastjson.JSON;
import com.sunnick.easyim.entity.IMServerInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.EmbeddedServletContainerInitializedEvent;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by Sunnick on 2019/1/28/028.
 */

@Controller
public class ServerInfoController implements ApplicationListener<EmbeddedServletContainerInitializedEvent> {

    private static Logger logger = LoggerFactory.getLogger(ServerInfoController.class);

    private IMServerInfo serverInfo;

    @Override
    public void onApplicationEvent(EmbeddedServletContainerInitializedEvent event) {
        try {
            this.serverInfo = new IMServerInfo();
            this.serverInfo.setPort(event.getEmbeddedServletContainer().getPort());
            this.serverInfo.setHost(InetAddress.getLocalHost().getHostAddress());
        } catch (UnknownHostException e) {
            e.printStackTrace();
            logger.info("ip和端口获取失败，即将退出...");
            System.exit(0);
        }
    }

    @Autowired
    private DiscoveryClient client;

    @RequestMapping("/getServerInfo")
    @ResponseBody
    public String getServiceInfo() {
        return JSON.toJSONString(this.serverInfo);
    }

}
