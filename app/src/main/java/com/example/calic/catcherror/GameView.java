package com.example.calic.catcherror;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Point;
import android.os.CountDownTimer;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

import java.util.List;

/**
 * Created by calic on 2017-06-05.
 */

public class GameView extends View {

    volatile boolean playing;
    //private Thread gameThread = null;
    private int width,height;
    private List<Error> errorList;
    private Character humanA;
    private int time=0,movement=0;
    public GameView(Context context){
        super(context);
        //화면 사이즈 구하기
        WindowManager manager=(WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
        Display display = manager.getDefaultDisplay();
        Point sizePoint = new Point();
        display.getSize(sizePoint);
        width=sizePoint.x;
        height = sizePoint.y;
        humanA.place(width,height);//캐릭터 위치 중심으로 고정
        new CountDownTimer(100*1000,1000){//1분간 플레이
            @Override
            public void onTick(long millisUntilFinished)
            {
                time++;
                if(time%5==0) {//5초마다 새로운 에러 발생

                    errorList.add(new Error(movement));
                    movement++;//방향설정을 위한 값
                }
            }

            @Override
            public void onFinish(){//끝내기

            }
        }.start();
    }

    protected void onDraw(Canvas canvas){//움직임 설정//1초에 60번 실행된다.

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
