package com.unittest.retrofitsample;

import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RetrofitAPI {
    @POST("musics/create")
    Call<MusicVo> create(@Body Model__CheckAlready modelCheckAlready);


}
