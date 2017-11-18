package com.yazuo.intelligent.autoconfig;

import com.yazuo.intelligent.logger.cache.CacheLogInterceptor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cache.annotation.AnnotationCacheOperationSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Year: 2017-2017/11/18-22:53
 * Project:logger-starter
 * Package:com.yazuo.intelligent.autoconfig
 * To change this template use File | Settings | File Templates.
 */
@Configuration
public class CacheLogAutoConfiguration {

    @Bean
    @Primary
    @ConditionalOnMissingBean
    public CacheLogInterceptor cacheInterceptor(){
        CacheLogInterceptor interceptor = new CacheLogInterceptor();
        interceptor.setCacheOperationSources(new AnnotationCacheOperationSource());
        return interceptor;
    }
}
