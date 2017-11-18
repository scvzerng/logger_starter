package com.yazuo.intelligent.logger;

import com.yazuo.intelligent.logger.filter.HttpObjectFilter;
import com.yazuo.intelligent.logger.filter.LoggerParamsFilter;

public  abstract class AbstractLoggerPrinter implements LoggerPrinter {
    /**
     * 过滤对象序列化时的属性
     */
    protected LoggerParamsFilter filter = new HttpObjectFilter();
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
}
