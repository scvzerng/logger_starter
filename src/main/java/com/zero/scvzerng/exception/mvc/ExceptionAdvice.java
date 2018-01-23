package com.zero.scvzerng.exception.mvc;

import com.yazuo.intelligent.common.exception.BusinessException;
import com.yazuo.intelligent.common.response.GenericResponse;
import com.zero.scvzerng.exception.builder.ResultBuilder;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.HandlerMethod;

import javax.annotation.Resource;
import javax.validation.ConstraintViolationException;

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
    @ExceptionHandler({
            MethodArgumentNotValidException.class,
            ConstraintViolationException.class,
            BindException.class
           })
    public GenericResponse<Object> processValidateException(Exception e){
        GenericResponse<Object> response = new GenericResponse<>();
        response.setCode(BAD_REQUEST.value());
        response.setMessage("参数校验失败");
        if(e instanceof MethodArgumentNotValidException||e instanceof BindException){
            BindingResult bindingResult = null;
            if(e instanceof MethodArgumentNotValidException){
                bindingResult = ((MethodArgumentNotValidException) e).getBindingResult();
            }

            if(e instanceof BindException){
                bindingResult = ((BindException) e).getBindingResult();
            }
            response.setDetails(
                    bindingResult.
                            getFieldErrors()
                            .stream()
                            .map(fieldError ->
                                    new GenericResponse.FieldError(
                                            fieldError.getField(),
                                            fieldError.getDefaultMessage(),
                                            fieldError.getRejectedValue()
                                    )

                            ).collect(toList()));
        }

        if(e instanceof ConstraintViolationException){
            ConstraintViolationException violationException = (ConstraintViolationException) e;
            response.setDetails( violationException.getConstraintViolations()
                    .stream()
                    .map(constraintViolation ->
                            new GenericResponse.FieldError(
                                    constraintViolation.getPropertyPath().toString(),
                                    constraintViolation.getMessage(),

                                    constraintViolation.getInvalidValue()
                            ))
                    .collect(toList()));

        }

        return response;
    }


}
