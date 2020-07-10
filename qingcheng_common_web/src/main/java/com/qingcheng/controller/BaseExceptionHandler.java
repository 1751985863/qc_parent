package com.qingcheng.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import com.qingcheng.entity.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class BaseExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result error(Exception e){
        return new Result(1,e.getMessage();

    }
}
