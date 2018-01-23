package com.zero.scvzerng.autoconfig;

import com.zero.scvzerng.exception.builder.RestResultBuilder;
import com.zero.scvzerng.exception.builder.ResultBuilder;
import com.zero.scvzerng.validation.ParameterNameValidationMethodValidationPostProcessor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.Servlet;

@Configuration
@ConditionalOnWebApplication
@ConditionalOnClass(value = {Servlet.class, RestControllerAdvice.class})
public class ExceptionAutoConfiguration {
    @Bean
    @ConditionalOnMissingBean
    public ResultBuilder errorResultBuilder(){
        return new RestResultBuilder();
    }
    @Bean
    public MethodValidationPostProcessor methodValidationPostProcessor(){
        return new ParameterNameValidationMethodValidationPostProcessor(Validated.class);
    }
}
