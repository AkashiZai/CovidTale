package main;

import java.awt.*;
import java.awt.image.BufferedImage; // นำเข้า
import java.io.IOException;          // นำเข้า
import javax.imageio.ImageIO;        // นำเข้า
import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable{
    final int tile = 16;
    final int scale = 4;

    final int tileSize = tile * scale;
    final int maxScreenColumn = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenColumn;
    final int screenHeight = tileSize * maxScreenRow;

    Key keyH = new Key();
    Thread gameThread;

    int playerX = 100;
    int playerY = 100;
    int playerspeed = 4;

    // --- UPDATED: ส่วนที่เพิ่มเข้ามาสำหรับ Animation ---
    BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    String direction = "down"; // ทิศทางเริ่มต้น
    int spriteCounter = 0;     // ตัวนับเวลาสำหรับสลับภาพ
    int spriteNum = 1;         // เลขระบุภาพ (1 หรือ 2)
    // ------------------------------------------------

    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);

        getPlayerImage(); // เรียกโหลดรูปภาพเมื่อเริ่มเกม
    }

    // --- UPDATED: ฟังก์ชันโหลดรูปภาพ ---
    public void getPlayerImage() {
        try {
            // หมายเหตุ: ต้องแก้ path ให้ตรงกับที่คุณวางไฟล์จริง
            // เช่นถ้าไฟล์อยู่ใน src/player/ ก็ใช้ "/player/ชื่อไฟล์.png"

            // ชุดเดินลง (หน้าตรง)
            down1 = ImageIO.read(getClass().getResourceAsStream("/player/Nurse-16x16-Base-Folder91.png")); // ยืน
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/Nurse-16x16-Base-Folder95.png")); // เดิน

            // ชุดเดินขึ้น (หันหลัง)
            up1 = ImageIO.read(getClass().getResourceAsStream("/player/Nurse-16x16-Base-Folder92.png")); // ยืน
            up2 = ImageIO.read(getClass().getResourceAsStream("/player/Nurse-16x16-Base-Folder98.png")); // เดิน

            // ชุดเดินซ้าย
            left1 = ImageIO.read(getClass().getResourceAsStream("/player/Nurse-16x16-Base-Folder97.png")); // ยืน
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/Nurse-16x16-Base-Folder99.png")); // เดิน

            // ชุดเดินขวา
            right1 = ImageIO.read(getClass().getResourceAsStream("/player/Nurse-16x16-Base-Folder93.png")); // ยืน
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/Nurse-16x16-Base-Folder94.png")); // เดิน

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000 / 60;
        double nextDrawTime = System.nanoTime() + drawInterval;

        while (gameThread != null) {

            update();
            repaint();

            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime / 1000000;

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
        // เช็คว่ามีการกดปุ่มหรือไม่ ถ้ากดปุ่มถึงจะอัปเดต Animation
        if (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) {

            if (keyH.upPressed) {
                direction = "up";
                playerY -= playerspeed;
            }
            else if (keyH.downPressed) {
                direction = "down";
                playerY += playerspeed;
            }
            else if (keyH.leftPressed) {
                direction = "left";
                playerX -= playerspeed;
            }
            else if (keyH.rightPressed) {
                direction = "right";
                playerX += playerspeed;
            }

            // --- UPDATED: Logic สลับ Animation ---
            spriteCounter++;
            if (spriteCounter > 12) { // เลข 12 คือความเร็วในการสลับภาพ (ยิ่งน้อยยิ่งสลับไว)
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
        // ถ้าต้องการให้หยุดเดินแล้วกลับมาเป็นท่ายืนเสมอ ให้เพิ่ม else ตรงนี้แล้ว set spriteNum = 1;
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        // --- UPDATED: เปลี่ยนจากวาดสี่เหลี่ยม เป็นวาดรูปภาพ ---
        BufferedImage image = null;

        switch(direction) {
            case "up":
                if (spriteNum == 1) image = up1;
                if (spriteNum == 2) image = up2;
                break;
            case "down":
                if (spriteNum == 1) image = down1;
                if (spriteNum == 2) image = down2;
                break;
            case "left":
                if (spriteNum == 1) image = left1;
                if (spriteNum == 2) image = left2;
                break;
            case "right":
                if (spriteNum == 1) image = right1;
                if (spriteNum == 2) image = right2;
                break;
        }

        // วาดภาพลงบนจอ
        g2.drawImage(image, playerX, playerY, tileSize, tileSize, null);

        // g2.setColor(Color.white); // ของเก่า
        // g2.fillRect(playerX,playerY,tileSize,tileSize); // ของเก่า

        g2.dispose();
    }
}