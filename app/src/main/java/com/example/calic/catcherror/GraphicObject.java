package com.example.calic.catcherror;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * Created by calic on 2017-06-24.
 */

public class GraphicObject {
    private Bitmap m_bitmap;
    private int m_x;
    private int m_y;

    public GraphicObject(Bitmap bitmap){
        m_bitmap=bitmap;
        m_x=0;
        m_y=0;
    }

    public void Draw(Canvas canvas){
        canvas.drawBitmap(m_bitmap,m_x,m_y,null);
    }

    public void SetPosition(int x,int y){
        m_y=y;
        m_x=x;
    }

    public int GetX(){
        return m_x;
    }

    public int GetY(){
        return m_y;
    }
}
