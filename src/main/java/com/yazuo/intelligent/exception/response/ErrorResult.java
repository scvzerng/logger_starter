package com.yazuo.intelligent.exception.response;

import lombok.Data;

@Data
public final class ErrorResult {
    private int code;
    private String message;
    private String stackInfo;
    private String tradeId;

}
