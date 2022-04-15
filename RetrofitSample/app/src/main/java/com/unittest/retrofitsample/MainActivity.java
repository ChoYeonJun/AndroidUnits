package com.unittest.retrofitsample;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;

import com.unittest.retrofitsample.model.LoginReqUserDto;
import com.unittest.retrofitsample.model.Model__CheckAlready;

import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CallRetrofit retrofit = new CallRetrofit();
        UUID id = UUID.randomUUID();
        Model__CheckAlready model = new Model__CheckAlready(id, "url", 10, id);
        retrofit.login(new LoginReqUserDto("user", "password"));

        retrofit.createMusic(model);
    }
}