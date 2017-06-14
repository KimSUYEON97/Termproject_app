package com.example.calic.catcherror;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.os.CountDownTimer;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by calic on 2017-06-05.
 */

public class GameView extends View {

    //volatile boolean playing;
    //private Thread gameThread = null;
    private int width,height;
    ArrayList<Error> errorList = new ArrayList<Error>();
    Character humanA = new Character(0,0);
    private int time=0,movement=0;
    private Bitmap Err;
    private Bitmap Char;
    public GameView(Context context){
        super(context);
        //화면 사이즈 구하기
        WindowManager manager=(WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
        Display display = manager.getDefaultDisplay();
        Point sizePoint = new Point();
        display.getSize(sizePoint);

        width=sizePoint.x;
        height = sizePoint.y;
        humanA.replace(width,height);
        Char = BitmapFactory.decodeResource(getResources(), R.drawable.holes);
        Err = BitmapFactory.decodeResource(getResources(), R.drawable.bugimage);

        new CountDownTimer(100*1000,1000){//1분간 플레이
            @Override
            public void onTick(long millisUntilFinished)
            {
                Random random =new Random();
                int num = random.nextInt(20)+10;
                time++;
                if(time%5==0) {//5초마다 새로운 에러 발생
                    for(int i=0;i<num;i++) {
                        errorList.add(new Error(movement, width, height));
                    }
                    movement++;//방향설정을 위한 값
                }
            }

            @Override
            public void onFinish(){//끝내기

            }
        }.start();
    }

    protected void onDraw(Canvas canvas){//움직임 설정//1초에 60번 실행된다.
        canvas.drawBitmap(Char,humanA.getX() ,humanA.getY(), null);
        for (int i=0;i<errorList.size();i++) {
            canvas.drawBitmap(Err, errorList.get(i).getX(), errorList.get(i).getX(), null);
        }
        super.onDraw(canvas);
        update();
    }

    private void update(){//움직임 변화
        for(int i=0;i<errorList.size();i++){
            if(movement%4==0){
                if(errorList.get(i).getX()==width||errorList.get(i).getY()==height){
                    movement++;
                }else {
                    errorList.get(i).move1();
                }
            }
            if(movement%4==1){
                if(errorList.get(i).getX()==width||errorList.get(i).getY()==height){
                    movement++;
                }else {
                    errorList.get(i).move2();
                }
            }
            if(movement%4==2){
                if(errorList.get(i).getX()==width||errorList.get(i).getY()==height){
                    movement++;
                }else {
                    errorList.get(i).move3();
                }
            }
            if(movement%4==3){
                if(errorList.get(i).getX()==width||errorList.get(i).getY()==height){
                    movement++;
                }else {
                    errorList.get(i).move4();
                }
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        super.onTouchEvent(event);

        if(event.getAction()==MotionEvent.ACTION_DOWN){
            float x= event.getX();
            float y= event.getY();
            for (int i=0;i<errorList.size();i++) {
                if (errorList.get(i).getX() == x && errorList.get(i).getY()==y) {
                    errorList.remove(i);//사라짐
                    return true;
                }
            }
        }
        if(event.getAction()==MotionEvent.ACTION_UP){
        }
        if(event.getAction()==MotionEvent.ACTION_MOVE){
        }
        return false;
    }

    //SystemClock.sleep(50)시간 딜레이

    //기본 움직임에서 무언가 물체에 부딪히면 가던 방향의 90도 꺽어서 이동하기??

}
