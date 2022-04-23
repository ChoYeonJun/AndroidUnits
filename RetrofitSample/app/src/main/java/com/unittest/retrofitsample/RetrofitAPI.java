package com.unittest.retrofitsample;

import com.unittest.retrofitsample.melisma.model.JwtResponse;
import com.unittest.retrofitsample.melisma.model.LoginReqUserDto;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RetrofitAPI {

    @POST("users/login")
    Call <JwtResponse> login(@Body LoginReqUserDto loginReqUserDto);

}
