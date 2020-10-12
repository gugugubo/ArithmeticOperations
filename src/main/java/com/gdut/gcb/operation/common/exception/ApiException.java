package com.gdut.gcb.operation.common.exception;


import com.gdut.gcb.operation.common.api.IErrorCode;


/**
 * @Author 古春波
 * @Description 自定义API异常
 * @Date 2020/10/11 22:08
 * @Version 1.0
 **/
public class ApiException extends RuntimeException {
    private IErrorCode errorCode;

    public ApiException(IErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public ApiException(String message) {
        super(message);
    }

    public ApiException(Throwable cause) {
        super(cause);
    }

    public ApiException(String message, Throwable cause) {
        super(message, cause);
    }

    public IErrorCode getErrorCode() {
        return errorCode;
    }
}
