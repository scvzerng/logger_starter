package com.yazuo.intelligent.logger;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.annotation.AnnotationUtils;

import javax.annotation.Resource;
import java.lang.annotation.Annotation;
import java.util.Arrays;

public class LogFilterBeanPostProcessor implements BeanPostProcessor {
    @Resource
    private InfoLogger infoLogger;

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Arrays.stream(bean.getClass().getMethods())
                .filter(method -> method.isAnnotationPresent(LoggerFilter.class))
                .forEach(method -> {
                    LoggerFilter filter = method.getAnnotation(LoggerFilter.class);
                    infoLogger.addFilter(filter,method.isAnnotationPresent(Cacheable.class)?method.getAnnotation( Cacheable.class):method.getAnnotation(CachePut.class));
                });
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
