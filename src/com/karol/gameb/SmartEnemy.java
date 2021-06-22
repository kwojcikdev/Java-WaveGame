package com.karol.gameb;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Random;


public class SmartEnemy extends GameObject {

    Random r = new Random();

    private Handler handler;
    private GameObject player;
    private BufferedImage img;

    public SmartEnemy(int x, int y, ID id, Handler handler) {
        super(x, y, id);

        this.handler = handler;

        for(int i = 0; i < handler.object.size(); i++){
            if(handler.object.get(i).getId() == ID.Player)
            {
                player = handler.object.get(i);
                break;
            }

        }
        try {
            img = ImageIO.read(new File("C:\\Users\\karol\\Desktop\\z.png"));
        } catch (Exception e){
            e.printStackTrace();
        }


    }

    @Override
    public void tick() {

        float dx, dy, distance;
        dx = x - player.getX() - 8;
        dy = y - player.getY() - 8;
        distance = (float) Math.sqrt(((x-player.getX()) * (x - player.getX())) + (((y - player.getY()) * (y - player.getY()))));

        velX = ((-1 / distance) * dx) * 2;
        velY = ((-1 / distance) * dy) * 2;

        System.out.println(velX+ " " +  velY);
        x += velX;
        y += velY;

        if(y <= 0 || y >= Game.HEIGHT - 32) velY *= -1;
        if(x <= 0 || x >= Game.WIDTH - 32) velX *= -1;

        //handler.addObject(new Trail(x,y, ID.Trail, Color.cyan, 16,16,0.07, handler));
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.cyan);
        //g.fillRect((int)x,(int)y,16, 16);
        g.drawImage(img, (int)x, (int)y, 64, 64, Color.WHITE, null);


    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, 64, 64);
    }



}

