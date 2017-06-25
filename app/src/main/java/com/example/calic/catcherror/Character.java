package com.example.calic.catcherror;

/**
 * Created by calic on 2017-06-13.
 */

public class Character {
    private float lifegage=50;
    private float x,y;

    public float life(boolean Edead){
        if(lifegage==0){
            return 0;
        }
        if(Edead==true){
            lifegage-=0.5;
        }else if (Edead==false){
            if(lifegage<50) {
                lifegage+=0.5;
            }
        }
        return lifegage;
    }

    public Character(float width, float height){
        x=width;
        y=height;
    }

    public float getLifegage(){return lifegage;}
    public float getX(){
        return x;
    }
    public float getY(){
        return y;
    }
}
