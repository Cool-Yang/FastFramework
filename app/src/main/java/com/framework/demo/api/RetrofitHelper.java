package com.framework.demo.api;


import com.framework.demo.https.HttpClient;

public class RetrofitHelper {

    public static RetrofitService getApiService(){
        return   HttpClient.getInstance().getRetrofitService("https://app.6x.com/app/", RetrofitService.class);

    }
}
