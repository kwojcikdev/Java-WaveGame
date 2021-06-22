package com.karol.gameb;

import java.util.Random;

public class Spawn {

    private Handler handler;
    private HUD hud;
    private int scoreKeep = 0;
    Random r = new Random();

    public Spawn(Handler handler, HUD hud){
        this.handler = handler;
        this.hud = hud;
    }

    public void tick(){
        scoreKeep++;

        if( scoreKeep >= 100
        ){
            scoreKeep = 0;
            hud.riseLevel();
            if(HUD.level == 1 ) spawnBasic();

            if(HUD.level % 2 == 0 && HUD.level < 10) spawnBasic();
             if (HUD.level % 3 == 0 && HUD.level < 10) spawnFast();
             if ( HUD.level == 5) handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH - 16), r.nextInt(Game.HEIGHT - 16), ID.SmartEnemy, handler));
             if ( HUD.level == 10) {

                handler.clearEnemies();
                 handler.addObject(new EnemyBoss((Game.WIDTH/2)-48, -120, ID.EnemyBoss, handler));

             }
             if ( HUD.level == 20) {
                 for (int i = 0; i < handler.object.size(); i++) {
                     GameObject tempObject = handler.object.get(i);
                     if (tempObject.getId() == ID.EnemyBoss) handler.removeObject(tempObject);
                     HUD.level = 1;
                 }
             }
        }

    }

    public void spawnBasic(){
        handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 16), r.nextInt(Game.HEIGHT - 16), ID.BasicEnemy, handler));
    }

    public  void spawnFast(){
        handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 16), r.nextInt(Game.HEIGHT - 16), ID.FastEnemy, handler));
    }
}
