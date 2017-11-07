package com.yazuo.intelligent.logger.exception.builder;

import com.yazuo.intelligent.logger.exception.AbstractException;
import com.yazuo.intelligent.logger.exception.response.ErrorResult;
import org.springframework.cloud.sleuth.Tracer;

public interface ErrorResultBuilder {
   ErrorResult build(AbstractException exception, Tracer tracer);

   ErrorResult build(Exception exception, Tracer tracer);
}
