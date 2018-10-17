package com.framework.demo.utils;

import android.util.Log;


import com.framework.demo.https.interceptor.CommonParamsInterceptor;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 *  okhttpclient 通用配置
 * Created by yzg on 2017/12/25.
 */

public class HttpCommonUtils{

    /**
     * @return 获取公共参数拦截器
     */
    public static Interceptor getCommonParamsInterceptor(Map<String,String> headerMap, Map<String,String> queryParamMap, Map<String,String> formBodyParamMap) {
        return new CommonParamsInterceptor(headerMap,queryParamMap,formBodyParamMap);
    }

    /**
     * @return 获取httplog日志 拦截器
     */
    public static HttpLoggingInterceptor getHttpLoggingInterceptor() {
        HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor(new HttpLogger());
        logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return logInterceptor;
    }

    /**
     * @return 获取缓存对象
     */
    public static Cache getCache(long cacheSize) {
        File httpCacheDirectory = new File(Utils.getApp().getCacheDir(), "responses");
//        int cacheSize = 10 * 1024 * 1024; // 10 MiB
//        long cacheSize = IdeaApi.getCachaSize(); // 10 MiB
        Cache cache = new Cache(httpCacheDirectory, cacheSize);
        return cache;
    }

    /**
     * @return 获取缓存拦截器
     */
    public static Interceptor getCacheInterceptor(final long maxStale) {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                if (Utils.isNetworkAvailable()) {
                    //有网络时只从网络获取
                    request = request.newBuilder()
                            .cacheControl(CacheControl.FORCE_NETWORK)
                            .build();
                } else {
                    //无网络时只从缓存中读取
                    //无网络下强制使用缓存，无论缓存是否过期,此时该请求实际上不会被发送出去。
                    request = request.newBuilder()
                            .cacheControl(CacheControl.FORCE_CACHE)
                            .build();
                }

                Response originalResponse = chain.proceed(request);
                if (Utils.isNetworkAvailable()) {//有网络情况下，根据请求接口的设置，配置缓存。
                    int maxAge = 0; // read from cache
//                    String cache=request.cacheControl().toString(); //读接口中的缓存配置
                    return originalResponse.newBuilder()
                            .removeHeader("Pragma")
                            .header("Cache-Control", "public ,max-age=" + maxAge)
                            .build();
                } else {//无网络
//                    int maxStale = 60 * 60 * 24 * 28; // tolerate 4-weeks stale
//                    long maxStale = IdeaApi.getCacheTime(); // tolerate 4-weeks stale
                    return originalResponse.newBuilder()
                            .removeHeader("Pragma")
                            .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                            .build();
                }

            }
        };

    }

    public static class HttpLogger implements HttpLoggingInterceptor.Logger {
        private StringBuilder mMessage = new StringBuilder();

        @Override
        public void log(String message) {

            // 请求或者响应开始
            if (message.startsWith("--> START")) {
                mMessage.setLength(0);
            }
            // 以{}或者[]形式的说明是响应结果的json数据，需要进行格式化
            if ((message.startsWith("{") && message.endsWith("}"))
                    || (message.startsWith("[") && message.endsWith("]"))) {
                message = JsonUtil.formatJson(message);
            }
            mMessage.append(message.concat("\n"));
            // 请求或者响应结束，打印整条日志
            if (message.startsWith("<-- END HTTP")) {
                Log.d("Request",mMessage.toString());
            }
        }
    }

}
