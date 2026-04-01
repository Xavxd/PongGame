package com.pong.engine;

import java.util.Random;
import java.awt.*;

public class Ball {
    //position
    private int x=Game.WIDTH/2,y=Game.HEIGHT/2;//initial position
    private int vx,vy;//velocity on axes
    private final int speed=5;
    private final int size=15;
    private final Random r = new Random();//initial movement

    public Ball(){
        vx = r.nextBoolean() ? speed : -speed;
        vy = r.nextBoolean() ? speed : -speed;
    }

    public int getSize(){
        return size;
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }

    public void draw(Graphics g){
        g.setColor(Color.WHITE);
        g.fillOval(x,y,size,size);
    }

    public void changexDir(){
        vx *=-1;
    }

    public void changeyDir(){
        vy *=-1;
    }

    public void move(){
        x+=vx;
        y+=vy;

        //for limits up and down
        if(y >= Game.HEIGHT - (size*4) || y<= 4) {
            changeyDir();
        }
        //for limits on both sides
        if(x>= Game.WIDTH - size ){
            Game.winner=true;

        }

        if( x<=size-20){
            Game.gameover=true;
        }
    }

}
