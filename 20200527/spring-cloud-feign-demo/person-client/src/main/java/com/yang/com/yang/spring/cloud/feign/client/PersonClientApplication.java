package com.yang.com.yang.spring.cloud.feign.client;

import com.yang.com.yang.spring.cloud.feign.RibbonConfiguration;
import com.yang.com.yang.spring.cloud.feign.client.ribbon.FirstServerForeverRule;
import com.yang.spring.cloud.feign.api.service.PersonService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

/**
 * Person Client 应用程序
 */
@SpringBootApplication
//@EnableEurekaClient
@EnableFeignClients(clients = PersonService.class)
@RibbonClient(value = "person-service",configuration = RibbonConfiguration.class)
public class PersonClientApplication {
    public static void main(String[] args) {
        SpringApplication.run(PersonClientApplication.class,args);
    }
}
