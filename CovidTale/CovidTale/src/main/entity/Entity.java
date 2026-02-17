package main.entity;

import java.awt.image.BufferedImage;

public class Entity {
    public int x, y;
    public int speed;

    public BufferedImage up1, up2, down1, down2;
    public BufferedImage left1, left2, left3, left4, left5, left6, left7;
    public BufferedImage right1, right2, right3, right4, right5, right6, right7;

    // ท่ากระโดด
    public BufferedImage jump1, jump2, jump3, jump4, jump5, jump6, jump7, jump8, jump9;

    // --- เพิ่มส่วนนี้: ท่าตก (Fall) ---
    public BufferedImage fall1, fall2, fall3, fall4, fall5, fall6, fall7;
    // -------------------------------

    public String direction;
    public int spriteCounter = 0;
    public int spriteNum = 1;

    // Physics
    public boolean jumping = false;
    public boolean falling = false;
    public int verticalVelocity = 0;
    public int jumpStrength = 10;
    public int gravity = 1;
    public int groundY;
}