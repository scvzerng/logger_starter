package com.yazuo.intelligent.logger;

import com.alibaba.fastjson.JSON;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.Signature;
@Slf4j
public class DefaultInfoLogger extends AbstractLoggerPrinter implements InfoLogger {
    private static final int DEFAULT = 200;
    private static final String SUFFIX = "成功";
    private static final String FORMAT = "耗时-{}毫秒 代码:{} 方法:{}[{}] 参数:{} 返回值:{}";

    @Override
    public void log(long start, Signature signature, ApiOperation api, Object[] args, Object result, Throwable exception) {
    log.info(FORMAT,System.currentTimeMillis(),DEFAULT,api.notes()+SUFFIX,signature.toString(), JSON.toJSONString(args,filter),JSON.toJSONString(result,filter));
    }
}
