package com.karol.gameb;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Random;

public class Player extends GameObject{

    Handler handler;
    BufferedImage img = null;
    private int size = 32;

    public Player(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;

        try {
            img = ImageIO.read(new File("C:\\Users\\karol\\Desktop\\maja.png"));
        } catch (Exception e){
            e.printStackTrace();
        }


    }

    @Override
    public void tick() {

        x += velX;
        x = Game.clamp(x, 0 , Game.WIDTH - 64);

        y += velY;
        y = Game.clamp(y, 0, Game.HEIGHT - 70);

        //handler.addObject(new Trail(x,y, ID.Trail, Color.white, 32,32,0.07, handler));

        collision();
    }

    public void collision(){
        for(int i = 0; i < handler.object.size(); i++){
            GameObject tempObject = handler.object.get(i);

            if( tempObject.getId() == ID.BasicEnemy || tempObject.getId() == ID.FastEnemy || tempObject.getId() == ID.SmartEnemy || tempObject.getId() == ID.EnemyBoss){
                if(getBounds().intersects(tempObject.getBounds())){
                    // collision
                    HUD.HEALTH--;
                    System.out.println("COLLISION -1 HP  " + tempObject.getId());

                }

            }
        }
    }

    @Override
    public void render(Graphics g) {
        //g.setColor(Color.WHITE);
        //g.fillRect((int)x,(int)y, 32, 32);
        g.drawImage(img, (int)x, (int)y, size, size, Color.WHITE, null);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, size, size);
    }
}
