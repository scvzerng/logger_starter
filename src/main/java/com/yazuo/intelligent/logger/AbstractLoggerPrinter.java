package com.yazuo.intelligent.logger;

import com.yazuo.intelligent.logger.filter.HttpObjectFilter;
import com.yazuo.intelligent.logger.filter.LoggerParamsFilter;

public  abstract class AbstractLoggerPrinter implements LoggerPrinter {
    /**
     * 过滤对象序列化时的属性
     */
    protected LoggerParamsFilter filter = new HttpObjectFilter();

    public AbstractLoggerPrinter(LoggerParamsFilter filter) {
        this.filter = filter;
    }
}
