package com.example.car.exception;

import com.example.car.base.ErrorCodeEnum;
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

    private final Logger logger = LoggerFactory.getLogger(ExceptionsHandler.class);

    /**
     * Basic Error/Exception
     */
    @ExceptionHandler(Exception.class)
    public ResponseResult exception(Exception e) {
        logger.error(e.getMessage(), e);
        return ResponseResult.errorResult(ErrorCodeEnum.UNKOWN_ERROR);
    }

    /**
     * path not found
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseResult notFoundException() {
        return ResponseResult.errorResult(ErrorCodeEnum.NOT_FOUND_ERROR);
    }

    /**
     * The request method does not exists
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseResult httpRequestMethodNotSupportedException() {
        return ResponseResult.errorResult(ErrorCodeEnum.METHOD_NOT_ALLOW_ERROR);
    }

    /**
     * parameters error
     */
    @ExceptionHandler({HttpMessageNotReadableException.class, MissingServletRequestParameterException.class, MissingServletRequestPartException.class, BindException.class})
    public ResponseResult parameterException() {
        return ResponseResult.errorResult(ErrorCodeEnum.PARAMETER_ERROR);
    }

    /**
     * service error
     */
    @ExceptionHandler(ServiceException.class)
    public ResponseResult serviceException(ServiceException e) {
        return new ResponseResult().error(e.getCode(), e.getMessage());
    }
}
