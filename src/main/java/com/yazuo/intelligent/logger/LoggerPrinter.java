package com.yazuo.intelligent.logger;

import com.alibaba.fastjson.serializer.PropertyFilter;
import com.yazuo.intelligent.logger.filter.ValueFilter;
import io.swagger.annotations.ApiOperation;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.annotation.Annotation;

public interface LoggerPrinter {
    /**
     * 打印日志
     * @param start 起始时间
     * @param signature 方法签名
     * @param args 参数
     * @param exception 异常
     * @param result 返回值
     */
    void log(long start, MethodSignature signature, Object[] args, Object result, Throwable exception);

    /**
     * 添加日志打印过滤器
     * @param filter 过滤注解
     */
    void addFilter(Annotation filter, Annotation cache);

    /**
     *获取该api的值过滤器 通过类型过滤
     * @return
     */
    PropertyFilter getValueFilter(Annotation filter);

    /**
     * 获取该api的key过滤器 通过字段名过滤
     * @return
     */
    PropertyFilter getKeyFilter(Annotation filter);

    /**
     * 根据缓存名称获取日志过滤注解
     * @param cacheName
     * @return
     */
    Annotation getLoggerFilter(String cacheName);


}
