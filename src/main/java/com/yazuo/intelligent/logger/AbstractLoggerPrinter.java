package com.yazuo.intelligent.logger;

import com.alibaba.fastjson.serializer.PropertyFilter;
import com.yazuo.intelligent.logger.filter.HttpObjectFilter;
import com.yazuo.intelligent.logger.filter.KeyFilter;
import com.yazuo.intelligent.logger.filter.LoggerParamsFilter;
import com.yazuo.intelligent.logger.filter.ValueFilter;
import io.swagger.annotations.ApiOperation;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.AnnotationUtils;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
public  abstract class AbstractLoggerPrinter implements LoggerPrinter {
    /**
     * 过滤对象序列化时的属性
     */
    protected LoggerParamsFilter filter = new HttpObjectFilter();
    private static final Map<LoggerFilter,KeyFilter> LOG_KEY_FILTERS = new HashMap<>();
    private static final Map<LoggerFilter,ValueFilter> LOG_VALUE_FILTERS = new HashMap<>();
    private static final Map<String,LoggerFilter> CACHE_FILTERS = new HashMap<>();
    /**
     * 用于对缓存日志的去重
     */

    public AbstractLoggerPrinter(LoggerParamsFilter filter) {
        this.filter = filter;
    }



    @Override
    public void addFilter( LoggerFilter filter,Annotation cache) {
            LOG_KEY_FILTERS.put(filter,new KeyFilter(Arrays.asList(filter.keys())));
            LOG_VALUE_FILTERS.put(filter,new ValueFilter(Arrays.asList(filter.types())));
            if(cache!=null){
                String[] cacheNames = (String[]) AnnotationUtils.getAnnotationAttributes(cache).get("value");
                Arrays.stream(cacheNames).forEach(name-> CACHE_FILTERS.put(name,filter));
            }

    }

    @Override
    public PropertyFilter getKeyFilter(LoggerFilter filter) {
        return LOG_KEY_FILTERS.get(filter);
    }

    @Override
    public PropertyFilter getValueFilter(LoggerFilter filter) {
        return LOG_VALUE_FILTERS.get(filter);
    }

    protected String getApiName(MethodSignature methodSignature){

        ApiOperation api = methodSignature.getMethod().getAnnotation(ApiOperation.class);
        return api==null?"":api.value();
    }

    protected LoggerFilter getLoggerFilter(MethodSignature methodSignature){
        return methodSignature.getMethod().getAnnotation(LoggerFilter.class);
    }

    @Override
    public LoggerFilter getLoggerFilter(String cacheName) {
        return CACHE_FILTERS.get(cacheName);
    }
}
