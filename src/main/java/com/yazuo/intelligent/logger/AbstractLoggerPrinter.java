package com.yazuo.intelligent.logger;

import com.alibaba.fastjson.serializer.PropertyFilter;
import com.yazuo.intelligent.logger.filter.HttpObjectFilter;
import com.yazuo.intelligent.logger.filter.KeyFilter;
import com.yazuo.intelligent.logger.filter.LoggerParamsFilter;
import com.yazuo.intelligent.logger.filter.ValueFilter;
import io.swagger.annotations.ApiOperation;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.AnnotationUtils;

import javax.annotation.Resource;
import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
public  abstract class AbstractLoggerPrinter implements LoggerPrinter {
    @Resource
    LogProperties logProperties;
    /**
     * 过滤对象序列化时的属性
     */
    protected LoggerParamsFilter filter = new HttpObjectFilter();
    private static final Map<Annotation,KeyFilter> LOG_KEY_FILTERS = new HashMap<>();
    private static final Map<Annotation,ValueFilter> LOG_VALUE_FILTERS = new HashMap<>();
    private static final Map<String,Annotation> CACHE_FILTERS = new HashMap<>();
    /**
     * 用于对缓存日志的去重
     */

    public AbstractLoggerPrinter(LoggerParamsFilter filter) {
        this.filter = filter;
    }



    @Override
    public void addFilter( Annotation filter,Annotation cache) {
            Map<String,Object> annotationAttributes = AnnotationUtils.getAnnotationAttributes(filter);
            LOG_KEY_FILTERS.put(filter,new KeyFilter(Arrays.asList((String[])annotationAttributes.get("keys"))));
            LOG_VALUE_FILTERS.put(filter,new ValueFilter(Arrays.asList((Class[])annotationAttributes.get("types"))));
            if(cache!=null){
                String[] cacheNames = (String[]) AnnotationUtils.getAnnotationAttributes(cache).get("value");
                Arrays.stream(cacheNames).forEach(name-> CACHE_FILTERS.put(name,filter));
            }

    }

    @Override
    public PropertyFilter getKeyFilter(Annotation filter) {
        return LOG_KEY_FILTERS.get(filter);
    }

    @Override
    public PropertyFilter getValueFilter(Annotation filter) {
        return LOG_VALUE_FILTERS.get(filter);
    }

    protected String getApiName(MethodSignature methodSignature){

        Annotation api = methodSignature.getMethod().getAnnotation(logProperties.getLogAnnotation());
        return api==null?"": (String) AnnotationUtils.getAnnotationAttributes(api).get("value");
    }

    protected Annotation getLoggerFilter(MethodSignature methodSignature){
        return methodSignature.getMethod().getAnnotation(logProperties.getFilterAnnotation());
    }

    @Override
    public Annotation getLoggerFilter(String cacheName) {
        return CACHE_FILTERS.get(cacheName);
    }
}
