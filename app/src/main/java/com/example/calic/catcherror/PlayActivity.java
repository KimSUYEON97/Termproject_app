package com.example.calic.catcherror;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.Random;

public class PlayActivity extends AppCompatActivity {

    //private GameView gameView;
    private int width,height;
    ArrayList<Error> errorList = new ArrayList<Error>();
    ArrayList<GraphicObject> drawList = new ArrayList<GraphicObject>();
    Character humanA = new Character(800,450);
    private int time=0,movement=0,count =0,change=0;
    private Bitmap Err;
    private Bitmap Char;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Char = BitmapFactory.decodeResource(getResources(), R.drawable.bugimage1);
        Err = BitmapFactory.decodeResource(getResources(), R.drawable.bugimage);
        View view = new MyView(this);
        setContentView(view);
        new CountDownTimer(60*1000,1000){//1분간 플레이
            @Override
            public void onTick(long millisUntilFinished)
            {
                Random random =new Random();
                int num = random.nextInt(20)+10;
                if(time%10==1) {//10초마다 새로운 에러 발생
                    for(int i=0;i<num;i++) {
                        errorList.add(new Error(movement, width,height));//에러 추가
                        drawList.add(new GraphicObject(Err));
                        drawList.get(i+count).SetPosition(errorList.get(i+count).getX(),errorList.get(i).getY());
                        Log.v("태그","에러가 생성되었습니다");
                        movement++;//방향설정을 위한 값
                    }
                    count=count+num;
                }
                time++;
                Log.v("태그","update가 실행되었습니다.");
            }

            @Override
            public void onFinish(){//끝내기

            }
        }.start();

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

    private void update(){//움직임 변화
        for(int i=0;i<count;i++){
            if(errorList.get(i).getMovement()%4==0){
                if (errorList.get(i).getX() == width || errorList.get(i).getY() == 0) {
                    errorList.get(i).setMovement(errorList.get(i).getMovement() + 1);
                    //errorList.get(i).move6();
                }
                else {
                    errorList.get(i).move1();
                    errorList.get(i).move6();
                    drawList.get(i).SetPosition(errorList.get(i).getX(),errorList.get(i).getY());
                }
            }
            if(errorList.get(i).getMovement()%4==1){
                if(errorList.get(i).getX()==width||errorList.get(i).getY()==height) {
                    errorList.get(i).setMovement(errorList.get(i).getMovement() + 1);
                    //errorList.get(i).move5();
                }
                else {
                    errorList.get(i).move2();
                    errorList.get(i).move5();
                    drawList.get(i).SetPosition(errorList.get(i).getX(),errorList.get(i).getY());
                }
            }
            if(errorList.get(i).getMovement()%4==2){
                if (errorList.get(i).getX() == 0 || errorList.get(i).getY() == height) {
                    errorList.get(i).setMovement(errorList.get(i).getMovement() + 1);
                    //errorList.get(i).move5();
                }
                else {
                    errorList.get(i).move3();
                    errorList.get(i).move5();
                    drawList.get(i).SetPosition(errorList.get(i).getX(),errorList.get(i).getY());
                }
            }
            if(errorList.get(i).getMovement()%4==3){
                if (errorList.get(i).getX() == 0 || errorList.get(i).getY() == 0) {
                    errorList.get(i).setMovement(errorList.get(i).getMovement() + 1);
                    //errorList.get(i).move6();
                }
                else {
                    errorList.get(i).move4();
                    errorList.get(i).move6();
                    drawList.get(i).SetPosition(errorList.get(i).getX(),errorList.get(i).getY());
                }
            }
        }
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

    }//뒤로가기 나가기

    protected class MyView extends View {

        public MyView(Context context) {
            super(context);
            DisplayMetrics dm = getApplicationContext().getResources().getDisplayMetrics();
            width=dm.widthPixels;
            height=dm.heightPixels;
        }

        protected void onDraw(Canvas canvas){//움직임 설정//1초에 60번 실행된다.?
            canvas.drawBitmap(Char,humanA.getX(),humanA.getY(),null);
            for(int i=0;i<count;i++){
                drawList.get(i).Draw(canvas);
            }
            invalidate();
            update();//움직임 업데이트

        }

        @Override
        public boolean onTouchEvent(MotionEvent event){
            super.onTouchEvent(event);
            if(event.getAction()==MotionEvent.ACTION_DOWN){
                float x= event.getX();//화면 터치했을때 터치한 위치 좌표?
                float y= event.getY();
                Log.v("태그","touch가 실행되었습니다.");
                for (int i=0;i<count;i++) {
                    if (errorList.get(i).getX() <= x &&x<=errorList.get(i).getX()+100&& errorList.get(i).getY()<=y&&y<=errorList.get(i).getY()+100) {
                        errorList.remove(i);//사라짐//그치만 안사라지넹
                        drawList.remove(i);
                        count--;
                        invalidate();
                        Log.v("태그","삭제가 실행되었습니다.");
                        return true;
                    }
                }
            }
            return false;
        }
    }

}
