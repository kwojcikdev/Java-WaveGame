package com.karol.gameb;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Game extends Canvas implements Runnable{

    public static final int WIDTH = 1280, HEIGHT=WIDTH/16*9;

    private Thread thread;
    private boolean running = false;

    public static boolean paused = false;

    private Random r;
    private Handler handler;
    private HUD hud;
    private Spawn spawn;
    private Menu menu;

    public enum STATE {
        Menu,
        Game,
        Options,
        GameOver
    }

    public STATE gameState = STATE.Menu;


    public Game(){

        handler = new Handler();
        hud = new HUD();
        spawn = new Spawn(handler, hud);
        menu = new Menu(this, handler);


        this.addKeyListener(new KeyInput(handler, this));
        this.addMouseListener(menu);


        new Window(WIDTH, HEIGHT, "Let's build a game", this);

        r = new Random();

        //for(int i = 0; i < 50; i++){
        //    handler.addObject(new Player(r.nextInt(WIDTH), r.nextInt(HEIGHT), ID.Player));
        //}
        if(gameState != STATE.Game){
            //handler.addObject( new Player(WIDTH/2 - 32, HEIGHT/2 - 32, ID.Player, handler));

            //for(int i = 0; i < 2; i++) {
           // handler.addObject(new BasicEnemy(r.nextInt(WIDTH), r.nextInt(HEIGHT), ID.BasicEnemy, handler));
            //handler.addObject(new EnemyBoss((Game.WIDTH/2)-48, -120, ID.EnemyBoss, handler));

            //}
            for(int i = 0; i < 30; i++)
            handler.addObject( new MenuParticle( r.nextInt(WIDTH), r.nextInt(HEIGHT), ID.MenuParticle, handler));
        }


    }



    public synchronized void start() {
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    public synchronized void stop() {
        try{
            thread.join();
            running = false;
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void run() {
        this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while (running){
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                tick();
                delta--;
            }
            if(running){
                render();
            }
            frames++;

            if(System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                System.out.println("FPS: " + frames);
                frames = 0;
            }
        }
        stop();
    }

    private void tick(){



        if(gameState == STATE.Game && !paused){
            System.out.println(paused);
            handler.tick();
            hud.tick();
            spawn.tick();
        }
        else if(gameState == STATE.Menu || gameState == STATE.Options || gameState == STATE.GameOver){
            handler.tick();
            menu.tick();
        }

        if(gameState == STATE.Game && HUD.HEALTH <= 0 ){
            HUD.HEALTH = 10;
            System.out.println("                         GAME OVER  SOCRE: " + hud.getScore());
            handler.clearEverything();
            for(int i = 0; i < 30; i++)
                handler.addObject( new MenuParticle( r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.MenuParticle, handler));
            gameState = STATE.GameOver;
        }

    }

    private void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        g.setColor(Color.BLUE);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        handler.render(g);

        if(gameState == STATE.Game){
            hud.render(g);
        }
        else if(gameState == STATE.Menu || gameState == STATE.Options || gameState == STATE.GameOver){
            menu.render(g);
        }

        if(paused){
            Font font = new Font("arial", 1, 50);
            g.setFont(font);
            g.setColor(Color.black);
            g.drawString("PAUSED" ,300, 100);
        }





        g.dispose();
        bs.show();
    }

    public static float clamp(float var, float min, float max){
        if(var >= max) return var = max;
        else if( var <= min) return var = min;
        else return var;
    }

    public static void main ( String [] args ) {

        new Game();
    }
}
