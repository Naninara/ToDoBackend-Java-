package com.crud.resotech.ExceptionHandling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ToDoExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleArgument(MethodArgumentNotValidException exception){
        Map<String,String> mp = new HashMap<>();
        exception.getBindingResult().getAllErrors().forEach((error)->{
            String key = ((FieldError)error).getField();
            String value = error.getDefaultMessage();
            mp.put(key,value);
        });
        return new ResponseEntity<>(mp,HttpStatus.BAD_REQUEST);

    }

}
