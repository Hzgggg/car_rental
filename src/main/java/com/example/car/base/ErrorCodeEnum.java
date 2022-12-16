package com.example.car.base;

public enum ErrorCodeEnum {
    // 成功
    SUCCESS(200, "success"),
    // 登录
    UNKOWN_ERROR(500, "unkown error, please connect developers"),
    NOT_FOUND_ERROR(404, "Not found"),
    METHOD_NOT_ALLOW_ERROR(405, "Method not allowed"),
    PARAMETER_ERROR(403, "Parameter error"),
    SERVICE_STOCK_ERROR(10000, "The stock of cars is insufficient"),
    SERVICE_CAR_NOT_EXISTS_ERROR(10001, "The car does not exist"),
    SERVICE_USER_NOT_EXISTS_ERROR(10002, "The user does not exist"),
    SERVICE_ORDER_NOT_COMPLETED_ERROR(10003, "There are orders in progress"),
    SERVICE_ORDER_STATUS_ERROR(10005, "Order status is incorrect and cannot be changed"),
    SERVICE_ORDER_NOT_EXISTS_ERROR(10004, "Order does not exist");
    int code;
    String msg;

    ErrorCodeEnum(int code, String errorMessage) {
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
