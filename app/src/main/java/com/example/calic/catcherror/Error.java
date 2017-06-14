package com.example.calic.catcherror;

import java.util.Random;

/**
 * Created by calic on 2017-06-13.
 */

public class Error {
    private float x,y;
    private int movement;

    public Error(int movement,int width, int height){
        Random random=new Random();
        this.movement=movement;

        if(movement%8==0&&movement%8==1){
            x=0;
            y=random.nextInt(2000)+1;
            while(y>height){
                y=random.nextInt(2000)+1;
            }
        }
        if(movement%8==2&&movement%8==3){
            y=0;
            x=random.nextInt(2000)+1;
            while(x>width){
                x=random.nextInt(2000)+1;
            }
        }
        if(movement%8==4&&movement%8==5){
            x=width;
            while(y>height){
                y=random.nextInt(2000)+1;
            }
        }
        if(movement%8==6&&movement%8==7){
            y=height;
            while(x>width){
                x=random.nextInt(2000)+1;
            }
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
    public float getX(){
        return x;
    }
    public float getY(){
        return y;
    }

}
