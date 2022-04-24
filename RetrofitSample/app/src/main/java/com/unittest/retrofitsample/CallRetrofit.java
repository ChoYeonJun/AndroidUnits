package com.unittest.retrofitsample;

import android.util.Log;

import com.unittest.retrofitsample.melisma.model.Hello;
import com.unittest.retrofitsample.melisma.model.Member;
import com.unittest.retrofitsample.melisma.model.JwtResponse;
import com.unittest.retrofitsample.melisma.model.LoginReqUserDto;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CallRetrofit {
    private static String token;

    public static String getToken() {
        return token;
    }


    public void login(LoginReqUserDto loginReqUserDto){
        Call<JwtResponse> call = RetrofitClient.getApiService().login(loginReqUserDto);
        call.enqueue(new Callback<JwtResponse>() {
            @Override
            public void onResponse(Call<JwtResponse> call, Response<JwtResponse> response) {
                if(!response.isSuccessful()){
                    Log.e("연결이 비정상적 : ", "error code : " + response.code());
                    return;
                }
                JwtResponse jwtResponse = response.body();
                Log.e("연결 완료 : ",""+ jwtResponse.getToken());
                token = jwtResponse.getToken();
            }

            @Override
            public void onFailure(Call call, Throwable t) {

            }
        });
    }

    public void login(Member member){
        Call<JwtResponse> call = RetrofitClient.getApiService().auth(member);
        call.enqueue(new Callback<JwtResponse>() {
            @Override
            public void onResponse(Call<JwtResponse> call, Response<JwtResponse> response) {
                if(!response.isSuccessful()){
                    Log.e("연결이 비정상적 : ", "error code : " + response.code());
                    return;
                }
                JwtResponse jwtResponse = response.body();
                Log.e("연결 완료 : ",""+ jwtResponse.getToken());
                token = jwtResponse.getToken();
            }

            @Override
            public void onFailure(Call call, Throwable t) {

            }
        });
    }

    public void hello(){
        Call<Hello> call = RetrofitClient.createService(RetrofitAPI.class, token).hello();
        call.enqueue(new Callback<Hello>() {
            @Override
            public void onResponse(Call<Hello> call, Response<Hello> response) {
                if(!response.isSuccessful()){
                    Log.e("연결이 비정상적 : ", "error code : " + response.code());
                    return;
                }

                Hello hello = response.body();
                Log.e("hello: ", "" + hello.getMessage());
            }

            @Override
            public void onFailure(Call<Hello> call, Throwable t) {

            }
        });
    }



}
