package com.example.calic.catcherror;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class PlayActivity extends AppCompatActivity {

    private GameView gameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gameView=new GameView(this);
        setContentView(gameView);
        View view = new MyView(this);
        setContentView(view);
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

    protected class MyView extends View {//화면 터치했을때 터치한 위치 좌표

        public MyView(Context context) {
            super(context);
        }

        @Override
        public boolean onTouchEvent(MotionEvent event){
            super.onTouchEvent(event);
            if(event.getAction()==MotionEvent.ACTION_DOWN){
                float x= event.getX();
                float y= event.getY();
                String msg="get Touch: "+x+"/"+y;
                Toast.makeText(PlayActivity.this,msg,Toast.LENGTH_SHORT).show();
                return true;
            }
            return false;
        }
    }
}
