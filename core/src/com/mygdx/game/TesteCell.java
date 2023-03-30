package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.BatalhaNaval.Cell;

public class TesteCell extends Actor {
    private Texture texture;
    private Cell cell;
    private AssetManager assetManager;

    public TesteCell(AssetManager assetManager, Cell cell) {
        this.cell = cell;
        this.assetManager = assetManager;
        texture = assetManager.get("water.png", Texture.class);
        
        addListener(new ClickListener() {
            @Override public void clicked(InputEvent event, float x, float y) {
                String message = "Clicked at this button"; 
                TesteCell.this.cell.setHit(true);
                Gdx.app.log("Clicked button", message);
                event.stop();
            }
        });
    }

    @Override
    public void draw(Batch batch, float parentAlpha){
        
        switch(cell.getState()){
            case "AGUA":
                texture = assetManager.get("water.png", Texture.class);
                break;
            case "HIT":
                texture = assetManager.get("hit.png", Texture.class);
                break;
            case "MISS":
                texture = assetManager.get("miss.png", Texture.class);
                break;
        }


        batch.draw(texture, getX(), getY(), getWidth(), getHeight());
    }

}
