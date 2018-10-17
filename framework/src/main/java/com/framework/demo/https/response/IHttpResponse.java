package com.framework.demo.https.response;

/**
 * 返回数据规范格式
 * 为了上层调用适应各种返回的数据结构
 * @param <T>
 */
public interface IHttpResponse<T> {

    /**
     * 响应吗
     * @return
     */
    int getCode();

    /**
     * 异常消息
     * @return
     */
    String getMessage();

    /**
     * 返回数据
     * @return
     */
    T getData();

    /**
     * 请求是否成功
     * @return
     */
    boolean isSuccess();
}
