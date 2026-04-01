package com.pong.engine;
import java.awt.*;
import java.awt.event.*;

public class Paddle {
    //position
    private final int x;
    private int y = (Game.HEIGHT / 2) - 50;
    private int with = 10;
    private int height = 100;
    public int vy=4;//velocity move
    private boolean upPressed = false;
    private boolean downPressed = false;

    public Paddle(int x){
        this.x=x;
    }
    public int getX(){
        return x;
    }
    public int gety(){
        return y;
    }
    public int getWith(){
        return with;
    }
    public int getHeight(){
        return height;
    }

    public void draw(Graphics g){
        g.setColor(Color.WHITE);
        g.fillRect(x, y, with, height);
    }

    public void playerM(){
        //adding the methods to move the paddle but avoiding pass the borders
        if (upPressed && y > 0) {//up
            y -= vy;
        }
        if (downPressed && y < Game.HEIGHT - height) {//down
            y += vy;
        }
    }

    public void setMovingUp(boolean moving) { this.upPressed = moving; }
    public void setMovingDown(boolean moving) { this.downPressed = moving; }

    public void botM(Ball ball) {
        // Calculate the center of the paddle
        int paddleCenter = y + (height / 2);

        // Move towards the ball's Y position
        if (ball.getY() > paddleCenter) {
            y += vy;
        } else if (ball.getY() < paddleCenter) {
            y -= vy;
        }

        // Keep the paddle inside the screen bounds
        if (y < 0) y = 0;
        if (y > Game.HEIGHT - height) y = Game.HEIGHT - height;
    }
}


