package com.yazuo.intelligent.logger;

import com.alibaba.fastjson.JSON;
import com.yazuo.intelligent.exception.AbstractException;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.Signature;
@Slf4j
public class DefaultErrorLogger extends AbstractLoggerPrinter implements ErrorLogger {
    private static final int DEFAULT = 500;
    private static final String FORMAT = "耗时-{}毫秒 代码:{} 异常信息:{} 方法:{}[{}] 参数:{}";
    private static final String SUFFIX = "失败";


    @Override
    public void log(long start, Signature signature, ApiOperation api, Object[] args, Object result, Throwable exception) {
      log.error(FORMAT,System.currentTimeMillis()-start,getCode(exception),exception,api.notes()+SUFFIX,signature.toString(), JSON.toJSONString(args,filter));
    }

    private int getCode(Throwable throwable){
        if(throwable instanceof AbstractException){
            return ((AbstractException) throwable).getCode();
        }
            return DEFAULT;
    }
}
