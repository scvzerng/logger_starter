package com.zero.scvzerng.logger;

import com.alibaba.fastjson.JSON;
import com.yazuo.intelligent.common.exception.AbstractException;
import com.yazuo.intelligent.common.exception.utils.ExceptionUtils;
import com.zero.scvzerng.logger.filter.LoggerParamsFilter;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.reflect.MethodSignature;

@Slf4j
public class DefaultErrorLogger extends AbstractLoggerPrinter implements ErrorLogger {
    private static final int DEFAULT = 500;
    private static final String FORMAT = "耗时-{}毫秒 代码:{} 异常信息:{} 方法:{}[{}] 参数:{}";
    private static final String SUFFIX = "失败";

    public DefaultErrorLogger(LoggerParamsFilter filter) {
        super(filter);
    }


    @Override
    public void log(long start, MethodSignature signature, Object[] args, Object result, Throwable exception) {
      log.error(FORMAT,System.currentTimeMillis()-start,getCode(exception), ExceptionUtils.exceptionToString(exception),getApiName(signature)+SUFFIX,signature.toString(), JSON.toJSONString(args,filter));
    }

    private int getCode(Throwable throwable){
        if(throwable instanceof AbstractException){
            return ((AbstractException) throwable).getCode();
        }
            return DEFAULT;
    }


}
