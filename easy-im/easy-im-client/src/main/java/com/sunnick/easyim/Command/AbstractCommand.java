package com.sunnick.easyim.Command;

import com.sunnick.easyim.client.ClientRunner;
import com.sunnick.easyim.util.SpringBeanFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Sunnick on 2019/2/19/019.
 */
public class AbstractCommand {
    private RestTemplate restTemplate;
    private ClientRunner clientRunner;

    protected AbstractCommand(){
        restTemplate = SpringBeanFactory.getBean(RestTemplate.class);
        clientRunner = SpringBeanFactory.getBean(ClientRunner.class);
    }

    public RestTemplate getRestTemplate() {
        return restTemplate;
    }

    public ClientRunner getClientRunner() {
        return clientRunner;
    }
}
