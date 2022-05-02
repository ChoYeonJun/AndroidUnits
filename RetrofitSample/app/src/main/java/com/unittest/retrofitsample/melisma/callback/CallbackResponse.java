package com.unittest.retrofitsample.melisma.callback;

import com.unittest.retrofitsample.melisma.model.vo.MusicVo;

import java.util.List;

import androidx.annotation.NonNull;

public interface CallbackResponse {
    void onSearchSuccess(@NonNull List<MusicVo> vos);

    void onError(@NonNull Throwable throwable);
}
