    package com.mygdx.game.Screen;

    import com.badlogic.gdx.assets.AssetManager;
    import com.badlogic.gdx.graphics.Texture;
    import com.badlogic.gdx.graphics.g2d.SpriteBatch;
    import com.mygdx.game.BatalhaNaval.Cell;
    import com.mygdx.game.BatalhaNaval.Tabuleiro;

    public class BoardRenderer {
        private final Tabuleiro tabuleiro;
        private final CellRenderer cellRenderer;
        private final int padding; 
        private SpriteBatch batch;
        private AssetManager assetManager; 

        public BoardRenderer(Tabuleiro tabuleiro, AssetManager assetManager, SpriteBatch batch, int padding) {
            this.tabuleiro = tabuleiro;
            this.batch = batch;
            this.padding = padding;
            this.assetManager = assetManager;
            
            // Loads sprites
            assetManager.load("water.png", Texture.class);
            assetManager.load("ship.png", Texture.class);
            assetManager.load("hit.png", Texture.class);
            assetManager.load("miss.png", Texture.class);
            assetManager.finishLoading();

            this.cellRenderer = new CellRenderer(assetManager);

        }

        public void render(int x, int y, int cellSize) {
            batch.begin();
            

            // percorre todas as células do tabuleiro e as desenha utilizando o CellRenderer
            for (int i = 0; i < tabuleiro.getLargura(); i++) {
                for (int j = 0; j < tabuleiro.getAltura(); j++) {
                    Cell cell = tabuleiro.getCell(i, j);
                    cellRenderer.render(batch, cell, x + padding + i * (cellSize + padding), y + padding + j * (cellSize + padding), cellSize);
                }
            }

            batch.end();
        }

        public void dispose() {
            batch.dispose();
        }
    }
