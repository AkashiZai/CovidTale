package main;

import java.awt.*;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable{
    final int tile = 16; // 16*16
    final int scale = 4;

    final int tileSize = tile * scale; // 64*64 tiles
    final int maxScreenColumn = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenColumn;
    final int screenHeight = tileSize * maxScreenRow;

    Thread gameThread;

    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setDoubleBuffered(true);
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void run(){
        while(gameThread != null){
            update();
        }
    }

    public void update(){

    }




}
