package com.framework.demo.https.retrofit.exception;

import android.net.ParseException;

import com.google.gson.JsonParseException;

import org.json.JSONException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import retrofit2.HttpException;

/**
 *异常处理类
 * Created by yzg on 2017/12/22.
 */

public class ExceptionEngine {
    //未知错误
    public static final int UN_KNOWN_ERROR = 1000;
    //解析数据错误
    public static final int ANALYTIC_SERVER_DATA_ERROR = 1001;
    //网络连接错误
    public static final int CONNECT_ERROR = 1002;
    //网络连接超时
    public static final int TIME_OUT_ERROR = 1003;

    public static ApiException handleException(Throwable e) {
        ApiException ex;
        if (e instanceof HttpException) {               //HTTP错误
            HttpException httpExc = (HttpException) e;
            ex = new ApiException(e, httpExc.code());   //均视为网络错误
            ex.setMsg("网络错误");
            ex.setMsg(e.getMessage());
        } else if (e instanceof ServerException) {      //服务器返回的错误
            ServerException serverExc = (ServerException) e;
            ex = new ApiException(serverExc, serverExc.getCode());
            ex.setMsg(serverExc.getMsg());
        } else if (e instanceof JsonParseException
                || e instanceof JSONException
                || e instanceof ParseException) {       //解析数据错误
            ex = new ApiException(e, ANALYTIC_SERVER_DATA_ERROR);
            ex.setMsg("解析错误");
        } else if (e instanceof ConnectException) {     //连接网络错误
            ex = new ApiException(e, CONNECT_ERROR);
            ex.setMsg("连接失败");
        } else if (e instanceof SocketTimeoutException) {//网络超时
            ex = new ApiException(e, TIME_OUT_ERROR);
            ex.setMsg("网络超时");
        } else {  //未知错误
            ex = new ApiException(e, UN_KNOWN_ERROR);
            ex.setMsg("未知错误");
        }
        return ex;
    }
}
