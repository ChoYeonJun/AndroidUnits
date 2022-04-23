package com.unittest.retrofitsample;

import android.os.Build;
import android.util.Log;

import com.unittest.retrofitsample.melisma.MelismaMusicAPI;
import com.unittest.retrofitsample.melisma.model.entity.MusicEntity;
import com.unittest.retrofitsample.melisma.model.JwtResponse;
import com.unittest.retrofitsample.melisma.model.LoginReqUserDto;
import com.unittest.retrofitsample.melisma.model.vo.MusicVo;

import androidx.annotation.RequiresApi;
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



}
