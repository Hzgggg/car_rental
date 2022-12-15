package com.example.car.exception;

import com.example.car.base.AppHttpCodeEnum;
import com.example.car.base.ResponseResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestControllerAdvice
public class ExceptionsHandler {

    /**
     * 注意：
     * 启用全局异常接管后，没有在此处定义拦截的异常都会默认返回500错误。
     * 若需要自定义拦截的异常，请在此处定义拦截。
     * 若需要输出异常的日志日志，请使用logger输出。
     */
    private final Logger logger = LoggerFactory.getLogger(ExceptionsHandler.class);

    /**
     * 基本异常
     */
    @ExceptionHandler(Exception.class)
    public ResponseResult exception(Exception e) {
        logger.error(e.getMessage(), e);
        return ResponseResult.errorResult(AppHttpCodeEnum.UNKOWN_ERROR);
    }

    /**
     * 请求路径无法找到异常
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseResult notFoundException() {
        return ResponseResult.errorResult(AppHttpCodeEnum.NOT_FOUND_ERROR);
    }

    /**
     * 请求方法不支持异常
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseResult httpRequestMethodNotSupportedException() {
        return ResponseResult.errorResult(AppHttpCodeEnum.METHOD_NOT_ALLOW_ERROR);
    }

    /**
     * 请求参数异常
     */
    @ExceptionHandler({HttpMessageNotReadableException.class, MissingServletRequestParameterException.class, MissingServletRequestPartException.class, BindException.class})
    public ResponseResult parameterException() {
        return ResponseResult.errorResult(AppHttpCodeEnum.PARAMETER_ERROR);
    }

    /**
     * 服务异常
     */
    @ExceptionHandler(ServiceException.class)
    public ResponseResult serviceException(ServiceException e) {
        return new ResponseResult().error(e.getCode(), e.getMessage());
    }
}
