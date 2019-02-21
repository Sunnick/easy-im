package com.sunnick.easyim.controller;

import com.sunnick.easyim.entity.IMServerInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.netflix.eureka.EurekaClientConfigBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import static com.sunnick.easyim.constans.Constans.HTTP_SCHEME;
import static com.sunnick.easyim.constans.Constans.IM_SERVER_NAME;

/**
 * Created by Sunnick on 2019/1/28/028.
 */

@Controller
public class ServerInfoController {

    private static Logger logger = LoggerFactory.getLogger(ServerInfoController.class);

    @Autowired
    @Qualifier("ribbonRestTemplate")
    private RestTemplate ribbonRestTemplate;

    @RequestMapping("/getServerInfo")
    @ResponseBody
    public String getServiceInfo() {
        IMServerInfo serverInfo = ribbonRestTemplate.getForObject( "http://EASY-IM-SERVER/getServerInfo",IMServerInfo.class);
        logger.info("返回给客户端：{}",serverInfo);
        return serverInfo.toString();
    }

}
