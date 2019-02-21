package com.sunnick.easyim.controller;

import com.sunnick.easyim.Server;
import com.sunnick.easyim.entity.IMServerInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.InetAddress;

/**
 * Created by Sunnick on 2019/1/28/028.
 */

@Controller
public class ServerInfoController implements ApplicationRunner {

    private static Logger logger = LoggerFactory.getLogger(ServerInfoController.class);

    private IMServerInfo serverInfo;

    @Value("${easyim.server.nettyPort}")
    private int nettyPort;

    @Value("${server.port}")
    private int httpPort;

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        Server.initValues(nettyPort);
        Server.start();
        serverInfo = new IMServerInfo();
        serverInfo.setHost(InetAddress.getLocalHost().getHostAddress());
        serverInfo.setNettyPort(nettyPort);
        serverInfo.setHttpPort(httpPort);
    }

    @RequestMapping("/getServerInfo")
    @ResponseBody
    public IMServerInfo getServiceInfo() {
        return this.serverInfo;
    }

}
