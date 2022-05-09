package com.unittest.retrofitsample;

import com.unittest.retrofitsample.melisma.model.Hello;
import com.unittest.retrofitsample.melisma.model.JwtResponse;
import com.unittest.retrofitsample.melisma.model.dto.LoginReqUserDto;
import com.unittest.retrofitsample.melisma.model.Member;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RetrofitAPI {

    @POST("users/login")
    Call <JwtResponse> login(@Body LoginReqUserDto loginReqUserDto);

    @POST("authenticate")
    Call <JwtResponse> auth(@Body Member member);

    @GET("hello")
    Call<Hello> hello();
}
