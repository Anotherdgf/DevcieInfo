package com.anotherdgf.deviceinfo.network;

import io.reactivex.Observable;

import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by denggaofeng on 2018/6/26.
 */

public interface API {

    @GET
    Observable<Response<ResponseBody>> getTop(@Query("start") int start, @Query("count") int count);

}
