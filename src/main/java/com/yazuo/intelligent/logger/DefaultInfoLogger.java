package com.yazuo.intelligent.logger;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.PropertyFilter;
import com.yazuo.intelligent.logger.filter.LoggerParamsFilter;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.annotation.Annotation;


@Slf4j
public class DefaultInfoLogger extends AbstractLoggerPrinter implements InfoLogger {
    private static final int DEFAULT = 200;
    private static final String SUFFIX = "成功";
    private static final String FORMAT = "耗时-{}毫秒 代码:{} 方法:{}[{}] 参数:{} 返回值:{}";

    public DefaultInfoLogger(LoggerParamsFilter filter) {
        super(filter);
    }

    @Override
    public void log(long start, MethodSignature signature, Object[] args, Object result, Throwable exception) {
        PropertyFilter[] filters = getFilters(signature);
        log.info(FORMAT,System.currentTimeMillis()-start,DEFAULT,getApiName(signature)+SUFFIX,signature, JSON.toJSONString(args,filters),JSON.toJSONString(result,filters));
    }

    private PropertyFilter[] getFilters(MethodSignature signature){
        Annotation loggerFilter = getLoggerFilter(signature);
        PropertyFilter keyFilter = this.getKeyFilter(loggerFilter);
        PropertyFilter valueFilter = this.getValueFilter(loggerFilter);
        return new PropertyFilter[]{filter,keyFilter,valueFilter};
    }

}
