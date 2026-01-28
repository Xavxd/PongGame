package com.pong.engine;

import javax.swing.JFrame;
import java.awt.*;

public class Window extends JFrame {
    public Window(String title, int width, int height) {
        this.setTitle(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(width, height);

        // 1. Create the panel
        Game gamePanel = new Game(width, height);

        // 2. Add the panel to the window
        this.add(gamePanel);

        // 3. Size the window to fit the panel perfectly
        this.pack();

        this.setLocationRelativeTo(null); // Centers the window on the screen
        this.setVisible(true);
    }
}
