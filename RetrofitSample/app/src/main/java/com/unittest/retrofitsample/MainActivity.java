package com.unittest.retrofitsample;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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
//        retrofit.login(new LoginReqUserDto("username", "password"));

//        retrofit.createMusic(model);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                retrofit.login(new LoginReqUserDto("users", "password"));

//                retrofit.hello();

            }
        });
    }
}