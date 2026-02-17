package main;

import javax.swing.*;
import java.awt.*;

public class Display { // REMOVED "extends GamePanel"

    public Display(String title){
        createDisplay(title);
    }

    private void createDisplay(String title){
        JFrame d = new JFrame();

        // This is the GamePanel that will actually be on screen
        GamePanel g = new GamePanel();

        d.add(g);
        d.pack(); // Important: This tells the Window to resize to fit the GamePanel

        g.setBackground(Color.black); // Make background black so you can see the white player
        d.setTitle(title);
        d.setResizable(false); // Usually good for 2D games
        d.setLocationRelativeTo(null);
        d.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        d.setVisible(true);
        g.startGameThread();
        g.requestFocusInWindow();
    }
}