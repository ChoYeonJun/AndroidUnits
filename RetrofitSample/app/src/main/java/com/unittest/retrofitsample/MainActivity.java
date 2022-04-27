package com.unittest.retrofitsample;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.unittest.retrofitsample.melisma.model.LoginReqUserDto;
import com.unittest.retrofitsample.melisma.model.dto.MusicDto;
import com.unittest.retrofitsample.melisma.service.MusicService;

import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CallRetrofit retrofit = new CallRetrofit();
        MusicService musicService = new MusicService();

//        retrofit.login(new LoginReqUserDto("username", "password"));

        retrofit.login(new LoginReqUserDto("users", "password"));
//        retrofit.login(new Member("test@gmail.com", "password"));
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UUID id = UUID.randomUUID();
                MusicDto model = new MusicDto(id, "url", 10, id);
                musicService.createMusic(model);
//                musicService.searchMusics();
                model.setViews(100);
                model.setMusicUrl("musicUrl");
                musicService.change(model);
//                retrofit.hello();

            }
        });
    }
}