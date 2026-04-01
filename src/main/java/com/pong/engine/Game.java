package com.pong.engine;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.*;

public class    Game extends JPanel {
    /*Here will go all the logic and limits variables*/
    public int score;
    public static boolean gameover=false;
    public static boolean winner=false;
    private Ball ball;
    private final Paddle padOne,padTwo;
    private final Timer timer;
    private static final long serialVersionUID = 1L;
    public static final int WIDTH = 1000;
    public static final int HEIGHT = WIDTH * 9 / 16;

    public Game(int width, int height) {
        // Set the size of the drawing area
        this.setPreferredSize(new Dimension(width, height));
        this.setBackground(Color.BLACK); // color for the window
        this.setFocusable(true);
        this.requestFocusInWindow();
        this.ball = new Ball();
        this.padOne= new Paddle(0);//paddle for player one
        this.padTwo= new Paddle(Game.WIDTH-10);//paddle for player two

        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_UP) padOne.setMovingUp(true);
                if (e.getKeyCode() == KeyEvent.VK_DOWN) padOne.setMovingDown(true);
                if(e.getKeyCode()==KeyEvent.VK_R){
                    resetGame();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_UP) padOne.setMovingUp(false);
                if (e.getKeyCode() == KeyEvent.VK_DOWN) padOne.setMovingDown(false);
            }
        });

        // ~60 FPS (1000ms / 16 ≈ 60)
        timer = new Timer(16, e -> {
            ball.move(); // move ball
            padOne.playerM();//player moves
            padTwo.botM(ball);//bot playing
            Rectangle bRect= new Rectangle(ball.getX(),ball.getY(),ball.getSize(),ball.getSize());
            Rectangle padOneR= new Rectangle(padOne.getX(),padOne.gety(),padOne.getWith(),padOne.getHeight());
            Rectangle padTwoR= new Rectangle(padTwo.getX(),padTwo.gety(),padTwo.getWith(),padTwo.getHeight());

            if(bRect.intersects(padOneR)||bRect.intersects(padTwoR)){
                ball.changexDir();
                if(bRect.intersects(padOneR))score+=1;
            }
            repaint();    // redraw
        });

        timer.start();
    }

    public void resetGame(){
        gameover=false;
        winner=false;
        score=0;
        this.ball = new Ball();//restart the position of the ball
        timer.start();
        repaint();
    }

    public void drawText(Graphics g, String msg, Color c, Font f, int x, int y) {
        g.setColor(c); g.setFont(f); g.drawString(msg, x, y);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // Important: This clears the screen
        String scre="Score: "+Integer.toString(score);
        Font f= new Font("Arial", Font.BOLD, 50);
        int tpx=Game.WIDTH / 2 - 150;
        int tpy=Game.HEIGHT / 2;
        drawText(g,scre,Color.WHITE,new Font("Arial", Font.ITALIC, 20),30,30);
        if(Game.winner){
            drawText(g,"NICE GAME",Color.GREEN,f, tpx,tpy);
            timer.stop();
        }else if(Game.gameover){
            drawText(g,"GAME OVER",Color.RED,f, tpx,tpy);
            drawText(g,"Press r to try again",Color.WHITE, new Font("Arial", Font.ITALIC, 25),
                    Game.WIDTH / 2 - 105,(Game.HEIGHT/2)+50);
            timer.stop();
        }else {
            ball.draw(g);
            padOne.draw(g);
            padTwo.draw(g);
            Toolkit.getDefaultToolkit().sync();//improve the frames for ubuntu
        }
    }

}
