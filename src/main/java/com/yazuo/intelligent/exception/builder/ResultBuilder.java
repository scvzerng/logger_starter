package com.yazuo.intelligent.exception.builder;

import com.yazuo.intelligent.exception.AbstractException;
import com.yazuo.intelligent.exception.response.ErrorResult;
import com.yazuo.intelligent.exception.response.SuccessResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.sleuth.Tracer;

public interface ResultBuilder {
   ErrorResult buildError(AbstractException exception, Tracer tracer);

   ErrorResult buildError(Exception exception, Tracer tracer);
   SuccessResult<Object> buildSuccess(Object obj , ApiOperation apiOperation);
}
