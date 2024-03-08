package com.example.controller;

import com.example.exception.BussinessException;
import com.example.exception.SystemException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class ProjectExceptionAdvice {

    @ExceptionHandler(SystemException.class)
    public Result doSystemException(SystemException e) {
        return new Result(false, e.getCode(), e.getMessage());
    }

    @ExceptionHandler(BussinessException.class)
    public Result doBussinessException(BussinessException e) {
        return new Result(false, e.getCode(), e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public Result doUnknownException(Exception e) {
        return new Result(false, Code.SYSTEM_UNKNOWN_ERR, e.getMessage());
    }
}
