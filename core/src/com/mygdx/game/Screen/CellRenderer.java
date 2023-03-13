package com.mygdx.game.Screen;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.BatalhaNaval.Cell;

public class CellRenderer {
    private Texture waterTexture;
    private Texture shipTexture;
    private Texture hitTexture;
    private Texture missTexture;

    public CellRenderer(AssetManager assetManager) {
        // carrega as texturas utilizadas para cada estado da célula
        waterTexture = assetManager.get("water.png", Texture.class);
        shipTexture = assetManager.get("ship.png", Texture.class);
        hitTexture = assetManager.get("hit.png", Texture.class);
        missTexture = assetManager.get("miss.png", Texture.class);
    }

    public void render(SpriteBatch batch, Cell cell, int x, int y, int size) {
        Texture texture;

        // seleciona a textura correspondente ao estado atual da célula
        switch (cell.getState()) {
            case "NAVIO":
                texture = shipTexture;
                break;
            case "HIT":
                texture = hitTexture;
                break;
            case "MISS":
                texture = missTexture;
                break;
            default:
                texture = waterTexture;
                break;
        }

        // desenha a textura na posição correspondente na tela
        batch.draw(texture, x, y, size, size);
    }
}
