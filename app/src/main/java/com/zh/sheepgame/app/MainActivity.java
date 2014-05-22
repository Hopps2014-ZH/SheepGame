package com.zh.sheepgame.app;

import android.app.Activity;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;

public class MainActivity extends Activity {

    private Handler frame = new Handler();
    //Divide the frame by 1000 to calculate how many times per second the screen will update.
    private static final int FRAME_RATE = 20; //50 frames per second

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Handler h = new Handler();
        //((Button)findViewById(R.id.the_button)).setOnClickListener(this);
        //We can't initialize the graphics immediately because the layout manager
        //needs to run first, thus we call back in a sec.
        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                initGfx();
            }
        }, 1000);
    }

    synchronized public void initGfx() {
        //Can add some graphics initialization here.
        //It's a good idea to remove any existing callbacks to keep
        //them from inadvertently stacking up.
        frame.removeCallbacks(frameUpdate);
        frame.postDelayed(frameUpdate, FRAME_RATE);
    }

    private Runnable frameUpdate = new Runnable() {
        @Override
        synchronized public void run() {    //This gets called every 20ms.
            frame.removeCallbacks(frameUpdate);
            GameBoard board = (GameBoard)findViewById(R.id.canvas);
            board.update();
            //***make any updates to on screen objects here***
            //then invoke the on draw by invalidating the canvas
            ((GameBoard)findViewById(R.id.canvas)).invalidate();    //forces the Gameboard to draw.
            frame.postDelayed(frameUpdate, FRAME_RATE);
        }
    };
}
