package com.mygdx.game.Actors;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.game.Logic.Ship;

public class ShipActor extends Actor {
    private Texture texture;
    private Ship ship;
    private String filename; 
    private AssetManager assetManager;

    public ShipActor(AssetManager assetManager, Ship ship) {
        this.ship = ship;
        this.assetManager = assetManager;
        this.filename = "maingame/navios/navio_" + ship.getSize(); 
        texture = assetManager.get(filename + ".png", Texture.class);
    }

    @Override
    public void draw(Batch batch, float parentAlpha){
        if(ship.isAfundado()){
            texture = assetManager.get(filename + "_explodido" + ".png", Texture.class);
        }
        batch.draw(texture, getX(), getY(), getWidth(), getHeight());
    }

}
