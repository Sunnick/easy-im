package com.sunnick.easyim.controller;

import com.alibaba.fastjson.JSON;
import com.sunnick.easyim.Server;
import com.sunnick.easyim.entity.IMServerInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
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
public class ServerInfoController implements ApplicationRunner {

    private static Logger logger = LoggerFactory.getLogger(ServerInfoController.class);

    private IMServerInfo serverInfo;

    @Value("${easyim.server.port}")
    private int nettyPort;

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        Server.initValues(nettyPort);
        Server.start();
        serverInfo = new IMServerInfo();
        serverInfo.setHost(InetAddress.getLocalHost().getHostAddress());
        serverInfo.setPort(nettyPort);
    }

    @RequestMapping("/getServerInfo")
    @ResponseBody
    public IMServerInfo getServiceInfo() {
        return this.serverInfo;
    }

}
