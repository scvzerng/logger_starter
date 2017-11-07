package com.yazuo.intelligent.logger.exception.mvc;

import com.alibaba.fastjson.JSONObject;
import com.yazuo.intelligent.logger.exception.builder.ResultBuilder;
import io.swagger.annotations.ApiOperation;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.annotation.Resource;

@RestControllerAdvice
public class SuccessAdvice implements ResponseBodyAdvice<Object> {

    @Resource
    ResultBuilder resultBuilder;
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        return resultBuilder.buildSuccess(body,returnType.getMethod().getAnnotation(ApiOperation.class));
    }
}
