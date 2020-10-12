package com.gdut.gcb.operation.common.exception;


import com.gdut.gcb.operation.common.api.IErrorCode;


/**
 * @Author 古春波
 * @Description 断言处理类，用于抛出各种API异常
 * @Date 2020/10/11 22:08
 * @Version 1.0
 **/
public class Asserts {
    public static void fail(String message) {
        throw new ApiException(message);
    }

    public static void fail(IErrorCode errorCode) {
        throw new ApiException(errorCode);
    }
}
