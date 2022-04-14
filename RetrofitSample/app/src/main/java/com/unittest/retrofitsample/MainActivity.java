package com.unittest.retrofitsample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CallRetrofit retrofit = new CallRetrofit();
        UUID id = UUID.randomUUID();
        Model__CheckAlready model = new Model__CheckAlready(id, "url", 10, id);

        retrofit.createMusic(model);
    }
}