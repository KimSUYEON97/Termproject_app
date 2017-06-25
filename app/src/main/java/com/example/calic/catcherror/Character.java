package com.example.calic.catcherror;

/**
 * Created by calic on 2017-06-13.
 */

public class Character {
    private int lifegage=50;
    private float x,y;

    public int life(boolean Edead){
        if(lifegage==0){
            return 0;
        }
        if(Edead==true){
            lifegage--;
        }else if (Edead==false){
            if(lifegage<50) {
                lifegage++;
            }
        }
        return lifegage;
    }

    public Character(float width, float height){
        x=width;
        y=height;
    }

    public int getLifegage(){return lifegage;}
    public float getX(){
        return x;
    }
    public float getY(){
        return y;
    }
}
