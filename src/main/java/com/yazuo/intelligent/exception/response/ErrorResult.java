package com.yazuo.intelligent.exception.response;

import lombok.Data;

@Data
public final class ErrorResult {
    private int code = 500;
    private String message;
    private String stackInfo;
    private String tradeId;

}
