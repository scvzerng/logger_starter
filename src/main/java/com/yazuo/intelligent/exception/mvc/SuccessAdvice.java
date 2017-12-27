package com.yazuo.intelligent.exception.mvc;

import com.yazuo.intelligent.exception.builder.ResultBuilder;
import io.swagger.annotations.ApiOperation;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.annotation.Resource;

@RestControllerAdvice(annotations = RestController.class)
public class SuccessAdvice implements ResponseBodyAdvice<Object> {

    @Resource
    ResultBuilder resultBuilder;

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return returnType.getMethod().getAnnotation(ApiOperation.class)!=null;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {

        return resultBuilder.buildSuccess(body,returnType.getMethod().getAnnotation(ApiOperation.class));
    }
}
