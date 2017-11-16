package com.yazuo.intelligent.exception.mvc;

import com.yazuo.intelligent.exception.BusinessException;
import com.yazuo.intelligent.exception.builder.ResultBuilder;
import com.yazuo.intelligent.exception.response.ErrorResult;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.annotation.Resource;

@RestControllerAdvice(annotations = RestController.class)
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
