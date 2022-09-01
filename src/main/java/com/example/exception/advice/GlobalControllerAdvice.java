package com.example.exception.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.el.MethodNotFoundException;

/*
전체적인 오류를 모두 잡는다.

@ControllerAdvice - 예외처리 시 사용한다.
@RestControllerAdvice - @ControllerAdvice와 @ResponseBody를 합쳐 내용이다.
단순히 예외만 처리하고 싶다면 @ControllerAdvice를 적용하면 되고, 응답으로 객체를 리턴해야 한다면 @RestControllerAdvice를 적용
*/
@RestControllerAdvice
public class GlobalControllerAdvice {

    //ExceptionHandler - Controller계층에서 발생하는 에러를 잡아서 메서드로 처리해주는 기능, Service, Repository에서 발생하는 에러는 제외
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity exception(Exception e){
        System.out.println("name = " + e.getClass().getName());
        System.out.println("e = " + e.getLocalizedMessage());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("");
    }

    //특정 메서드의 예외를 확인할 때
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity methodArgumentNotValidException(MethodArgumentNotValidException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

}
