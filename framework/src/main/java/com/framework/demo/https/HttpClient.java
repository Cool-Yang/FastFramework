package com.framework.demo.https;


import com.framework.demo.utils.HttpCommonUtils;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpClient {
    private HttpConfig mConfig;

    /**
     * 内部类单列设计模式
     */
    private HttpClient() {
    }

    private static class HttpClientInstance {
        private final static HttpClient RETROFIT_MANAGER = new HttpClient();
    }

    public static HttpClient getInstance() {
        return HttpClientInstance.RETROFIT_MANAGER;
    }

    public void init(HttpConfig config){
        mConfig = config;
    }

    /**
     * 创建retrofit
     *
     * @return Retrofit
     */
    private Retrofit createrRetrofit(String baseurl) {
        OkHttpClient.Builder okHttpBuilder = new OkHttpClient.Builder()
                .readTimeout(mConfig.getReadTimeOut(), TimeUnit.SECONDS)
                .connectTimeout(mConfig.getConnectTimeOut(), TimeUnit.SECONDS)
                .writeTimeout(mConfig.getWriteTimeOut(), TimeUnit.SECONDS)
                .addNetworkInterceptor(HttpCommonUtils.getCommonParamsInterceptor(mConfig.getmCommonHeaders(),mConfig.getmCommonGetParams(),mConfig.getmCommonPostParams()));

        if(mConfig.getCookieJar() != null)
            okHttpBuilder.cookieJar(mConfig.getCookieJar());

        if(mConfig.isDebug())
            okHttpBuilder.addInterceptor(HttpCommonUtils.getHttpLoggingInterceptor());

        if(mConfig.isCache()) {
            okHttpBuilder.addInterceptor(HttpCommonUtils.getCacheInterceptor(mConfig.getCacheTime()));
            okHttpBuilder.cache(HttpCommonUtils.getCache(mConfig.getCachaSize()));
        }

        if(mConfig.getCookieJar() != null)
            okHttpBuilder.cookieJar(mConfig.getCookieJar());

        OkHttpClient httpClient = okHttpBuilder.build();

        return new Retrofit.Builder()
                .baseUrl(baseurl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(httpClient)
                .build();
    }

    /**
     *根据各模块业务接口 获取不同的retrofit service接口对象
     */
    public <T> T getRetrofitService(String baseUrl, Class<T> cls) {
        if(mConfig == null)
            mConfig = HttpConfig.getBulider().build();
        return createrRetrofit(baseUrl).create(cls);
    }

}
