package com.qingcheng.entity;

import java.io.Serializable;

public class Result implements Serializable {
    private Integer code;//返回作业状态码 0表示执行成功 1表示执行失败
    private String message;//作业状态信息

    public Result() {
        code=0;
        message="执行成功";
    }

    public Result(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
