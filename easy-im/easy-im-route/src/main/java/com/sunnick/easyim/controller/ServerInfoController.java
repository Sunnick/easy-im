package com.sunnick.easyim.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
    private RestTemplate template;

    @RequestMapping("/getServerInfo")
    @ResponseBody
    public String getServiceInfo() {
        String result = template.getForEntity(HTTP_SCHEME + IM_SERVER_NAME + "/getServerInfo",String.class).getBody();
        logger.info("返回给客户端：{}",result);
        return result;
    }

}
