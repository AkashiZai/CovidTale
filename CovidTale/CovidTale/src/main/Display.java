package main;

import javax.swing.*;
import java.awt.*;

public class Display extends GamePanel{
    private String title;

    public Display(String title){ //เอาไว้เรียกใช้ทุกอย่าง
        createDisplay(title);
    }

    private void createDisplay(String title){ //สร้างจอภาพ + GamePanel
        JFrame d = new JFrame();
        GamePanel g = new GamePanel();

        d.add(g);
        d.pack();

        g.setBackground(Color.black);
        d.setTitle(title);
        d.setLocationRelativeTo(null);
        d.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        d.setVisible(true);
    }




}
