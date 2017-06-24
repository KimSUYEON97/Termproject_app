package com.example.calic.catcherror;

import java.util.Random;

/**
 * Created by calic on 2017-06-13.
 */

public class Error {
    private int x,y;
    private int movement;
    /*public Error(){
        movement=0;
        x=0;
        y=0;
    }*/

    public Error(int movement,int width, int height){
        Random random=new Random();
        this.movement=movement;

        if(movement%4==0){
            x=0;
            do {
                y = random.nextInt(2000);
            }while(y>height);
        }
        if(movement%4==1){
            y=0;
            do {
                x = random.nextInt(2000);
            }while(x>width);
        }
        if(movement%4==2){
            x=width;
            do {
                y = random.nextInt(2000);
            }while(y>height);
        }
        if(movement%4==3){
            y=height;
            do {
                x = random.nextInt(2000);
            }while(x>width);
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
        y--;
    }
    public void move4(){//왼쪽 아래//3
        x--;
        y++;
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }

}
