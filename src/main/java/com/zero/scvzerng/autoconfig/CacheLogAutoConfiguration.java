package com.zero.scvzerng.autoconfig;

import com.zero.scvzerng.logger.LogFilterBeanPostProcessor;
import com.zero.scvzerng.logger.CacheLogProperties;
import com.zero.scvzerng.logger.cache.CacheLogInterceptor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.AnnotationCacheOperationSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;

import javax.annotation.Resource;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Year: 2017-2017/11/18-22:53
 * Project:logger-starter
 * Package:com.yazuo.intelligent.autoconfig
 * To change this template use File | Settings | File Templates.
 */
@Configuration
@Import(LogFilterBeanPostProcessor.class)
@EnableConfigurationProperties(CacheLogProperties.class)
public class CacheLogAutoConfiguration {
    @Resource
    CacheLogProperties logProperties;
    @Bean
    @Primary
    @ConditionalOnMissingBean
    @ConditionalOnProperty(name = "spring.cache.type",havingValue = "redis")
    public CacheLogInterceptor cacheInterceptor(){
        CacheLogInterceptor interceptor = new CacheLogInterceptor(logProperties);
        interceptor.setCacheOperationSources(new AnnotationCacheOperationSource());
        return interceptor;
    }
}
