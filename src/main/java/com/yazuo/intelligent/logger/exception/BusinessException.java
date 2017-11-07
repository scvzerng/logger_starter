package com.yazuo.intelligent.logger.exception;

public class BusinessException extends AbstractException {
    public BusinessException(int code, String message, Throwable e) {
        super(code, message, e);
    }
}
