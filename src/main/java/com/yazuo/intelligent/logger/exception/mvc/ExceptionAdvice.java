package com.yazuo.intelligent.logger.exception.mvc;

import com.yazuo.intelligent.logger.exception.BusinessException;
import com.yazuo.intelligent.logger.exception.builder.ErrorResultBuilder;
import com.yazuo.intelligent.logger.exception.response.ErrorResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.HandlerMethod;

import javax.annotation.Resource;

@RestControllerAdvice
@Slf4j
public class ExceptionAdvice  {
    @Resource
    private Tracer tracer;
    @Resource
    private ErrorResultBuilder errorResultBuilder;

    @ExceptionHandler(BusinessException.class)
    public ErrorResult processRestError(BusinessException exception, HandlerMethod handler){
        return errorResultBuilder.build(exception,tracer);
    }
    @ExceptionHandler(Exception.class)
    public ErrorResult processSysError(Exception e,HandlerMethod handler){
        return errorResultBuilder.build(e,tracer);
    }


}
