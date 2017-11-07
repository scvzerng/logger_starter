package com.yazuo.intelligent.logger.exception.autoconfig;

import com.yazuo.intelligent.logger.exception.builder.ErrorResultBuilder;
import com.yazuo.intelligent.logger.exception.builder.RestErrorResultBuilder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.Servlet;

@Configuration
@ConditionalOnWebApplication
@ConditionalOnClass(value = {Servlet.class, RestControllerAdvice.class})
public class ExceptionAutoConfiguration {
    @Bean
    @ConditionalOnMissingBean
    public ErrorResultBuilder errorResultBuilder(){
        return new RestErrorResultBuilder();
    }
}
