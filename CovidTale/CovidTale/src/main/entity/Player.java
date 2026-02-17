package main.entity;

import main.GamePanel;
import main.Key;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {

    GamePanel gp;
    Key keyH;

    public Player(GamePanel gp, Key keyH) {
        this.gp = gp;
        this.keyH = keyH;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        x = 100;
        y = 100;
        speed = 4;
        direction = "down";
    }

    public void getPlayerImage() {
        try {
            down1 = ImageIO.read(getClass().getResourceAsStream("/main/res/Nurse-16x16-Base-Folder34.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/main/res/Nurse-16x16-Base-Folder35.png"));

            // เดินขึ้น (หันหลัง)
            up1 = ImageIO.read(getClass().getResourceAsStream("/main/res/Nurse-16x16-Base-Folder34.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/main/res/Nurse-16x16-Base-Folder35.png"));

            // เดินซ้าย
            left1 = ImageIO.read(getClass().getResourceAsStream("/main/res/Nurse-16x16-Base-Folder19.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/main/res/Nurse-16x16-Base-Folder20.png"));
            left3 = ImageIO.read(getClass().getResourceAsStream("/main/res/Nurse-16x16-Base-Folder21.png"));
            left4 = ImageIO.read(getClass().getResourceAsStream("/main/res/Nurse-16x16-Base-Folder22.png"));
            left5 = ImageIO.read(getClass().getResourceAsStream("/main/res/Nurse-16x16-Base-Folder23.png"));
            left6 = ImageIO.read(getClass().getResourceAsStream("/main/res/Nurse-16x16-Base-Folder24.png"));
            left7 = ImageIO.read(getClass().getResourceAsStream("/main/res/Nurse-16x16-Base-Folder25.png"));

            // เดินขวา
            right1 = ImageIO.read(getClass().getResourceAsStream("/main/res/Nurse-16x16-Base-Folder26.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/main/res/Nurse-16x16-Base-Folder27.png"));
            right3 = ImageIO.read(getClass().getResourceAsStream("/main/res/Nurse-16x16-Base-Folder28.png"));
            right4 = ImageIO.read(getClass().getResourceAsStream("/main/res/Nurse-16x16-Base-Folder29.png"));
            right5 = ImageIO.read(getClass().getResourceAsStream("/main/res/Nurse-16x16-Base-Folder30.png"));
            right6 = ImageIO.read(getClass().getResourceAsStream("/main/res/Nurse-16x16-Base-Folder31.png"));
            right7 = ImageIO.read(getClass().getResourceAsStream("/main/res/Nurse-16x16-Base-Folder32.png"));

        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            System.err.println("Error: NO PICTURE PLEASE CHECK A FILE PATH");
            e.printStackTrace();
        }
    }

    public void update() {
        if (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) {

            // เคลื่อนที่
            if (keyH.upPressed) {
                direction = "up";
                y -= speed;
            } else if (keyH.downPressed) {
                direction = "down";
                y += speed;
            } else if (keyH.leftPressed) {
                direction = "left";
                x -= speed;
            } else if (keyH.rightPressed) {
                direction = "right";
                x += speed;
            }

            // คำนวณอนิเมชั่น (สลับขา)
            spriteCounter++;
            if (spriteCounter > 12) { // ทุกๆ 12 เฟรม ให้เปลี่ยนรูป
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;

        switch (direction) {
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
                if (spriteNum == 3) image = left3;
                if (spriteNum == 4) image = left4;
                if (spriteNum == 5) image = left5;
                if (spriteNum == 6) image = left6;
                if (spriteNum == 7) image = left7;
                break;
            case "right":
                if (spriteNum == 1) image = right1;
                if (spriteNum == 2) image = right2;
                if (spriteNum == 3) image = right3;
                if (spriteNum == 4) image = right4;
                if (spriteNum == 5) image = right5;
                if (spriteNum == 6) image = right6;
                if (spriteNum == 7) image = right7;
                break;
        }

        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
    }
}