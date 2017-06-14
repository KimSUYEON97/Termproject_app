package com.example.calic.catcherror;

/**
 * Created by calic on 2017-06-13.
 */

public class Character {
    private int lifegage=100;
    private float x,y;

    public int life(boolean Edead){
        if(lifegage==0){
            return 0;
        }
        if(Edead){
            lifegage--;
        }
        return lifegage;
    }

    public Character(float width, float height){
        x=width;
        y=height;
    }
    public void replace(int width, int height){
        x=width/2;
        y=height/2;
    }
    public float getX(){
        return x;
    }
    public float getY(){
        return y;
    }
}
