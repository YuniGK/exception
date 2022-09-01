package com.example.exception.controller;

import com.example.exception.dto.User;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class ApiController {

    @GetMapping("")
    //required = false 해당값이 없어도 동작한다.
    public User get(@RequestParam(required = false) String name, @RequestParam int age){
        User user = new User();

        user.setName(name);
        user.setAge(age);

        return user;
    }

    @PostMapping("")
    public User post(@Valid @RequestBody User user){
        System.out.println("user = " + user);
        return user;
    }

}
