package com.yang.spring.cloud.feign.api.service;

import com.yang.spring.cloud.feign.api.domain.Person;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Collection;

/**
 * {@link Person} 服务
 */
@FeignClient(value = "person-service")// 服务提供方应用的名称
public interface PersonService {
    /**
     * 保存
     * @param person {@link Person}
     * @return 如果成功，<code>true</code>
     */
    @PostMapping(value = "/person/save")
    boolean save(@RequestBody Person person);

    /**
     * 查找所有的服务
     * @return
     */
    @GetMapping(value = "/person/find/all")
    Collection<Person> findAll();
}
