package com.sunnick.easyim.client;

import com.sunnick.easyim.Client;
import com.sunnick.easyim.constans.Constans;
import com.sunnick.easyim.entity.IMServerInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Sunnick on 2019/1/30/030.
 * 继承ApplicationRunner，实现开机后启动
 */
@Component
public class ClientRunner implements ApplicationRunner {

    private Logger logger = LoggerFactory.getLogger(ClientRunner.class);

    @Value("${easyim.client.userid}")
    private String userid;

    @Value("${easyim.client.username}")
    private String username;

    @Value("${easyim.route.host}")
    private String routeHost;

    @Value("${easyim.route.port}")
    private int routePort;

    @Autowired
    RestTemplate template;

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        //1. 从route获取server信息
        IMServerInfo serverInfo = getServerInfoFromRoute(routeHost,routePort);

        //2. 与server建立长连接
        connectToServer(serverInfo);
    }

    private IMServerInfo getServerInfoFromRoute(String routeHost, int routePort) {
        if (StringUtils.isEmpty(routeHost) || StringUtils.isEmpty(routePort)){
            logger.error("请在配置文件中配置easyim.route.host和easyim.route.port");
            throw new RuntimeException("请在配置文件中配置easyim.route.host和easyim.route.port!");
        }
        IMServerInfo serverInfo = template.getForObject(Constans.HTTP_SCHEME + routeHost + ":" + routePort + "/getServerInfo",IMServerInfo.class);
        logger.info("IMServer信息为：{}",serverInfo);
        return serverInfo;
    }

    private void connectToServer(IMServerInfo serverInfo) {
        Client.initValues(userid,username,serverInfo.getHost(),serverInfo.getPort());
        Client.start();
    }
}
