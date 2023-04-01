package com.mygdx.game.Actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.Logic.Cell;
import com.mygdx.game.Logic.CellState;
import com.mygdx.game.Logic.LogicGame;

public class CellActor extends Actor {
    private Texture texture;
    private Cell cell;
    private AssetManager assetManager;
    private LogicGame logicGame;

    /**
     * Construtor da classe BoardCellActor.
     * 
     * @param assetManager Gerenciador de assets.
     * @param cell         Célula correspondente.
     * @param logicGame    Instância da lógica do jogo.
     */
    public CellActor(AssetManager assetManager, Cell cell, LogicGame logicGame) {
        this.cell = cell;
        this.assetManager = assetManager;
        this.logicGame = logicGame;
        texture = assetManager.get("maingame/board/water.png", Texture.class);

        // Listener para tratar eventos de clique na célula
        addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Cell cell = CellActor.this.cell;

                // Só realiza o evento se a célula não foi atingida e o jogo não foi encerrado
                if (!cell.isHit() && !CellActor.this.logicGame.isJogoTerminado()) {
                    CellActor.this.logicGame.atacar();
                    Gdx.app.log("[ClickedBoardCellActor]",
                            "Quantidade de bombas = " + CellActor.this.logicGame.getQntdBombas());
                    cell.setHit(true);

                    // Define qual som será tocado baseado no estado da célula e se o navio foi
                    // totalmente destruído
                    if (cell.getState() == CellState.MISS) {
                        CellActor.this.assetManager.get("maingame/sound/sound_miss.wav", Sound.class).play();
                    } else if (cell.getState() == CellState.HIT) {
                        String soundPath = cell.getShip().isAfundado() ? "maingame/sound/sound_explosionship.wav"
                                : "maingame/sound/sound_hit.wav";
                        CellActor.this.assetManager.get(soundPath, Sound.class).play();
                    }

                }

                event.stop();
            }
        });

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {

        switch (cell.getState()) {
            case WATER:
                texture = assetManager.get("maingame/board/water.png", Texture.class);
                break;
            case HIT:
                texture = assetManager.get("maingame/board/hit.png", Texture.class);
                break;
            case MISS:
                texture = assetManager.get("maingame/board/miss.png", Texture.class);
                break;
        }

        batch.draw(texture, getX(), getY(), getWidth(), getHeight());
    }

}
