package com.example.car.base;

public enum AppHttpCodeEnum {
    // 成功
    SUCCESS(200, "success"),
    // 登录
    UNKOWN_ERROR(500, "unkown error, please connect developers"),
    NOT_FOUND_ERROR(404, "Not found"),
    METHOD_NOT_ALLOW_ERROR(405, "Method not allowed"),
    PARAMETER_ERROR(403, "Parameter error");
    int code;
    String msg;

    AppHttpCodeEnum(int code, String errorMessage) {
        this.code = code;
        this.msg = errorMessage;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
