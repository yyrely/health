package com.chuncongcong.health.common.exception;

public interface ErrorCode {
    int getCode();
    String getMessage();
    int getHttpCode();
}
