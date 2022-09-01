package com.example.exception.controller;

import com.example.exception.dto.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
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

    //GlobalExceptionHandler과 다르게 해당 코드가 있는 부분의 오류만 확인이 가능하. 우선순위가 GlobalExceptionHandler보다 높다
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity methodArgumentNotValidException(MethodArgumentNotValidException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

}
