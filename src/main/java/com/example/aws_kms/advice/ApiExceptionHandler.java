package com.example.aws_kms.advice;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.aws_kms.exceptions.DuplicateEntityException;

@RestControllerAdvice
public class ApiExceptionHandler {



    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,Object>> handleInvalidArgument(MethodArgumentNotValidException exception){
        Map<String,String> errorMap=new HashMap<>();
        exception.getBindingResult().getFieldErrors().forEach(error->{
            errorMap.put(error.getField(),error.getDefaultMessage());
        });
        Map<String,Object> responseMap=new HashMap<>();     
        responseMap.put("errors",errorMap);
        responseMap.put("httpStatusMessage",HttpStatus.UNPROCESSABLE_ENTITY.toString());
        responseMap.put("httpStatusCode",HttpStatus.UNPROCESSABLE_ENTITY.value());
        responseMap.put("exception",exception.getClass().getSimpleName());
        return  ResponseEntity.badRequest().body(responseMap);

    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DuplicateEntityException.class)
    public ResponseEntity<Map<String,Object>> handleInvalidArgument(DuplicateEntityException exception){
        Map<String,Object> responseMap=new HashMap<>();        String [] errors=new String[]{exception.getMessage()};
        responseMap.put("errors",errors);
        responseMap.put("httpStatusMessage",HttpStatus.BAD_REQUEST.toString());
        responseMap.put("httpStatusCode",HttpStatus.BAD_REQUEST.value());
        responseMap.put("exception",exception.getClass().getSimpleName());
        return  ResponseEntity.badRequest().body(responseMap);

    }


    
}
