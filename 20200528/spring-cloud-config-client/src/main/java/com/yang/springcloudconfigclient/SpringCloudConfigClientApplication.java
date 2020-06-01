package com.yang.springcloudconfigclient;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.bootstrap.config.PropertySourceLocator;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.PropertySource;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class SpringCloudConfigClientApplication {

    public static void main(String[] args) {

        SpringApplication.run(SpringCloudConfigClientApplication.class, args);
    }
    @Configuration
    public static class CustomPropertySourceLocator implements PropertySourceLocator {
        @Override
        @Order(Ordered.HIGHEST_PRECEDENCE)
        public PropertySource<?> locate(Environment environment) {
            Map<String,Object> source = new HashMap<>();
            source.put("server.port","8083");
            MapPropertySource propertySource = new MapPropertySource("my-property-source",source);
            return propertySource;
        }


    }

}
