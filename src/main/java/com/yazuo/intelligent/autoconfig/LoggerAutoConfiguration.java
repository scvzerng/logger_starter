package com.yazuo.intelligent.autoconfig;

import com.alibaba.fastjson.JSON;
import com.yazuo.intelligent.logger.*;
import com.yazuo.intelligent.logger.aop.LogAnnotationMethodBeanFactoryAwareAdvisingPostProcessor;
import com.yazuo.intelligent.logger.aop.LogMethodInterceptor;
import com.yazuo.intelligent.logger.filter.HttpObjectFilter;
import com.yazuo.intelligent.logger.filter.LoggerParamsFilter;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass({Logger.class, JSON.class, ApiOperation.class})
@EnableConfigurationProperties(LogProperties.class)
@Slf4j
public class LoggerAutoConfiguration {
    @Bean
    @ConditionalOnMissingBean
    public ErrorLogger errorLogger(LoggerParamsFilter loggerParamsFilter){
        return new DefaultErrorLogger(loggerParamsFilter);
    }
    @Bean
    @ConditionalOnMissingBean
    public InfoLogger infoLogger(LoggerParamsFilter loggerParamsFilter){
        return new DefaultInfoLogger(loggerParamsFilter);
    }
    @Bean
    @ConditionalOnMissingBean
    public LoggerParamsFilter loggerParamsFilter(){
        return new HttpObjectFilter();
    }


    @Bean
    @ConditionalOnMissingBean
    public LogAnnotationMethodBeanFactoryAwareAdvisingPostProcessor logAnnotationMethodBeanFactoryAwareAdvisingPostProcessor(){
        return new LogAnnotationMethodBeanFactoryAwareAdvisingPostProcessor();
    }

    @Bean
    @ConditionalOnMissingBean
    public LogMethodInterceptor logMethodInterceptor(){
        return new LogMethodInterceptor();
    }


}
