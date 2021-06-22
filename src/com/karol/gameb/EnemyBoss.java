package com.karol.gameb;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Random;


public class EnemyBoss extends GameObject {

    Random r = new Random();
    private BufferedImage img;
    private Handler handler;

    private int timer = 80, timer2 = 50;

    public EnemyBoss(int x, int y, ID id, Handler handler) {
        super(x, y, id);

        this.handler = handler;
        velX = 0;
        velY = 2;

        try {
            img = ImageIO.read(new File("C:\\Users\\karol\\Desktop\\l.png"));
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void tick() {
        x += velX;
        y += velY;

        timer--;
        if(timer <= 0) velY = 0;
        if(timer <= 0 && timer2 > 0 ) timer2--;
        if(timer2 <=0 ){
            if(velX == 0) velX = 4;
            if(velX > 0) velX += 0.03f;
            else velX -= 0.03f;

            Game.clamp(velX, - 7, 7);
            int spawn = r.nextInt(3);
            if (spawn == 0) handler.addObject(new BossEnemyProj((int)x + 48, (int)y + 48, ID.BasicEnemy, handler));
        }
       // if(y <= 0 || y >= Game.HEIGHT - 32) velY *= -1;
        if(x <= 0 || x >= Game.WIDTH - 96) velX *= -1;

        //handler.addObject(new Trail(x,y, ID.Trail, Color.MAGENTA, 96,96,0.07, handler));
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.MAGENTA);
        //g.fillRect((int)x,(int)y,16, 16);
        g.fillOval((int)x, (int)y, 96, 96);
        g.drawImage(img, (int)x, (int)y, 96, 96, null);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, 96, 96);
    }



}

