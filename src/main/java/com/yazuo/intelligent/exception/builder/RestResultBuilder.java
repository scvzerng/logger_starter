package com.yazuo.intelligent.exception.builder;

import com.yazuo.intelligent.exception.AbstractException;
import com.yazuo.intelligent.exception.response.GenericResponse;
import com.yazuo.intelligent.exception.utils.ExceptionUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.http.HttpStatus;

public class RestResultBuilder implements ResultBuilder {

    @Override
    public GenericResponse<?> buildError(ApiOperation apiOperation, AbstractException exception, Tracer tracer) {
        return createFromException(tracer,apiOperation,exception);

    }

    @Override
    public GenericResponse<?> buildError( ApiOperation apiOperation,Exception exception, Tracer tracer) {
        return createFromException(tracer,apiOperation,exception);
    }

    @Override
    public GenericResponse<?> buildSuccess(Object obj, ApiOperation apiOperation) {
        GenericResponse<Object> successResult=obj instanceof GenericResponse?(GenericResponse<Object>) obj:new GenericResponse<>();
        if(successResult.getCode()<=0){
            successResult.setCode(HttpStatus.OK.value());
        }
        if(successResult.getMessage()==null){
            successResult.setMessage(apiOperation.value()+"成功");
        }
        if(successResult.getData()==null){
            if(!(obj instanceof GenericResponse)){
                successResult.setData(obj);
            }
        }
        return successResult;
    }

    private GenericResponse<?> createFromException( Tracer tracer, ApiOperation apiOperation, Exception exception){
        GenericResponse<Object> result = new GenericResponse<>();
        StringBuilder message = new StringBuilder(apiOperation.value()+"失败");
        if(exception instanceof AbstractException){
             message.append(":")
                    .append(exception.getMessage());
             result.setCode(((AbstractException) exception).getCode());
        }
        result.setMessage(message.toString());
        result.setTradeId(tracer.getCurrentSpan().traceIdString());
        result.setStackInfo(ExceptionUtils.exceptionToString(exception));
        return result;
    }
}
