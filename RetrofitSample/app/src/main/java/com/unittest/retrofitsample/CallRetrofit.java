package com.unittest.retrofitsample;

import android.os.Build;
import android.util.Log;

import com.unittest.retrofitsample.model.LoginReqUserDto;
import com.unittest.retrofitsample.model.Model__CheckAlready;
import com.unittest.retrofitsample.model.MusicVo;

import androidx.annotation.RequiresApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CallRetrofit {
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void createMusic(Model__CheckAlready model__checkAlready){
        Call<MusicVo> call = RetrofitClient.getApiService().create(model__checkAlready);
        call.enqueue(new Callback<MusicVo>() {
            @Override
            public void onResponse(Call<MusicVo> call, Response<MusicVo> response) {
                if(!response.isSuccessful()){
                    Log.e("연결이 비정상적 : ", "error code : " + response.code());
                    return;
                }
                MusicVo musicVo = response.body();
                Log.d("연결이 성공적 : ", response.body().toString());
                Log.d("music id", musicVo.getId().toString());
                Log.d("music url", musicVo.getMusicUrl());
                Log.d("music at", musicVo.getCreatedAt().toString());
                Log.d("music at", musicVo.getUpdatedAt().toString());
                Log.d("music url", musicVo.getViews().toString());
//                if(modelCheckAlready.getMessage() == "can use this number"){
//                    Log.d("중복검사: ", "중복된 번호가 아닙니다");
//                    modelCheckAlready.setRight(true);
//                }
            }

            @Override
            public void onFailure(Call<MusicVo> call, Throwable t) {

            }


        });
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void login(LoginReqUserDto loginReqUserDto){
        Call call = RetrofitClient.getApiService().login(loginReqUserDto);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                if(!response.isSuccessful()){
                    Log.e("연결이 비정상적 : ", "error code : " + response.code());
                    return;
                }
                Log.e("연결 완료 : ",""+ response.code());
            }

            @Override
            public void onFailure(Call call, Throwable t) {

            }
        });
    }
}
