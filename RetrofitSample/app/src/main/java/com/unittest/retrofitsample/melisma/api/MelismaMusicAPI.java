package com.unittest.retrofitsample.melisma.api;

import com.unittest.retrofitsample.melisma.model.dto.MusicDto;
import com.unittest.retrofitsample.melisma.model.vo.MusicVo;

import java.util.List;
import java.util.UUID;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface MelismaMusicAPI {

    @POST("musics/create")
    Call<MusicVo> create(@Body MusicDto musicDto);

    @GET("musics/searchList")
    Call<List<MusicVo>> searchList();

    @GET("musics/search/{id}")
    Call<MusicVo> search(@Path("id") UUID id);

    @PATCH("musics/change")
    Call<String> change(@Body MusicDto musicDto);

    @PUT("musics/update")
    Call<String> update(@Body MusicDto musicDto);

    @DELETE("musics/delete")
    Call<String> delete(UUID id);

}
