package com.unittest.retrofitsample.melisma.api;

import com.unittest.retrofitsample.melisma.model.dto.MusicDto;
import com.unittest.retrofitsample.melisma.model.vo.MusicVo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;

public interface MelismaMusicAPI {

    @POST("musics/create")
    Call<MusicVo> create(@Body MusicDto musicDto);

    @GET("musics/searchList")
    Call<List<MusicVo>> searchList();

    @PATCH("musics/change")
    Call<String> change(@Body MusicDto musicDto);
}
