package com.yazuo.intelligent.logger;

import io.swagger.annotations.ApiOperation;
import org.aspectj.lang.Signature;

public interface LoggerPrinter {
    /**
     * 打印日志
     * @param start 起始时间
     * @param signature 方法签名
     * @param api 接口说明
     * @param args 参数
     * @param exception 异常
     * @param result 返回值
     */
    void log(long start, Signature signature, ApiOperation api, Object[] args, Object result, Throwable exception);
    void clear();
}
