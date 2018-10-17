package com.framework.demo.https.retrofit.exception;

/**
 * 自定义的错误类型 例如:
 * int code：表示接口请求状态，0表示成功，-101表示密码错误等等
 * String msg：表示接口请求返回的描述。success，”token过期”等等
 * Created by yzg on 2017/12/22.
 */

public class ServerException extends RuntimeException {
    private int code;
    private String msg;

    public ServerException(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
