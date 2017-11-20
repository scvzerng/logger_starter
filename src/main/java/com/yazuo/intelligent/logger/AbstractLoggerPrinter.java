package com.yazuo.intelligent.logger;

import com.alibaba.fastjson.serializer.PropertyFilter;
import com.yazuo.intelligent.logger.filter.HttpObjectFilter;
import com.yazuo.intelligent.logger.filter.KeyFilter;
import com.yazuo.intelligent.logger.filter.LoggerParamsFilter;
import com.yazuo.intelligent.logger.filter.ValueFilter;
import io.swagger.annotations.ApiOperation;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
public  abstract class AbstractLoggerPrinter implements LoggerPrinter {
    /**
     * 过滤对象序列化时的属性
     */
    protected LoggerParamsFilter filter = new HttpObjectFilter();
    private static final Map<ApiOperation,KeyFilter> LOG_KEY_FILTERS = new HashMap<>();
    private static final Map<ApiOperation,ValueFilter> LOG_VALUE_FILTERS = new HashMap<>();
    /**
     * 用于对缓存日志的去重
     */
    public static final ThreadLocal<Boolean> TAG = new ThreadLocal<>();

    public AbstractLoggerPrinter(LoggerParamsFilter filter) {
        this.filter = filter;
    }

    @Override
    public void clear() {
        TAG.remove();
    }

    @Override
    public void addFilter(ApiOperation api, LoggerFilter filter) {
            LOG_KEY_FILTERS.put(api,new KeyFilter(Arrays.asList(filter.keys())));
            LOG_VALUE_FILTERS.put(api,new ValueFilter(Arrays.asList(filter.types())));
    }

    @Override
    public PropertyFilter getKeyFilter(ApiOperation api) {
        return LOG_KEY_FILTERS.get(api);
    }

    @Override
    public PropertyFilter getValueFilter(ApiOperation api) {
        return LOG_VALUE_FILTERS.get(api);
    }

}
