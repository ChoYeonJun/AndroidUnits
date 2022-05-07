package com.unittest.retrofitsample.melisma.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.unittest.retrofitsample.R;

import java.util.Arrays;
import java.util.List;

public class YoutubeActivity extends YouTubeBaseActivity {

    YouTubePlayerView youTubePlayerView;
    YouTubePlayer.OnInitializedListener listener;
    YouTubePlayer player;
    Button play_btn, next_btn, load_btn;
    int count = 0;
    List<String> urls;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube);

        initialize();
        setOnClick();
    }

    private void initialize(){
        youTubePlayerView = findViewById(R.id.youtubeView);
        load_btn = findViewById(R.id.load_btn);
        play_btn = findViewById(R.id.play_btn);
        next_btn = findViewById(R.id.next_btn);
        Intent intent = getIntent();
        urls = Arrays.asList(intent.getStringArrayExtra("urls"));


        listener = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                player = youTubePlayer;
                loadClick("str");
                player.play();
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        };

        youTubePlayerView.initialize("none",listener);
    }

    private void setOnClick(){
        next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadClick(urls.get(count++));
                playClick();
            }
        });

        play_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playClick();
            }
        });

        load_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initClick();
            }
        });
    }

    public void initClick(){
        youTubePlayerView.initialize("none",listener);
    }

    public void loadClick(String code){
        player.loadVideo("1cB5BREyq9Y");
    }

    public void playClick(){
        if(player.isPlaying()) player.pause();
        else player.play();
    }
}
