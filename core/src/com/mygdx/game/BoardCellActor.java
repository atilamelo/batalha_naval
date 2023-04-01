package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.BatalhaNaval.Cell;
import com.mygdx.game.BatalhaNaval.LogicGame;

public class BoardCellActor extends Actor {
    private Texture texture;
    private Cell cell;
    private AssetManager assetManager;
    private LogicGame logicGame; 

    public BoardCellActor(AssetManager assetManager, Cell cell, LogicGame logicGame) {
        this.cell = cell;
        this.assetManager = assetManager;
        this.logicGame = logicGame;
        texture = assetManager.get("maingame/board/water.png", Texture.class);
        
        addListener(new ClickListener() {
            @Override public void clicked(InputEvent event, float x, float y) {
                Cell cell = BoardCellActor.this.cell;

                // Just make the event if this cell is not hit yet
                if(!cell.isHit() && !BoardCellActor.this.logicGame.isJogoTerminado()){
                    BoardCellActor.this.logicGame.atacar();
                    Gdx.app.log("[ClickedBoardCellActor]", "Quantidade de bombas = " + BoardCellActor.this.logicGame.getQntdBombas());

                    cell.setHit(true);
                    if(cell.getState() == "MISS"){
                        BoardCellActor.this.assetManager.get("maingame/sound/sound_miss.wav", Sound.class).play();
                    }else if(cell.getState() == "HIT" && cell.getShip().isAfundado()){
                        BoardCellActor.this.assetManager.get("maingame/sound/sound_explosionship.wav", Sound.class).play();   
                    }else if(cell.getState() == "HIT"){
                        BoardCellActor.this.assetManager.get("maingame/sound/sound_hit.wav", Sound.class).play();
                    }
                }

                event.stop();
            }
        });
    }

    @Override
    public void draw(Batch batch, float parentAlpha){
        
        switch(cell.getState()){
            case "AGUA":
                texture = assetManager.get("maingame/board/water.png", Texture.class);
                break;
            case "HIT":
                texture = assetManager.get("maingame/board/hit.png", Texture.class);
                break;
            case "MISS":
                texture = assetManager.get("maingame/board/miss.png", Texture.class);
                break;
        }


        batch.draw(texture, getX(), getY(), getWidth(), getHeight());
    }

}
