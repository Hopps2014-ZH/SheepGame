package com.zh.sheepgame.app;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * Created by Zander on 5/21/14.
 */
public class Fence {
    int holeX;
    int fenceY;
    int holeSpeed;
    int fenceType;
    int fenceDelay;
    int holeGap;
    int fHeight;
    int h, w;
    Rect leftRect, rightRect;
    Bitmap fence;



    public Fence(int height, int width, int initHoleX, int speed, int type, int time){
        holeX = initHoleX;
        fenceType = type;
        holeSpeed = speed;
        fHeight = 45;
        h=height;
        w=width;
        holeGap = (int)(w*.23);
        fenceDelay = time;
        leftRect = new Rect(0,fenceY,holeX,fenceY+fHeight);
        rightRect = new Rect(holeX+holeGap,fenceY,w,fenceY+fHeight);
        fenceY=-fHeight;



    }

    public void update(){
        fenceY+=6;

        if (!((holeSpeed<=0 && holeX<w/8) ||(holeSpeed>=0 && holeX+holeGap>7*(w/8)))){
            holeX+=holeSpeed;

        }else{
            holeSpeed = -holeSpeed;
        }


        leftRect = new Rect(0,fenceY,holeX,fenceY+fHeight);
        rightRect = new Rect(holeX+holeGap,fenceY,w,fenceY+fHeight);
    }

    public void draw(Canvas c, Paint p){

        p.setColor(0xFF996600);
        c.drawRect(leftRect,p);
        c.drawRect(rightRect,p);

    }







    public int getHoleX(){return holeX;}
    public int getholeSpeed(){return holeSpeed;}
    public int getFenceType(){return fenceType;}
    public int getfHeight(){return fHeight;}
    public int getFenceY(){return fenceY;}
    public Rect getLeftRect(){return leftRect;}
    public Rect getRightRect(){return rightRect;}
    public int getFenceDelay(){return fenceDelay;}

}
