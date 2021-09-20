package com.kurodevs.app.springbootawsdynamodb.controllers;

import java.util.List;

import com.kurodevs.app.springbootawsdynamodb.models.entity.Person;
import com.kurodevs.app.springbootawsdynamodb.models.repository.PersonRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class PersonController {
    
    @Autowired
    private PersonRepository personRepository;

    @GetMapping("/all")
    public List<Person> findAll(){
        return personRepository.findAll();
    }
    @PostMapping("/create")
    public Person postMethodName(@RequestBody Person person) {
        return personRepository.save(person);
    }
    
}
