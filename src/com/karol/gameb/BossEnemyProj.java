package com.karol.gameb;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Random;


public class BossEnemyProj extends GameObject {

    Random r = new Random();
    private Handler handler;


    public BossEnemyProj(int x, int y, ID id, Handler handler) {

        super(x, y, id);

        this.handler = handler;

        velX = r.nextInt(10) - 5;
        velY = r.nextInt(5) + 2;


    }

    @Override
    public void tick() {
        x += velX;
        y += velY;

        //if(y <= 0 || y >= Game.HEIGHT - 32) velY *= -1;
        //if(x <= 0 || x >= Game.WIDTH - 32) velX *= -1;

        if(y >= Game.HEIGHT) {
            handler.removeObject(this);
        }
        handler.addObject(new Trail(x,y, ID.Trail, Color.RED, 16,16,0.07, handler));
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect((int)x,(int)y,16, 16);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, 16, 16);
    }



}

