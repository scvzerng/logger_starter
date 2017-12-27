package com.yazuo.intelligent.exception.mvc;

import com.yazuo.intelligent.common.exception.BusinessException;
import com.yazuo.intelligent.common.response.GenericResponse;
import com.yazuo.intelligent.exception.builder.ResultBuilder;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.HandlerMethod;

import javax.annotation.Resource;

@RestControllerAdvice(annotations = RestController.class)
public class ExceptionAdvice  {
    @Resource
    private Tracer tracer;
    @Resource
    private ResultBuilder resultBuilder;

    @ExceptionHandler(BusinessException.class)
    public GenericResponse<?> processRestError(BusinessException exception, HandlerMethod method){
        return resultBuilder.buildError(method.getMethodAnnotation(ApiOperation.class),exception,tracer);
    }
    @ExceptionHandler(Exception.class)
    public GenericResponse<?> processSysError(Exception e,HandlerMethod method){
        return resultBuilder.buildError(method.getMethodAnnotation(ApiOperation.class),e,tracer);
    }


}
