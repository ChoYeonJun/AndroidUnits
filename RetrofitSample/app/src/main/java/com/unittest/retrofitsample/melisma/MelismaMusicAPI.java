package com.unittest.retrofitsample.melisma;

import com.unittest.retrofitsample.melisma.model.entity.MusicEntity;
import com.unittest.retrofitsample.melisma.model.vo.MusicVo;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface MelismaMusicAPI {

    @POST("musics/create")
    Call<MusicVo> create(@Body MusicEntity musicEntity);

}
