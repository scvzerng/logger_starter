package com.yazuo.intelligent.logger.exception.mvc;

import com.yazuo.intelligent.logger.exception.BusinessException;
import com.yazuo.intelligent.logger.exception.builder.ResultBuilder;
import com.yazuo.intelligent.logger.exception.response.ErrorResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.annotation.Resource;

@RestControllerAdvice
@Slf4j
public class ExceptionAdvice  {
    @Resource
    private Tracer tracer;
    @Resource
    private ResultBuilder resultBuilder;

    @ExceptionHandler(BusinessException.class)
    public ErrorResult processRestError(BusinessException exception){
        return resultBuilder.buildError(exception,tracer);
    }
    @ExceptionHandler(Exception.class)
    public ErrorResult processSysError(Exception e){
        return resultBuilder.buildError(e,tracer);
    }


}
