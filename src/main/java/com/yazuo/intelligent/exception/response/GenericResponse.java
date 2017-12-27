package com.yazuo.intelligent.exception.response;

import lombok.Data;

/**
 * description
 * <p>
 * 2017-12-27 09:25
 *
 * @author scvzerng
 **/
@Data
public class GenericResponse<T> {
    private int code;
    private String message;
    private T data;
    private String stackInfo;
    private String tradeId;
}
