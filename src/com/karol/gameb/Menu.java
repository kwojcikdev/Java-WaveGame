package com.karol.gameb;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class Menu extends MouseAdapter {

    private Game game;
    private Handler handler;
    Random r = new Random();

    public Menu(Game game, Handler handler) {
        this.game = game;
        this.handler = handler;
    }
    public void mousePressed(MouseEvent e){

        int mx = e.getX();
        int my = e.getY();

        if(game.gameState == Game.STATE.Options || game.gameState == Game.STATE.GameOver){
            if (mouseOver(mx, my, Game.WIDTH/2 - 100, 400, 200, 60)) {
                spawnMenuParticle();
                game.gameState = Game.STATE.Menu;
                return;
            }
        }


        if(game.gameState == Game.STATE.Menu){
            if(mouseOver(mx, my, Game.WIDTH/2 - 100, 200, 200, 60)){
                game.gameState = Game.STATE.Game;
                handler.clearEnemies();
                handler.addObject( new Player(Game.WIDTH/2 - 32, Game.HEIGHT/2 - 32, ID.Player, handler));
                handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.BasicEnemy, handler));
            }

            if (mouseOver(mx, my, Game.WIDTH/2 - 100, 300, 200, 60)){
                game.gameState = Game.STATE.Options;
            }

            if (mouseOver(mx, my, Game.WIDTH/2 - 100, 400, 200, 60)) {
                System.exit(1);
            }
        }


    }

    public void mouseRelased(MouseEvent e){

    }

    private boolean mouseOver(int mx, int my, int x, int y, int width, int height){
        if(mx > x && mx < x + width){
            if( my > y && my < y + height){
                return true;
            }else return false;
        }else return false;
    }

    public void tick(){

    }

    public void render(Graphics g){

        Font font = new Font("arial", 1, 50);
        Font font2 = new Font("arial", 1, 30);
        FontMetrics metrics = g.getFontMetrics(font2);
        FontMetrics metrics2 = g.getFontMetrics(font);

        g.setFont(font);
        g.setColor(Color.WHITE);

        if(game.gameState == Game.STATE.Menu) {

            g.drawString("MAJA", Game.WIDTH / 2 - 100, 100);

            g.setFont(font2);

            g.drawRect(Game.WIDTH / 2 - 100, 200, 200, 60);
            g.drawString(" Play ", Game.WIDTH / 2 - 100 + ((200 - metrics.stringWidth("Play")) / 2), 200 + ((60 - metrics.getHeight()) / 2) + metrics.getAscent());

            g.drawRect(Game.WIDTH / 2 - 100, 300, 200, 60);

            g.drawString(" Options ", Game.WIDTH / 2 - 100 + ((200 - metrics.stringWidth("Options")) / 2), 300 + ((60 - metrics.getHeight()) / 2) + metrics.getAscent());


            g.drawRect(Game.WIDTH / 2 - 100, 400, 200, 60);
            g.drawString(" Exit ", Game.WIDTH / 2 - 100 + ((200 - metrics.stringWidth("Exit")) / 2), 400 + ((60 - metrics.getHeight()) / 2) + metrics.getAscent());
        }else if( game.gameState == Game.STATE.Options) {

            g.drawString(" OPTIONS ", Game.WIDTH / 2 - 100, 100);

            g.setFont(font);
            g.drawString(" YOU HAVE THE OPTION TO SHUT THE FUCK UP ", 50, 250);

            g.drawRect(Game.WIDTH / 2 - 100, 400, 200, 60);
            g.drawString(" Back ", Game.WIDTH / 2 - 100 + ((200 - metrics2.stringWidth("Back")) / 2), 400 + ((60 - metrics2.getHeight()) / 2) + metrics2.getAscent());
        } else if( game.gameState == Game.STATE.GameOver){

            g.drawString(" OPTIONS ", Game.WIDTH / 2 - 100, 100);

            g.setFont(font2);
            g.drawString("GAME OVER", 50, 250);
            g.drawString("SCORE: " + HUD.score, 50, 300);

            g.drawRect(Game.WIDTH / 2 - 100, 400, 200, 60);
            g.drawString(" Back ", Game.WIDTH / 2 - 100 + ((200 - metrics2.stringWidth("Back")) / 2), 400 + ((60 - metrics2.getHeight()) / 2) + metrics2.getAscent());

        }

    }


    public void drawCenteredString(Graphics g, String text, Rectangle rect, Font font) {
        // Get the FontMetrics
        FontMetrics metrics = g.getFontMetrics(font);
        // Determine the X coordinate for the text
        int x = rect.x + (rect.width - metrics.stringWidth(text)) / 2;
        // Determine the Y coordinate for the text (note we add the ascent, as in java 2d 0 is top of the screen)
        int y = rect.y + ((rect.height - metrics.getHeight()) / 2) + metrics.getAscent();
        // Set the font
        g.setFont(font);
        // Draw the String
        g.drawString(text, x, y);
    }

    public void spawnMenuParticle(){
        if(game.gameState != Game.STATE.Game){
            for(int i = 0; i < 30; i++)
                handler.addObject( new MenuParticle( r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.MenuParticle, handler));
        }
    }
}
