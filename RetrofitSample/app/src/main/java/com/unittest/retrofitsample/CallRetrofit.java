package com.unittest.retrofitsample;

import android.os.Build;
import android.util.Log;

import com.unittest.retrofitsample.model.Hello;
import com.unittest.retrofitsample.model.JwtResponse;
import com.unittest.retrofitsample.model.LoginReqUserDto;
import com.unittest.retrofitsample.model.Model__CheckAlready;
import com.unittest.retrofitsample.model.MusicVo;

import androidx.annotation.RequiresApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@RequiresApi(api = Build.VERSION_CODES.O)
public class CallRetrofit {
    private String token;

    public void createMusic(Model__CheckAlready model__checkAlready){
        Call<MusicVo> call = RetrofitClient.createService(RetrofitAPI.class, token).create(model__checkAlready);
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

    public void hello(){
        Call<Hello> call = RetrofitClient.createService(RetrofitAPI.class, token).hello();
        call.enqueue(new Callback<Hello>() {
            @Override
            public void onResponse(Call<Hello> call, Response<Hello> response) {
                if(!response.isSuccessful()){
                    Log.e("연결이 비정상적 hello: ", "error code : " + response.code());
                    return;
                }
                Hello str = response.body();
                Log.e("연결 완료 : ",""+ str.getMessage());
            }

            @Override
            public void onFailure(Call<Hello> call, Throwable t) {

            }
        });
    }


}
