package com.karol.gameb;

import java.awt.*;

public class HUD {

    public static float HEALTH = 10;

    public static int score = 0, level = 1;
    public void tick(){
        HEALTH = Game.clamp(HEALTH, 0 , 100);

        score++;

    }

    public void render(Graphics g){
        g.setColor(Color.GRAY);
        g.fillRect(15,15, 200, 32);

        if (HEALTH <= 25){
            g.setColor(Color.RED);
        }else g.setColor(Color.GREEN);

        g.fillRect(15,15, (int)HEALTH*2, 32);
        g.setColor(Color.WHITE);
        g.drawRect(15,15, 200, 32);

        g.drawString("Score: "+ score, 15, 60);
        g.drawString("Level: "+ level, 15, 70);

    }

    public void score(int score){
        this.score = score;
    }

    public int getScore(){
        return this.score;
    }

    public int getLevel(){
        return this.level;
    }

    public void riseLevel(){
        this.level++;
    }




}
