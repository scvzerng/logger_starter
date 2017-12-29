package com.yazuo.intelligent.exception.builder;

import com.yazuo.intelligent.common.exception.AbstractException;
import com.yazuo.intelligent.common.response.GenericResponse;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.sleuth.Tracer;

public interface ResultBuilder {
   GenericResponse<?> buildError(ApiOperation apiOperation, AbstractException exception, Tracer tracer);
   GenericResponse<?> buildError( ApiOperation apiOperation,Exception exception, Tracer tracer);
   GenericResponse<?> buildSuccess(Object obj , ApiOperation apiOperation);
}
