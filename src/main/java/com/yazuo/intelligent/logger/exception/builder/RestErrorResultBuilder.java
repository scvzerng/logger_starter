package com.yazuo.intelligent.logger.exception.builder;

import com.yazuo.intelligent.exception.AbstractException;
import com.yazuo.intelligent.exception.response.ErrorResult;
import com.yazuo.intelligent.exception.utils.ExceptionUtils;
import org.springframework.cloud.sleuth.Tracer;

import java.util.function.Consumer;

public class RestErrorResultBuilder implements ErrorResultBuilder {

    @Override
    public ErrorResult build(AbstractException exception, Tracer tracer) {
        return createFromException(exception,errorResult -> {
            errorResult.setTradeId(tracer.getCurrentSpan().traceIdString());
            errorResult.setCode(exception.getCode());
        });

    }

    @Override
    public ErrorResult build(Exception exception, Tracer tracer) {
        return createFromException(exception,errorResult -> {
            errorResult.setTradeId(tracer.getCurrentSpan().traceIdString());
            errorResult.setCode(500);
        });
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
