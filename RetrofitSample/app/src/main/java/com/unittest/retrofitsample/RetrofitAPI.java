package com.unittest.retrofitsample;

import com.unittest.retrofitsample.model.Hello;
import com.unittest.retrofitsample.model.JwtResponse;
import com.unittest.retrofitsample.model.LoginReqUserDto;
import com.unittest.retrofitsample.model.Model__CheckAlready;
import com.unittest.retrofitsample.model.MusicVo;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RetrofitAPI {
    @POST("musics/create")
    Call<MusicVo> create(@Body Model__CheckAlready modelCheckAlready);

    @POST("authenticate")
    Call <JwtResponse> login(@Body LoginReqUserDto loginReqUserDto);

    @GET("hello")
    Call <Hello> hello();
}
