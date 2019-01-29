package com.sunnick.easyim;

import com.sunnick.easyim.entity.IMServerInfo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Sunnick on 2019/1/29/029.
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.sunnick.easyim"})
public class App {

    @Bean
    RestTemplate restTemplate(){
        return new RestTemplate();
    }
    public static void main(String[] args){
        SpringApplication.run(App.class,args);
        Client.start();
    }

}
