package com.yang.spring.cloud.feign.person.service.provider.web.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.ribbon.proxy.annotation.Hystrix;
import com.yang.spring.cloud.feign.api.domain.Person;
import com.yang.spring.cloud.feign.api.service.PersonService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

/**
 * {@link PersonService} 提供者控制器 (可选 实现{@link PersonService} 接口)
 */
@RestController
public class PersonServiceProviderController {
    private Map<Long, Person> persons = new ConcurrentHashMap();
    private final static Random random = new Random();
    /**
     * 保存
     *
     * @param person {@link Person}
     * @return 如果成功，<code>true</code>
     */
    @PostMapping(value = "/person/save")
    public boolean savePerson(@RequestBody Person person) {
        return persons.put(person.getId(), person) == null;
    }
    /**
     * 查找所有的服务
     *
     * @return
     */
    @GetMapping(value = "/person/find/all")
    @HystrixCommand(fallbackMethod = "fallbackForFindAllPersons", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "100")
    })
    public Collection<Person> findAllPerson() throws Exception{
        //如果随机时间大于100，那么触发容错
        int value = random.nextInt(200);
        System.out.println("findAllPerson() costs " + value + "ms.");
        Thread.sleep(value);
        return persons.values();
    }
    /**
     * {@link #findAllPerson()} Fallback方法
     *
     * @return 返回空集合
     */
    public Collection<Person> fallbackForFindAllPersons() {
        System.err.println("fallbackForFindAllPersons is invoked!");
        return Collections.emptyList();
    }
}
