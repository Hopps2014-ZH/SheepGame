package com.zh.sheepgame.app;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class GameBoard extends View implements SensorEventListener{
    private Paint p;
    private SensorManager sensorManager;
    private Sensor Accelerometer;
    float accX;
    float vX;
    float sY;
    float sX;
    int time;
    Boolean lose, firstUpdate, firstDraw;
    ArrayList<Fence>fences;
    Bitmap resetBtn, fenceIMG;
  //  ArrayList<Wolf>wolves;


    //This executes once, at the beginning of the app.
    public GameBoard(Context context, AttributeSet aSet) {  //constructor...
        super(context, aSet);

        sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        Accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(this,Accelerometer,SensorManager.SENSOR_DELAY_GAME);
        fences = new ArrayList<Fence>();


        resetBtn = BitmapFactory.decodeResource(getResources(),R.drawable.resetbutton);
        fenceIMG = BitmapFactory.decodeResource(getResources(),R.drawable.fence);


        p = new Paint();
        sY = (float)450.0;
        sX= 400;
        time = 0;
        lose = false;
  //      wolves = new ArrayList<Wolf>();
        firstUpdate= true;
        firstDraw = true;





        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                    float tX = motionEvent.getX();
                    float tY = motionEvent.getY();
                    if (lose){
                        if(tX>getWidth()/2-resetBtn.getWidth()/2 && tX<getWidth()/2+resetBtn.getWidth()/2 &&
                           tY>getHeight()/2-resetBtn.getHeight()/2 && tY<getHeight()/2+resetBtn.getHeight()/2){
                            reset();
                        }
                    }
                }
                return true;  //SHOULD BE TRUE.  NOT SURE WHY ;)
            }
        });
    }

    //DOES ALL THE DRAWING...
    synchronized public void onDraw(Canvas canvas) {
        p.setColor(Color.BLACK);
        p.setAlpha(255);
        p.setStrokeWidth(1);
        p.setTextSize(75);
        p.setTypeface(((MainActivity)this.getContext()).font);

        if (firstDraw){
//            Wolf wolf = new Wolf((int)sX,(int)sY,(getHeight()), getWidth());
//            wolves.add(wolf);
            firstDraw = false;
        }
        if (!lose) {
            p.setColor(Color.BLUE);
            canvas.drawCircle(sX, sY, 20, p);

//        Wolf wolf = wolves.get(0);
//        wolf.draw(canvas,p);

            for (int i = 0; i < fences.size(); i++) {
                Fence f = fences.get(i);
                f.draw(canvas, p);
            }


        }else{


            p.setColor(Color.LTGRAY);
            p.setTextAlign(Paint.Align.CENTER);
            canvas.drawText("Game Over!", getWidth()/2, getHeight()/6, p);
            canvas.drawBitmap(resetBtn, getWidth()/2-resetBtn.getWidth()/2, getHeight()/2,p);
        }

    }



    //This will get called everytime there is a frame update, right before the drawing.
    synchronized public void update(){
    sX+=vX;
    if (firstUpdate){

        firstUpdate = false;
    }
//    Wolf wolf = wolves.get(0);
//    int width = getWidth();
  //  wolf.update((int)sX,(int)sY,fences,getHeight(), width);



    if(time>70){
        int w = getWidth();
        int h = getHeight();
        int Rand = (int)(Math.random()*100);
        int holeX = (int)(w/8 + (Math.random()*(w-w/8)));
        int speed = 5;
        int type = 1;
        if(Rand<33){
            speed = 4;
            type = 1;
        }else if(Rand<66){
            speed = 4;
            type = 2;
        }else{
            speed=6;
            type = 3;
        }

        Fence f = new Fence(h,w,holeX,speed,type);
        fences.add(f);
        time = 0;
    }

    for(int i = 0; i < fences.size(); i++){
        Fence f = fences.get(i);
        f.update();

        Rect sheepBox = new Rect((int)(sX-10),(int)(sY-10),(int)(sX+10),(int)(sY+10));
        if ((sheepBox.intersect(f.getLeftRect()) || sheepBox.intersect(f.getRightRect())) && (sY)>=(f.getFenceY()+f.getfHeight()-8)){
            sY=f.getFenceY() + f.getfHeight() +20;
        }

        if (f.getFenceY()>getHeight()){
            fences.remove(f);
        }

    }

    if(sY>getHeight()){
        lose = true;
    }

    time++;


    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        accX = sensorEvent.values[0];
        if (!((accX>=0 && sX<getWidth()/8) ||(accX<=0 && sX+20>7*(getWidth()/8)))){
        vX = -4*accX;

        }else{
            vX = 0;
        }
    }
    public void reset(){
        lose = false;
        fences.clear();
        sX = getWidth()/2;
        sY = getHeight()/2;
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

}