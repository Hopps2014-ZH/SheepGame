package com.zh.sheepgame.app;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by henry_price on 5/27/14.
 */
public class Levels {

    int level;
    Fence currFence;
    int fenceNum;
    ArrayList<Fence>fences;
    Boolean first;
    int numberFences;


    /*TODO: Add some blank array for the fences of the current level
    Zander be basic yooool
     */
    public Levels (int levela){
        level = levela;
        fenceNum = 0;
        fences = new ArrayList<Fence>();
        first = true;

    }
    public void update(int h, int w){
        if (level == 1 && first){
            level1(h,w);
            first = false;
        }
        if (level == 2 && first){
            level2(h,w);
            first = false;
        }


    }
    public void level1(int h, int w){
        fences.clear();

        Fence a = new Fence(h,w,w/4,2,1);
        Fence b = new Fence(h,w,3*(w/4),2,1);
        Fence c = new Fence(h,w,w/2,2,1);
        fences.add(a);
        fences.add(b);
        fences.add(c);
        numberFences=3;




    }
    public void level2(int h, int w){

    }
    public Fence call(){
        currFence = fences.get(fenceNum);
        fenceNum++;
        return currFence;
    }
    public int getNumberFences(){return numberFences;}


}
