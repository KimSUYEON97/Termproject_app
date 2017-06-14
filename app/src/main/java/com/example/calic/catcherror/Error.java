package com.example.calic.catcherror;

/**
 * Created by calic on 2017-06-13.
 */

public class Error {
    private int x,y;
    private int movement;

    public Error(int movement){
        this.movement=movement;

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
