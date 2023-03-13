package com.mygdx.game.Screen;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.BatalhaNaval.Ship;
import com.mygdx.game.BatalhaNaval.Tabuleiro;

public class GameScreen implements Screen {

    private final MyGdxGame game;
    private BoardRenderer boardRenderer;
    // private final InputHandler inputHandler;
    private final Tabuleiro tabuleiro; 
    private SpriteBatch batch; 

    
    public GameScreen(MyGdxGame game) {
        List<Ship> navios = new ArrayList<Ship>();

        navios.add(new Ship(5));
        navios.add(new Ship(4));
        navios.add(new Ship(4));
        navios.add(new Ship(3));
        navios.add(new Ship(3));
        navios.add(new Ship(3));
        navios.add(new Ship(2));
        navios.add(new Ship(2));
        navios.add(new Ship(2));
        navios.add(new Ship(2));

        this.game = game;
        this.tabuleiro = new Tabuleiro(10, 10, navios); 
        // this.inputHandler = new InputHandler();
    }
    
    @Override
    public void show() {
        this.batch = new SpriteBatch();
        this.boardRenderer = new BoardRenderer(tabuleiro, game.getAssetManager(), batch, 5);
        // Configurações iniciais da tela
        System.out.println();
    }

    @Override
    public void render(float delta) {
        // Renderiza o tabuleiro do jogador
        boardRenderer.render(100, 50, 32);
        
    }

    @Override
    public void resize(int width, int height) {
        // Redimensionamento da tela
    }

    @Override
    public void pause() {
        // Pausa o jogo
    }

    @Override
    public void resume() {
        // Retorna ao jogo após pausa

    }

    @Override
    public void hide() {
        // Esconde a tela
    }

    @Override
    public void dispose() {
        // Libera recursos da memória
    }
}
