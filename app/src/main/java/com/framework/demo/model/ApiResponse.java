package com.framework.demo.model;


import com.framework.demo.https.response.IHttpResponse;

/**
 * http响应参数实体类
 * 通过Gson解析属性名称需要与服务器返回字段对应,或者使用注解@SerializedName
 * 备注:这里与服务器约定返回格式
 *
 * Created by yzg on 2017/12/22.
 */
public class ApiResponse<T> implements IHttpResponse<T> {

    /**
     * 状态码
     */
    public int responseCode;

    /**
     * 描述信息
     */
    public String message;


    public String status;

    /**
     * 数据对象
     */
    public T data;


    @Override
    public int getCode() {
        return responseCode;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public T getData() {
        return data;
    }

    @Override
    public boolean isSuccess() {
        return responseCode == 200 && status.equals("SUCCESS");
    }

    @Override
    public String toString() {
        return "ApiResponse{" +
                "responseCode=" + responseCode +
                ", message='" + message + '\'' +
                ", status='" + status + '\'' +
                ", data=" + data +
                '}';
    }
}
