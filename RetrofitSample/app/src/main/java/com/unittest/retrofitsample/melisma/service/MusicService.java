package com.unittest.retrofitsample.melisma.service;

import android.util.Log;

import com.unittest.retrofitsample.CallRetrofit;
import com.unittest.retrofitsample.RetrofitClient;
import com.unittest.retrofitsample.melisma.api.MelismaMusicAPI;
import com.unittest.retrofitsample.melisma.model.dto.MusicDto;
import com.unittest.retrofitsample.melisma.model.vo.MusicVo;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MusicService {

    public void createMusic(MusicDto musicDto){
        Call<MusicVo> call = RetrofitClient.createService(MelismaMusicAPI.class, CallRetrofit.getToken()).create(musicDto);
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
                Log.e("연결 실패 : ", "error code : " + t.getMessage());
            }


        });
    }

    public void searchMusics(){
        Call<List<MusicVo>> call = RetrofitClient.createService(MelismaMusicAPI.class, CallRetrofit.getToken()).searchList();
        call.enqueue(new Callback<List<MusicVo>>() {
            @Override
            public void onResponse(Call<List<MusicVo>> call, Response<List<MusicVo>> response) {
                if(!response.isSuccessful()){
                    Log.e("연결이 비정상적 : ", "error code : " + response.code());
                    return;
                }
                List<MusicVo> vos = response.body();

                for (MusicVo musicVo: vos) {
                    Log.d("연결이 성공적 : ", response.body().toString());
                    Log.d("music id", musicVo.getId().toString());
                    Log.d("music url", musicVo.getMusicUrl());
                    Log.d("music at", musicVo.getCreatedAt().toString());
                    Log.d("music at", musicVo.getUpdatedAt().toString());
                    Log.d("music url", musicVo.getViews().toString());
                }
            }

            @Override
            public void onFailure(Call<List<MusicVo>> call, Throwable t) {
                Log.e("연결 실패 : ", "error code : " + t.getMessage());
            }
        });
    }

    public void change(MusicDto dto){
        Call<String> call = RetrofitClient.createService(MelismaMusicAPI.class, CallRetrofit.getToken()).change(dto);

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(!response.isSuccessful()){
                    Log.e("연결이 비정상적 : ", "error code : " + response.code());
                    return;
                }
                Log.d("성공 : ", response.body());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.e("연결 실패 : ", "error code : " + t.getMessage());
            }
        });
    }

    public void update(MusicDto dto){
        Call<String> call = RetrofitClient.createService(MelismaMusicAPI.class, CallRetrofit.getToken()).update(dto);

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(!response.isSuccessful()){
                    Log.e("연결이 비정상적 : ", "error code : " + response.code());
                    return;
                }
                Log.d("성공 : ", response.body());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.e("연결 실패 : ", "error code : " + t.getMessage());
            }
        });
    }

    public void delete(UUID id){
        Call<String> call = RetrofitClient.createService(MelismaMusicAPI.class, CallRetrofit.getToken()).delete(id);

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(!response.isSuccessful()){
                    Log.e("연결이 비정상적 : ", "error code : " + response.code());
                    return;
                }
               Log.d( "성공 : ", response.body());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }
}
