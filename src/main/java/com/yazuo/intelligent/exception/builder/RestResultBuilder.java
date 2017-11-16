package com.yazuo.intelligent.exception.builder;

import com.yazuo.intelligent.exception.AbstractException;
import com.yazuo.intelligent.exception.response.ErrorResult;
import com.yazuo.intelligent.exception.response.SuccessResult;
import com.yazuo.intelligent.exception.utils.ExceptionUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.sleuth.Tracer;

import java.util.function.Consumer;

public class RestResultBuilder implements ResultBuilder {

    @Override
    public ErrorResult buildError( ApiOperation apiOperation,AbstractException exception, Tracer tracer) {
        return createFromException(tracer,apiOperation,exception);

    }

    @Override
    public ErrorResult buildError( ApiOperation apiOperation,Exception exception, Tracer tracer) {
        return createFromException(tracer,apiOperation,exception);
    }

    @Override
    public SuccessResult<Object> buildSuccess(Object obj, ApiOperation apiOperation) {
        SuccessResult<Object> successResult = new SuccessResult<>();
        successResult.setData(obj);
        successResult.setMessage(apiOperation.value()+"成功");
        return successResult;
    }

    private ErrorResult createFromException( Tracer tracer, ApiOperation apiOperation, Exception exception){
        ErrorResult result = new ErrorResult();
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
