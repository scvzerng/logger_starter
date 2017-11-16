package com.yazuo.intelligent.logger.exception;

public class BusinessException extends AbstractException {
    public BusinessException(int code, String message, Throwable e) {
        super(code, message, e);
    }

    public BusinessException( String message, Throwable e) {
        this(2000,message,e);
    }

    public BusinessException(String message) {
       this(2000,message,null);
    }
}
