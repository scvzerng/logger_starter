package com.yazuo.intelligent.exception;

public class SystemException extends AbstractException {
    public SystemException(Throwable e) {
        super(500, e.getMessage(), e);
    }
}
