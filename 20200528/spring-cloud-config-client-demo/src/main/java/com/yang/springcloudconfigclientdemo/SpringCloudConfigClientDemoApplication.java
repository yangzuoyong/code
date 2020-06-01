package com.yang.springcloudconfigclientdemo;

import com.yang.springcloudconfigclientdemo.health.MyHealthIndicator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.refresh.ContextRefresher;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Set;

@SpringBootApplication
public class SpringCloudConfigClientDemoApplication {
    private final ContextRefresher contextRefresher;
    private final Environment environment;
    @Autowired
    public SpringCloudConfigClientDemoApplication(ContextRefresher contextRefresher, Environment environment) {
        this.contextRefresher = contextRefresher;
        this.environment = environment;
    }
    public static void main(String[] args) {
        SpringApplication.run(SpringCloudConfigClientDemoApplication.class, args);
    }
    @Scheduled(fixedRate = 5 * 1000, initialDelay = 3 * 1000)
    public void autoRefresh() {
        Set<String> updatedPropertyNames = contextRefresher.refresh();
        updatedPropertyNames.forEach(propertyName -> System.err.printf("[Thread :%s]当前配置已更新，具体Key：%s, value :%s\n",
                Thread.currentThread().getName(), propertyName, environment.getProperty(propertyName)));
        if (!updatedPropertyNames.isEmpty()) {
            System.out.printf("[Thread :%s]当前配置已更新，具体项目：%s \n",
                    Thread.currentThread().getName(), updatedPropertyNames);
        }
    }
    @Bean
    public MyHealthIndicator myHealthIndicator(){
        return new MyHealthIndicator();
    }
    public void onNext(Object value){

    }
    public void onComplete(Object value){

    }
    public void onError(Throwable throwable){

    }
}
