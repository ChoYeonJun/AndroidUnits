package com.unittest.retrofitsample.melisma.activity;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.unittest.retrofitsample.CallRetrofit;
import com.unittest.retrofitsample.R;
import com.unittest.retrofitsample.melisma.callback.SearchResponse;
import com.unittest.retrofitsample.melisma.model.dto.LoginReqUserDto;
import com.unittest.retrofitsample.melisma.model.vo.MusicVo;
import com.unittest.retrofitsample.melisma.service.MusicService;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<String> urls;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        urls = new ArrayList<>();

        CallRetrofit retrofit = new CallRetrofit();
        MusicService musicService = new MusicService();

//        retrofit.login(new LoginReqUserDto("username", "password"));

        retrofit.login(new LoginReqUserDto("users", "password"));
//        retrofit.login(new Member("test@gmail.com", "password"));
        Button button = findViewById(R.id.search_btn);
        EditText codeEditText = findViewById(R.id.codeEditText);

        Button add_btn = findViewById(R.id.add_btn);
        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String code = codeEditText.getText().toString();
                if(code != "")
                    urls.add(code);
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                UUID id = UUID.randomUUID();
//                MusicDto model = new MusicDto(id, "url", 10, id);
//                musicService.createMusic(model);
                musicService.searchMusics(new SearchResponse() {
                    @Override
                    public void onSuccess(@NonNull List<MusicVo> vos) {
                        for (MusicVo vo: vos) {
                            Log.d("onSuccess search", vo.getId().toString());
                            urls.add(vo.getMusicUrl());
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable throwable) {

                    }
                });

//                String id = "74e052d2-561a-402d-a8b3-0b6261cb63f9";
//
//                musicService.searchMusic(UUID.fromString(id));
//                model.setViews(100);
//                model.setMusicUrl("musicUrl");
////                musicService.change(model);
////                retrofit.hello();
//                musicService.update(model);
            }
        });

        Button youtube_btn = findViewById(R.id.youtube_btn);

        youtube_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, YoutubeActivity.class);
                intent.putStringArrayListExtra("urls",  (ArrayList<String>)urls);
                startActivity(intent);
            }
        });
    }


}