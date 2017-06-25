package com.example.calic.catcherror;

import java.util.Random;

/**
 * Created by calic on 2017-06-13.
 */

public class Error {
    private int x,y;
    private int movement;
    private int loop;

    public Error(int movement,int width, int height){
        Random random=new Random();
        Random plus = new Random();
        this.movement=movement;
        loop=plus.nextInt(4);
        if(movement%4==0){
            x=0;
            do {
                y = random.nextInt(2000)+plus.nextInt(40);
            }while(y<height/2||y>height);
        }
        if(movement%4==1){
            y=0;
            do {
                x = random.nextInt(2000)+plus.nextInt(100);
            }while(x>width/2);
        }
        if(movement%4==2){//여러곳에서 버그 이미지가 안뜸 수정 필요
            x=width;
            do {
                y = random.nextInt(2000)+plus.nextInt(100);
            }while(y>height/2);
        }
        if(movement%4==3){
            y=height;
            do {
                x = random.nextInt(2000)+plus.nextInt(40);
            }while(x<width/2||x>width);
        }
    }
    //기본 움직임에서 무언가 물체에 부딪히면 가던 방향의 90도 꺽어서 이동하기??
    public void move1(){//오른쪽 위 대각선 방향으로 상승//0
        x++;
        y--;
    }
    public void move2(){//오른쪽 아래 대각선//1
        x++;
        y++;
    }
    public void move3(){//왼쪽 위//2
        x--;
        y++;
    }
    public void move4(){//왼쪽 아래//3
        x--;
        y--;
    }
    public void move5(){
        if(loop%4==1) {
            x++;
            loop++;
        }else if(loop%4==2){
            y--;
            loop+=2;
        }else if(loop%4==3){
            x-=2;
            loop-=2;
        }
        else{
            y+=2;
            loop--;
        }
    }
    public void move6(){
        if(loop%4==1) {
            y++;
            loop++;
        }else if(loop%4==2){
            x--;
            loop+=2;
        }else if(loop%4==3){
            y-=2;
            loop-=2;
        }
        else{
            x+=2;
            loop--;
        }

    }
    public void setMovement(int movement){
        this.movement=movement;
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public int getMovement(){return movement;}
}//update 다시 만들기, 라이프 조정 넣기, 버그이미지끼리 안겹치게 하기
