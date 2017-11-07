package com.yazuo.intelligent.logger.exception.builder;

import com.yazuo.intelligent.logger.exception.AbstractException;
import com.yazuo.intelligent.logger.exception.response.ErrorResult;
import com.yazuo.intelligent.logger.exception.response.SuccessResult;
import com.yazuo.intelligent.logger.exception.utils.ExceptionUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.sleuth.Tracer;

import java.util.function.Consumer;

public class RestResultBuilder implements ResultBuilder {

    @Override
    public ErrorResult buildError(AbstractException exception, Tracer tracer) {
        return createFromException(exception,errorResult -> {
            errorResult.setTradeId(tracer.getCurrentSpan().traceIdString());
            errorResult.setCode(exception.getCode());
        });

    }

    @Override
    public ErrorResult buildError(Exception exception, Tracer tracer) {
        return createFromException(exception,errorResult -> {
            errorResult.setTradeId(tracer.getCurrentSpan().traceIdString());
            errorResult.setCode(500);
        });
    }

    @Override
    public SuccessResult<Object> buildSuccess(Object obj, ApiOperation apiOperation) {
        SuccessResult<Object> successResult = new SuccessResult<>();
        successResult.setData(obj);
        successResult.setMessage(apiOperation.value()+"成功");
        return successResult;
    }

    private ErrorResult createFromException( Exception exception, Consumer<ErrorResult> afterProcess){
        ErrorResult result = new ErrorResult();
        result.setMessage(exception.getMessage());
        result.setStackInfo(ExceptionUtils.exceptionToString(exception));
        if(afterProcess!=null){
            afterProcess.accept(result);
        }
        return result;
    }
}
