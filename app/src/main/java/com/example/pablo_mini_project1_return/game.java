package com.example.pablo_mini_project1_return;

import android.media.MediaPlayer;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

// PLEASE NOTE THAT THE SONG USED IN THIS APP IS FROM THE VIDEO GAME CELESTE AND I DO NOT OWN THIS SONG "AWAKE" FROM
// THE OFFICIAL SOUNDTRACK AND TAKE NO CREDIT TO ITS CREATION.

public class game extends AppCompatActivity {

    MediaPlayer mp; // mediaplayer for music

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
    }

}
