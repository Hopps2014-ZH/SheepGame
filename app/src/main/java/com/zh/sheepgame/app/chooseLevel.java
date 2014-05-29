package com.zh.sheepgame.app;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Paint;
import android.graphics.Typeface;
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
import android.widget.TextView;

import com.zh.sheepgame.app.GameBoard;
import com.zh.sheepgame.app.MainActivity;
import com.zh.sheepgame.app.R;

public class chooseLevel extends Activity {
    Typeface font, myriad;
    private Handler frame = new Handler();
    Intent backHome;
    Boolean playOn;
    //Divide the frame by 1000 to calculate how many times per second the screen will update.
    private static final int FRAME_RATE = 20; //50 frames per second

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chooselevel);
        Handler h = new Handler();
        backHome = new Intent(this, MainActivity.class);
        playOn = false;

        myriad = Typeface.createFromAsset(getAssets(),"fonts/myriad.otf");
        font = Typeface.createFromAsset(getAssets(),"fonts/font.ttf");
        final TextView title = (TextView) findViewById(R.id.textView);
        final TextView lev1 = (TextView) findViewById(R.id.textView3);
        final TextView lev2 = (TextView) findViewById(R.id.textView3);
        final TextView lev3 = (TextView) findViewById(R.id.textView3);
        title.setTypeface(myriad);
        title.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);






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
            if (playOn) {
                GameBoard board = (GameBoard) findViewById(R.id.canvas);
                board.update();
                //***make any updates to on screen objects here***
                //then invoke the on draw by invalidating the canvas
                ((GameBoard) findViewById(R.id.canvas)).invalidate();    //forces the Gameboard to draw.
                frame.postDelayed(frameUpdate, FRAME_RATE);
            }
        }
    };
}
