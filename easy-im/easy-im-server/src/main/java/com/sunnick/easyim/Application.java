package com.sunnick.easyim;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by Sunnick on 2019/1/29/029.
 */
@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(basePackages = {"com.sunnick.easyim"})
public class Application {

    public static void main(String[] args){

        SpringApplication.run(Application.class,args);

//        //启动netty服务
//        Server.start();
    }
}
