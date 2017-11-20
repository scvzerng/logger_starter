package com.yazuo.intelligent.logger;

import com.alibaba.fastjson.serializer.PropertyFilter;
import com.yazuo.intelligent.logger.filter.ValueFilter;
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

    /**
     * 添加日志打印过滤器
      * @param api 被调用的api
     * @param filter 过滤注解
     */
    void addFilter(ApiOperation api,LoggerFilter filter);

    /**
     *获取该api的值过滤器 通过类型过滤
     * @param api
     * @return
     */
    PropertyFilter getValueFilter(ApiOperation api);

    /**
     * 获取该api的key过滤器 通过字段名过滤
     * @param api
     * @return
     */
    PropertyFilter getKeyFilter(ApiOperation api);
}
