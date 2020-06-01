package com.yang.com.yang.spring.cloud.feign.client.web.controller;

import com.yang.spring.cloud.feign.api.domain.Person;
import com.yang.spring.cloud.feign.api.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * {@link PersonClientController} 实现 {@link PersonService}
 */
@RestController
public class PersonClientController implements PersonService{

    private final PersonService personService;
    @Autowired
    public PersonClientController(PersonService personService){
        this.personService=personService;
    }

    @Override
    public boolean save(@RequestBody Person person) {
        return personService.save(person);
    }

    @Override
    public Collection<Person> findAll() {
        return personService.findAll();
    }
}
