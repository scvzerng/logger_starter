package com.yazuo.intelligent.exception.utils;


import java.io.PrintWriter;
import java.io.StringWriter;

public final class ExceptionUtils {
    /**
     * 将异常转换为string
     * @param exception
     * @return
     */
    public static String exceptionToString(Throwable exception){
        if(exception==null) throw new NullPointerException("exception must be not null");
            StringWriter writer = new StringWriter();
            exception.printStackTrace(new PrintWriter(writer,true));
            return writer.toString();
    }
}
