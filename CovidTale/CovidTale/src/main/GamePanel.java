package main;

import java.awt.*;
import javax.swing.JPanel;
import javax.swing.plaf.basic.BasicTreeUI;

public class GamePanel extends JPanel implements Runnable{
    final int tile = 16; // 16*16
    final int scale = 4;

    final int tileSize = tile * scale; // 64*64 tiles
    final int maxScreenColumn = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenColumn;
    final int screenHeight = tileSize * maxScreenRow;

    Key keyH = new Key();

    Thread gameThread;

    //set default please default position
    int playerX = 100;
    int playerY = 100;
    int playerspeed = 4;

    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000 / 60; // 60 FPS
        double nextDrawTime = System.nanoTime() + drawInterval;

        while (gameThread != null) {

            update();
            repaint();

            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime / 1000000; // Convert nanoseconds to milliseconds

                if (remainingTime < 0) {
                    remainingTime = 0;
                }

                Thread.sleep((long) remainingTime);

                nextDrawTime += drawInterval;

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void update() {
        // CHANGED: Removed 'else' so you can move diagonally
        if (keyH.upPressed == true) {
            playerY -= playerspeed;
        }
        if (keyH.downPressed == true) {
            playerY += playerspeed;
        }
        // FIXED: Changed '=' to '=='
        if (keyH.leftPressed == true) {
            playerX -= playerspeed;
        }
        if (keyH.rightPressed == true) {
            playerX += playerspeed;
        }
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(Color.white);
        g2.fillRect(playerX,playerY,tileSize,tileSize);
        g2.dispose();
    }
}
