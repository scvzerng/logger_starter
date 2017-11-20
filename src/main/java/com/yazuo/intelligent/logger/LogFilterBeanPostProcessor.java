package com.yazuo.intelligent.logger;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import javax.annotation.Resource;
import java.util.Arrays;

public class LogFilterBeanPostProcessor implements BeanPostProcessor {
    @Resource
    InfoLogger infoLogger;

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Arrays.stream(bean.getClass().getMethods())
                .filter(method -> method.isAnnotationPresent(LoggerFilter.class)&&method.isAnnotationPresent(ApiOperation.class))
                .forEach(method -> {
                    ApiOperation operation = method.getAnnotation(ApiOperation.class);
                    LoggerFilter filter = method.getAnnotation(LoggerFilter.class);
                    infoLogger.addFilter(operation,filter);
                });
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
