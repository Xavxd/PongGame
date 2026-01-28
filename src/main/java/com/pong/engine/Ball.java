package com.pong.engine;

import java.util.Random;
import java.awt.*;

public class Ball {
    //position
    private int x=Game.WIDTH/2,y=Game.HEIGHT/2;//initial position
    private int vx,vy;//velocity on axes
    private int speed=5;
    private int size=15;
    private Random r = new Random();
    //private int n=r.nextInt(-4,5)*2-1;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Ball(){
        //move();
        vx = r.nextBoolean() ? speed : -speed;
        vy = r.nextBoolean() ? speed : -speed;
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
        if(y >= Game.HEIGHT - size || y<= 0) {
            changeyDir();
        }

        if(x>= Game.WIDTH - size || x<=0){
            changexDir();;
        }
    }

}
