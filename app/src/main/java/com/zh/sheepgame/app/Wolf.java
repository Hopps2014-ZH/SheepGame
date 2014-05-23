package com.zh.sheepgame.app;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.ArrayList;

/**
 * Created by zander_bobronnikov on 5/22/14.
 */
public class Wolf {
    int wX;
    int wY;
    int sheepX;
    int sheepY;
    int hX;
    Boolean stayX;

    public Wolf(int sx, int sy, int h, int w){

        wX = w/2;
        stayX = false;

    }
    public void draw(Canvas c, Paint p){
        p.setColor(Color.RED);
        c.drawCircle(wX,wY,15,p);
    }


    public void update(int sx, int sy, ArrayList<Fence> fences, int h, int w){

        for (int i = 0; i < fences.size(); i++ ){
            Fence f = fences.get(i);
            int y = f.getFenceY();
            int fH = f.getfHeight();

            hX = f.holeX + f.holeGap/2;
        }

            if (wX <hX+8 && wX>hX-8){

            }
            else if(wX<hX)
                wX += 7;
            else if(wX>hX)
                wX -= 7;




        wY = h-45;

    }




    public int getwX(){return wX;}
    public int getwY(){return wY;}
}
