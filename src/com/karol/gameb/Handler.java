package com.karol.gameb;

import java.awt.*;
import java.util.LinkedList;

public class Handler {

    LinkedList<GameObject> object = new LinkedList<GameObject>();

    public void tick(){
        for(int i = 0; i < object.size(); i++){
            GameObject tempObject = object.get(i);

            if(tempObject != null)tempObject.tick();
        }
    }

    public void render(Graphics g){
        for(int i = 0; i < object.size(); i++){
            GameObject tempObject = object.get(i);

            if(tempObject != null)tempObject.render(g);
        }
    }

    public void addObject(GameObject object){
        this.object.add(object);
    }

    public void removeObject(GameObject object ){
        this.object.remove(object);
    }

    public void clearEnemies(){
        for( int i = 0; i < object.size(); i++){
            GameObject tempObject = object.get(i);
            if(tempObject.getId() != ID.Player){
                removeObject(tempObject);
                i--;
            }

        }
    }

    public void clearEverything(){
        for( int i = 0; i < object.size(); i++){
            GameObject tempObject = object.get(i);
                removeObject(tempObject);
                i--;
        }
    }
}
