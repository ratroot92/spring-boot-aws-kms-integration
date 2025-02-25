package com.example.aws_kms.advice;

import java.io.IOException;
import java.lang.reflect.Field;

import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;

import aj.org.objectweb.asm.Type;

@RestControllerAdvice
public class SampleRequestBodyAdvice implements RequestBodyAdvice {

    @Override
    public boolean supports(MethodParameter methodParameter, java.lang.reflect.Type targetType,
            Class<? extends HttpMessageConverter<?>> converterType) {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'supports'");
        return true;
    }

    @Override
    public HttpInputMessage beforeBodyRead(HttpInputMessage inputMessage, MethodParameter parameter,
            java.lang.reflect.Type targetType, Class<? extends HttpMessageConverter<?>> converterType)
            throws IOException {
                Field fields[]=inputMessage.getClass().getDeclaredFields();
                for (Field field : fields) {
                }

                return inputMessage;
    }

    @Override
    public Object afterBodyRead(Object body, HttpInputMessage inputMessage, MethodParameter parameter,
            java.lang.reflect.Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        return body;
    }

    @Override
    public Object handleEmptyBody(Object body, HttpInputMessage inputMessage, MethodParameter parameter,
            java.lang.reflect.Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
                return null;
    }
   
}
