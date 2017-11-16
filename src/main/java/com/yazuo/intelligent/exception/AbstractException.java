package com.yazuo.intelligent.exception;


public class AbstractException extends RuntimeException {
    private int code;

    public AbstractException(int code,String message,Throwable e){
        super(message,e);
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

}
