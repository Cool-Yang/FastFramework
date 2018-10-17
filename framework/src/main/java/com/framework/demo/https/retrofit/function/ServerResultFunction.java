package com.framework.demo.https.retrofit.function;


import android.util.Log;


import com.framework.demo.BuildConfig;
import com.framework.demo.https.response.IHttpResponse;
import com.framework.demo.https.retrofit.exception.ServerException;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;


/**
 * Created by yzg on 2017/12/22.
 */

public class ServerResultFunction<T> implements Function<IHttpResponse<T>, Object> {
    @Override
    public Object apply(@NonNull IHttpResponse<T> response){
        //打印服务器回传结果
        if (BuildConfig.DEBUG)
            Log.e("ServerResultFunction:" , response.toString());

        if (!response.isSuccess()) {
            throw new ServerException(response.getCode(), response.getMessage());
        }
        return response.getData();
    }
}
