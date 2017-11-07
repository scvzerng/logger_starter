package com.yazuo.intelligent.logger.exception.response;

import lombok.Data;

@Data
public class SuccessResult<T> {
    private int code = 200;
    private String message;
    private T data;
}
