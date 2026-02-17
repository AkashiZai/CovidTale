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
        groundY = y; // จำตำแหน่งพื้นเริ่มต้น
    }

    public void getPlayerImage() {
        try {
            down1 = ImageIO.read(getClass().getResourceAsStream("/main/res/Nurse-16x16-Base-Folder34.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/main/res/Nurse-16x16-Base-Folder35.png"));

            up1 = ImageIO.read(getClass().getResourceAsStream("/main/res/Nurse-16x16-Base-Folder34.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/main/res/Nurse-16x16-Base-Folder35.png"));

            left1 = ImageIO.read(getClass().getResourceAsStream("/main/res/Nurse-16x16-Base-Folder19.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/main/res/Nurse-16x16-Base-Folder20.png"));
            left3 = ImageIO.read(getClass().getResourceAsStream("/main/res/Nurse-16x16-Base-Folder21.png"));
            left4 = ImageIO.read(getClass().getResourceAsStream("/main/res/Nurse-16x16-Base-Folder22.png"));
            left5 = ImageIO.read(getClass().getResourceAsStream("/main/res/Nurse-16x16-Base-Folder23.png"));
            left6 = ImageIO.read(getClass().getResourceAsStream("/main/res/Nurse-16x16-Base-Folder24.png"));
            left7 = ImageIO.read(getClass().getResourceAsStream("/main/res/Nurse-16x16-Base-Folder25.png"));

            right1 = ImageIO.read(getClass().getResourceAsStream("/main/res/Nurse-16x16-Base-Folder26.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/main/res/Nurse-16x16-Base-Folder27.png"));
            right3 = ImageIO.read(getClass().getResourceAsStream("/main/res/Nurse-16x16-Base-Folder28.png"));
            right4 = ImageIO.read(getClass().getResourceAsStream("/main/res/Nurse-16x16-Base-Folder29.png"));
            right5 = ImageIO.read(getClass().getResourceAsStream("/main/res/Nurse-16x16-Base-Folder30.png"));
            right6 = ImageIO.read(getClass().getResourceAsStream("/main/res/Nurse-16x16-Base-Folder31.png"));
            right7 = ImageIO.read(getClass().getResourceAsStream("/main/res/Nurse-16x16-Base-Folder32.png"));


            jump1 = ImageIO.read(getClass().getResourceAsStream("/main/res/Nurse-16x16-Base-Folder33.png"));
            jump2 = ImageIO.read(getClass().getResourceAsStream("/main/res/Nurse-16x16-Base-Folder34.png"));
            jump3 = ImageIO.read(getClass().getResourceAsStream("/main/res/Nurse-16x16-Base-Folder35.png"));
            jump4 = ImageIO.read(getClass().getResourceAsStream("/main/res/Nurse-16x16-Base-Folder36.png"));
            jump5 = ImageIO.read(getClass().getResourceAsStream("/main/res/Nurse-16x16-Base-Folder37.png"));
            jump6 = ImageIO.read(getClass().getResourceAsStream("/main/res/Nurse-16x16-Base-Folder38.png"));
            jump7 = ImageIO.read(getClass().getResourceAsStream("/main/res/Nurse-16x16-Base-Folder39.png"));
            jump8 = ImageIO.read(getClass().getResourceAsStream("/main/res/Nurse-16x16-Base-Folder40.png"));
            jump9 = ImageIO.read(getClass().getResourceAsStream("/main/res/Nurse-16x16-Base-Folder41.png"));


            fall1 = ImageIO.read(getClass().getResourceAsStream("/main/res/Nurse-16x16-Base-Folder63.png"));
            fall2 = ImageIO.read(getClass().getResourceAsStream("/main/res/Nurse-16x16-Base-Folder64.png"));
            fall3 = ImageIO.read(getClass().getResourceAsStream("/main/res/Nurse-16x16-Base-Folder65.png"));
            fall4 = ImageIO.read(getClass().getResourceAsStream("/main/res/Nurse-16x16-Base-Folder66.png"));
            fall5 = ImageIO.read(getClass().getResourceAsStream("/main/res/Nurse-16x16-Base-Folder67.png"));
            fall6 = ImageIO.read(getClass().getResourceAsStream("/main/res/Nurse-16x16-Base-Folder68.png"));
            fall7 = ImageIO.read(getClass().getResourceAsStream("/main/res/Nurse-16x16-Base-Folder69.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        if(!jumping && !falling) {
            groundY = y;
        }

        if (keyH.spacePressed && !jumping && !falling) {
            jumping = true;
            verticalVelocity = -jumpStrength;
            spriteNum = 1;
            spriteCounter = 0;
        }

        if (jumping || falling) {
            y += verticalVelocity;
            verticalVelocity += gravity;
            if (verticalVelocity > 0) {
                if(!falling) {
                    falling = true;
                    jumping = false;
                    spriteNum = 1;
                    spriteCounter = 0;
                }
            }
            if (y >= groundY) {
                y = groundY;
                jumping = false;
                falling = false;
                verticalVelocity = 0;
                spriteNum = 1;
            }
        }

        if (jumping) {
            spriteCounter++;
            if (spriteCounter > 5) {
                spriteNum++;
                if (spriteNum > 9) spriteNum = 9;
                spriteCounter = 0;
            }
        }
        else if (falling) {
            spriteCounter++;
            if (spriteCounter > 5) {
                spriteNum++;
                if (spriteNum > 7) spriteNum = 1;
                spriteCounter = 0;
            }
        }
        else {
            if (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) {
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

                groundY = y;
                spriteCounter++;
                if (direction.equals("left") || direction.equals("right")) {
                    if (spriteCounter > 5) {
                        spriteNum++;
                        if (spriteNum > 7) spriteNum = 1;
                        spriteCounter = 0;
                    }
                } else {
                    if (spriteCounter > 12) {
                        spriteNum = (spriteNum == 1) ? 2 : 1;
                        spriteCounter = 0;
                    }
                }
            }
        }
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;

        if (jumping) {
            switch(spriteNum) {
                case 1: image = jump1; break;
                case 2: image = jump2; break;
                case 3: image = jump3; break;
                case 4: image = jump4; break;
                case 5: image = jump5; break;
                case 6: image = jump6; break;
                case 7: image = jump7; break;
                case 8: image = jump8; break;
                case 9: image = jump9; break;
                default: image = jump9; break;
            }
        }
        else if (falling) {
            switch(spriteNum) {
                case 1: image = fall1; break;
                case 2: image = fall2; break;
                case 3: image = fall3; break;
                case 4: image = fall4; break;
                case 5: image = fall5; break;
                case 6: image = fall6; break;
                case 7: image = fall7; break;
                default: image = fall1; break;
            }
        }
        else {
            switch (direction) {
                case "up":
                    image = (spriteNum == 1) ? up1 : up2;
                    break;
                case "down":
                    image = (spriteNum == 1) ? down1 : down2;
                    break;
                case "left":
                    if (spriteNum == 1) image = left1;
                    if (spriteNum == 2) image = left2;
                    if (spriteNum == 3) image = left3;
                    if (spriteNum == 4) image = left4;
                    if (spriteNum == 5) image = left5;
                    if (spriteNum == 6) image = left6;
                    if (spriteNum == 7) image = left7;
                    if (spriteNum > 7) image = left1;
                    break;
                case "right":
                    if (spriteNum == 1) image = right1;
                    if (spriteNum == 2) image = right2;
                    if (spriteNum == 3) image = right3;
                    if (spriteNum == 4) image = right4;
                    if (spriteNum == 5) image = right5;
                    if (spriteNum == 6) image = right6;
                    if (spriteNum == 7) image = right7;
                    if (spriteNum > 7) image = right1;
                    break;
            }
        }

        if (image != null) {
            g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
        }
    }
}