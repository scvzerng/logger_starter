package com.yazuo.intelligent.exception.mvc;

import com.yazuo.intelligent.common.exception.BusinessException;
import com.yazuo.intelligent.common.response.GenericResponse;
import com.yazuo.intelligent.exception.builder.ResultBuilder;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.HandlerMethod;

import javax.annotation.Resource;

import static java.util.stream.Collectors.toList;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

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
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public GenericResponse<Object> processValidateException(MethodArgumentNotValidException e){
        GenericResponse<Object> response = new GenericResponse<>();
        response.setCode(BAD_REQUEST.value());
        response.setMessage("参数校验失败");
        response.setDetails(
                e.getBindingResult().
                        getFieldErrors()
                        .stream()
                        .map(fieldError ->
                                new GenericResponse.FieldError(
                                        fieldError.getField(),
                                        fieldError.getDefaultMessage(),
                                        fieldError.getRejectedValue()
                                        )

                        ).collect(toList()));
        return response;
    }


}
