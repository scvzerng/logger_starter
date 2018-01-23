package com.zero.scvzerng.logger.cache;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.PropertyFilter;
import com.zero.scvzerng.logger.InfoLogger;
import com.zero.scvzerng.logger.CacheLogProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.cache.interceptor.CacheInterceptor;

import javax.annotation.Resource;
import java.lang.annotation.Annotation;

/**
 * 缓存命中日志拦截器
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Year: 2017-2017/11/18-18:17
 * Project:xycrm_intelligent_service
 * Package:com.yazuo.intelligent.cache
 * To change this template use File | Settings | File Templates.
 */
@Slf4j
public class CacheLogInterceptor extends CacheInterceptor {
    private static final String LOG_TEMPLATE = "hit cache name:{} key:{} value:{}";
    private final CacheLogProperties logProperties;
    @Resource
    private InfoLogger infoLogger;
    public CacheLogInterceptor(final CacheLogProperties logProperties) {
        this.logProperties = logProperties;
    }

    @Override
    protected Cache.ValueWrapper doGet(Cache cache, Object key) {
        Cache.ValueWrapper valueWrapper = super.doGet(cache, key);
        if(needLog(valueWrapper)){
            CacheLogProperties.Enable enable = logProperties.getEnable();
            infoLogger.getLoggerFilter(cache.getName());
            log.info(LOG_TEMPLATE,enable.isName()?cache.getName():"",enable.isKey()?key.toString():"", enable.isValue()?JSON.toJSONString(valueWrapper.get(),getFilters(cache)):"");
        }

        return valueWrapper;
    }

    private boolean needLog(Cache.ValueWrapper valueWrapper){
        return logProperties.getEnable()!=null&&valueWrapper!=null&&valueWrapper.get()!=null;
    }

    private PropertyFilter[] getFilters(Cache cache){
        Annotation filter = infoLogger.getLoggerFilter(cache.getName());
        return new PropertyFilter[]{infoLogger.getKeyFilter(filter),infoLogger.getValueFilter(filter)};
    }


}
