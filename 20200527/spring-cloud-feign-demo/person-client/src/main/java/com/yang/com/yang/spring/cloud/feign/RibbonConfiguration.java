package com.yang.com.yang.spring.cloud.feign;

import com.yang.com.yang.spring.cloud.feign.client.ribbon.FirstServerForeverRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RibbonConfiguration {
    @Bean
    public FirstServerForeverRule firstServerForeverRule(){
        return new FirstServerForeverRule();
    }
}
