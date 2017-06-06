package com.example.calic.catcherror;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class PlayActivity extends AppCompatActivity {

    private GameView gameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gameView=new GameView(this);
        setContentView(gameView);
        //setContentView(R.layout.activity_play);
    }

    @Override
    protected void onPause(){
        super.onPause();
        gameView.pause();
    }

    @Override
    protected void onResume(){
        super.onResume();
        gameView.resume();
    }
}
