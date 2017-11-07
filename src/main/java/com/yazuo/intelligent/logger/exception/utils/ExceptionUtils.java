package com.yazuo.intelligent.logger.exception.utils;

import org.slf4j.Logger;

import java.io.PrintWriter;
import java.io.StringWriter;

public final class ExceptionUtils {
    /**
     * 将异常转换为string
     * @param exception
     * @return
     */
    public static String exceptionToString(Exception exception){
        if(exception==null) throw new NullPointerException("exception must be not null");
            StringWriter writer = new StringWriter();
            exception.printStackTrace(new PrintWriter(writer,true));
            return writer.toString();
    }

    public static void logError(Logger logger,long spend,String methodName,String apiName,String args,int code,Throwable exception){
        logger.error("耗时-{}毫秒 代码:{} 异常信息:{} 方法:{}[{}] 参数:{}",spend,code,exception.getMessage(),apiName,methodName,args);
    }
    
}
