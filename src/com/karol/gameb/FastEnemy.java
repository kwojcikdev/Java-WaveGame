package com.karol.gameb;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.Buffer;
import java.util.Random;


public class FastEnemy extends GameObject {

    Random r = new Random();

    private Handler handler;
    private BufferedImage img;


    public FastEnemy(int x, int y, ID id, Handler handler) {
        super(x, y, id);

        this.handler = handler;
        velX = r.nextInt(5) + 3;
        velY = r.nextInt(8) + 5;

        try {
            img = ImageIO.read(new File("C:\\Users\\karol\\Desktop\\t.png"));
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void tick() {
        x += velX;
        y += velY;

        if(y <= 0 || y >= Game.HEIGHT - 32) velY *= -1;
        if(x <= 0 || x >= Game.WIDTH - 32) velX *= -1;

        //handler.addObject(new Trail(x,y, ID.Trail, Color.PINK, 16,16,0.07, handler));
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.PINK);
        //g.fillRect((int)x,(int)y,16, 16);
        g.drawImage(img, (int)x, (int)y, 64, 64, Color.WHITE, null);

    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)x,(int) y, 64, 64);
    }


}

