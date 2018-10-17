package com.framework.demo.api;


import com.framework.demo.model.ApiResponse;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.POST;

public interface RetrofitService {

    /*获取主版行情头部列表*/
    @POST("api/index/quotesCategory")
    Observable<ApiResponse<List<String>>> getMarket();

}
