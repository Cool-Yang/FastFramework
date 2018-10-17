package com.framework.demo.https.interceptor;


import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by yzg on 2017/12/25.
 */

public class CommonParamsInterceptor implements Interceptor {
    private Map<String,String> headerMap;
    private Map<String,String> queryParamMap;
    private Map<String,String> newFormBodyParamMap;

    public CommonParamsInterceptor (Map<String,String> headerMap, Map<String,String> queryParamMap, Map<String,String> formBodyParamMap){
        this.headerMap = headerMap;
        this.queryParamMap = queryParamMap;
        this.newFormBodyParamMap = formBodyParamMap;
    }

    public CommonParamsInterceptor(){}

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originRequest = chain.request();
        Request.Builder newRequest = originRequest.newBuilder();

        // Header
        Headers.Builder newHeaderBuilder = originRequest.headers().newBuilder();
        if (headerMap != null && !headerMap.isEmpty()) {
            for (Map.Entry<String, String> entry : headerMap.entrySet()) {
                newHeaderBuilder.add(entry.getKey(), entry.getValue());
            }
            newRequest.headers(newHeaderBuilder.build());
        }

        // Query Param
        if ("GET".equals(originRequest.method())) {
            HttpUrl.Builder newUrlBuilder = originRequest.url().newBuilder();
            if (queryParamMap != null && !queryParamMap.isEmpty()) {
                for (Map.Entry<String, String> entry : queryParamMap.entrySet()) {
                    newUrlBuilder.addQueryParameter(entry.getKey(), entry.getValue());
                }
                newRequest.url(newUrlBuilder.build());
            }
        } else if ("POST".equals(originRequest.method())) {
            RequestBody body = originRequest.body();
            if (body != null && body instanceof FormBody) {
                // POST Param x-www-form-urlencoded
                FormBody formBody = (FormBody) body;
                Map<String, String> formBodyParamMap = new HashMap<>();
                int bodySize = formBody.size();
                for(int i = 0; i < bodySize; i++) {
                    formBodyParamMap.put(formBody.name(i), formBody.value(i));
                }

                if (newFormBodyParamMap != null && !newFormBodyParamMap.isEmpty()) {
                    formBodyParamMap.putAll(newFormBodyParamMap);
                    FormBody.Builder bodyBuilder = new FormBody.Builder();
                    for (Map.Entry<String, String> entry : formBodyParamMap.entrySet()) {
                        bodyBuilder.add(entry.getKey(), entry.getValue());
                    }
                    newRequest.method(originRequest.method(), bodyBuilder.build());
                }
            } else if (body != null && body instanceof MultipartBody) {
                // POST Param form-data
                MultipartBody multipartBody = (MultipartBody) body;
                MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);

                if(newFormBodyParamMap != null && !newFormBodyParamMap.isEmpty()) {
                    for (Map.Entry<String, String> entry : newFormBodyParamMap.entrySet()) {
                        builder.addFormDataPart(entry.getKey(), entry.getValue());
                    }
                }
                List<MultipartBody.Part> parts = multipartBody.parts();
                for (MultipartBody.Part part : parts) {
                    builder.addPart(part);
                }
                newRequest.post(builder.build());
            }
        }
        return chain.proceed(newRequest.build());
    }
}
