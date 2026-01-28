package com.pong.engine;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;

public class Game extends JPanel {
    /*Here will go all the logic and limits variables*/
    private Ball ball;
    private Timer timer;
    private static final long serialVersionUID = 1L;
    public static final int WIDTH = 1000;
    public static final int HEIGHT = WIDTH * 9 / 16;


    public Game(int width, int height) {
        // Set the size of the drawing area
        this.setPreferredSize(new Dimension(width, height));
        this.setBackground(Color.BLACK); // Pong is usually black!
        this.ball = new Ball();

        // ~60 FPS (1000ms / 16 ≈ 60)
        timer = new Timer(16, e -> {
            ball.move(); // move ball
            repaint();    // redraw
        });

        timer.start();
    }



    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g); // Important: This clears the screen
        ball.draw(g);
        //ball.move();
    }
}
