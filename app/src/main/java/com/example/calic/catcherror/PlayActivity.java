package com.example.calic.catcherror;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

public class PlayActivity extends AppCompatActivity {

    private GameView gameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gameView=new GameView(this);
        setContentView(gameView);//GameView로 화면 전환?
        //View view = new MyView(this);
        //setContentView(view);
        /*new CountDownTimer(100*1000,1000){
            @Override
            public void onTick(long millisUntilFinished){
                value++;
            }

            @Override
            public void onFinish(){
                Intent intent = new Intent(getApplicationContext(),EndActivity.class);
                startActivity(intent);
            }
        }.start();*/
        //setContentView(R.layout.activity_play);
    }



    @Override
    protected void onPause(){
        super.onPause();
        //gameView.pause();
    }

    @Override
    protected void onResume(){
        super.onResume();
        //gameView.resume();
    }

    @Override
    public void onBackPressed() {
        onPause();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Wanna Restart?")
                .setCancelable(false)
                .setPositiveButton("Yes",new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int id){
                        dialog.cancel();//다시시작
                        onResume();
                    }
                })
                .setNegativeButton("No",new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int id){
                        PlayActivity.this.finish();//play화면 종료 main화면으로 Gameview는 어떻게 종료?
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
        //Toast.makeText(this, "Back button pressed.", Toast.LENGTH_SHORT).show();
        //super.onBackPressed();
    }//뒤로가기 나가기

    /*protected class MyView extends View {//화면 터치했을때 터치한 위치 좌표

        public MyView(Context context) {
            super(context);
        }

        @Override
        public boolean onTouchEvent(MotionEvent event){
            super.onTouchEvent(event);
            if(event.getAction()==MotionEvent.ACTION_DOWN){
                float x= event.getX();
                float y= event.getY();
                return true;
            }
            return false;
        }


    }*/

}
