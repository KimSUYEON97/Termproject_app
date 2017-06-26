package com.example.calic.catcherror;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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

    private int width,height;
    ArrayList<Error> errorList = new ArrayList<Error>();
    ArrayList<GraphicObject> drawList = new ArrayList<GraphicObject>();
    Character humanA = new Character(800,450);
    CountDownTimer Countdown = null;
    private int time=0,movement=0,count =0,catchs=0,all=0,max_ne=10;//시간, 움직임 변화, list길이, 에러잡은개수,에러발생개수
    private Bitmap Err1,Err2,Err3,nErr;
    private Bitmap Char;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Char = BitmapFactory.decodeResource(getResources(), R.drawable.noname);
        nErr = BitmapFactory.decodeResource(getResources(), R.drawable.notbug);
        Err1 = BitmapFactory.decodeResource(getResources(), R.drawable.bug1);
        Err2 = BitmapFactory.decodeResource(getResources(), R.drawable.bug2);
        Err3 = BitmapFactory.decodeResource(getResources(), R.drawable.bug3);

        View view = new MyView(this);
        setContentView(view);

        Countdown = new CountDownTimer(60*1000,1000){//1분간 플레이
            @Override
            public void onTick(long millisUntilFinished)
            {
                Random random =new Random();
                int e_num = random.nextInt(20)+10;
                int ne_num;
                if(max_ne<=0){
                    ne_num=0;
                }else{
                    ne_num=random.nextInt(3)+2;
                }
                if(time%10==1) {//10초마다 새로운 에러 발생
                    int i;
                    for(i=0;i<e_num-ne_num;i++) {
                        errorList.add(new Error(0,movement, width,height));//에러 추가
                        if(movement%3==1) {
                            drawList.add(new GraphicObject(Err1));
                        }if(movement%3==2) {
                            drawList.add(new GraphicObject(Err2));
                        }if(movement%3==0) {
                            drawList.add(new GraphicObject(Err3));
                        }
                        drawList.get(i+count).SetPosition(errorList.get(i+count).getX(),errorList.get(i+count).getY());
                        Log.v("태그","에러가 생성되었습니다");
                        movement++;//방향설정을 위한 값
                    }for(;i<e_num;i++) {
                        errorList.add(new Error(1, movement, width, height));//에러 추가
                        drawList.add(new GraphicObject(nErr));
                        drawList.get(i+count).SetPosition(errorList.get(i+count).getX(),errorList.get(i+count).getY());
                        movement++;
                    }
                    max_ne-=ne_num;
                    count=count+e_num;
                    all=all+(e_num-ne_num);
                }
                for(int i=0;i<count;i++) {
                    if(errorList.get(i).getDiffer()==0) {
                        if (errorList.get(i).getX() <= humanA.getX() + 300 && humanA.getX() <= errorList.get(i).getX()
                                && errorList.get(i).getY() <= humanA.getY() + 300 && humanA.getY() <= errorList.get(i).getY()) {
                            if (humanA.life(true) == 0) {
                                onFinish();
                                cancel();
                                break;
                            }
                        }
                    }
                }
                time++;
            }

            @Override
            public void onFinish(){//끝내기

                String score = String.valueOf((int) (((double) catchs / all) * 100));
                String lastlife = String.valueOf((int) (humanA.getLifegage() * 2));
                Intent intent = new Intent(getApplicationContext(), EndActivity.class);
                intent.putExtra("score", score);
                intent.putExtra("lastlife", lastlife);
                startActivity(intent);
                finish();
            }
        }.start();

    }

    @Override
    protected void onPause(){
        super.onPause();
    }

    @Override
    protected void onResume(){
        super.onResume();
    }

    @Override
    public void onBackPressed() {
        onPause();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Restart?")
                .setCancelable(false)
                .setPositiveButton("Yes",new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int id){
                        dialog.cancel();//다시시작
                        onResume();
                    }
                })
                .setNegativeButton("No",new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int id){
                        Countdown.cancel();
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                        finish();//play화면 종료
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

        protected void onDraw(Canvas canvas){//움직임 그리기
            canvas.drawBitmap(Char,humanA.getX(),humanA.getY(),null);
            for(int i=0;i<count;i++){
                drawList.get(i).Draw(canvas);
            }
            invalidate();
            update();//움직임 업데이트
            Log.v("태그","update가 실행되었습니다.");
        }

        private void update(){//움직임 변화
            for(int i=0;i<count;i++){
                if(errorList.get(i).getMovement()%4==0){
                    if (errorList.get(i).getX() == width || errorList.get(i).getY() == 0) {
                        errorList.get(i).setMovement(errorList.get(i).getMovement() + 1);
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
        public boolean onTouchEvent(MotionEvent event){
            super.onTouchEvent(event);
            if(event.getAction()==MotionEvent.ACTION_DOWN){
                float x= event.getX();//화면 터치했을때 터치한 위치 좌표
                float y= event.getY();
                Log.v("태그","touch가 실행되었습니다.");
                for (int i=0;i<count;i++) {
                    if (errorList.get(i).getX() <= x &&x<=errorList.get(i).getX()+200
                            && errorList.get(i).getY()<=y &&y<=errorList.get(i).getY()+200) {
                        if(errorList.get(i).getDiffer()==1) {
                            for(int j=0;j<3;j++) {
                                errorList.add(new Error(0, movement, width, height));//에러 추가
                                errorList.get(j + count).resetXY(errorList.get(i).getX(), errorList.get(i).getY());
                                if (movement % 3 == 1) {
                                    drawList.add(new GraphicObject(Err1));
                                }
                                if (movement % 3 == 2) {
                                    drawList.add(new GraphicObject(Err2));
                                }
                                if (movement % 3 == 0) {
                                    drawList.add(new GraphicObject(Err3));
                                }
                                drawList.get(j + count).SetPosition(errorList.get(j + count).getX(), errorList.get(j+count).getY());
                                Log.v("태그", "에러가 생성되었습니다");
                                movement++;//방향설정을 위한 값
                                humanA.life(true);
                            }
                            count+=3;
                        }
                        if(errorList.get(i).getDiffer()==0) {
                            humanA.life(false);
                        }
                        errorList.remove(i);//사라짐
                        drawList.remove(i);

                        count--;
                        catchs++;
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
