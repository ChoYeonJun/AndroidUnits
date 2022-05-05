package com.unittest.retrofitsample.melisma.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.unittest.retrofitsample.R;

public class YoutubeActivity extends YouTubeBaseActivity {

    YouTubePlayerView youTubePlayerView;
    YouTubePlayer.OnInitializedListener listener;
    YouTubePlayer player;
    Button play_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube);


        youTubePlayerView = findViewById(R.id.youtubeView);
        Button load_btn = findViewById(R.id.load_btn);
        play_btn = findViewById(R.id.play_btn);

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
