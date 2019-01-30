package com.sunnick.easyim;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Sunnick on 2019/1/28/028.
 */
@SpringBootApplication
@EnableEurekaClient
@ComponentScan(basePackages = {"com.sunnick.easyim"})
public class App {

    @Bean
    @LoadBalanced
    RestTemplate restTemplate(){
        return new RestTemplate();
    }
    public static void main(String[] args){
        SpringApplication.run(App.class,args);
    }
}
