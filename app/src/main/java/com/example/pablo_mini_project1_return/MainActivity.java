package com.example.pablo_mini_project1_return;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

public class MainActivity extends AppCompatActivity {

    MediaPlayer mp; // mediaplayer for music

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        mp = MediaPlayer.create(this,R.raw.awake);
        mp.start();
    }

    public void welcomeimage(View view) {
        Intent intent = new Intent(this, game.class);
        startActivity(intent);
    }

}
