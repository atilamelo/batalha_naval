package com.mygdx.game;

import java.util.ArrayList;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.game.BatalhaNaval.Game;
import com.mygdx.game.BatalhaNaval.Ship;
import com.mygdx.game.BatalhaNaval.Tabuleiro;

public class MainGame extends ApplicationAdapter{
	private Stage stage;
	private Table table;
	private Game game;
	private Tabuleiro tabuleiro;
	private AssetManager assetManager;

	@Override
	public void create () {
		// Loads assets for the game
		assetManager = new AssetManager();
		assetManager.load("water.png", Texture.class);
		assetManager.load("ship.png", Texture.class);
		assetManager.load("miss.png", Texture.class);
		assetManager.load("hit.png", Texture.class);
		assetManager.load("sound_miss.wav", Sound.class);
		assetManager.load("sound_hit.wav", Sound.class);
		assetManager.finishLoading();

		// Create game logic 
        ArrayList<Ship> navios = new ArrayList<Ship>();
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
		game = new Game(10, 10, navios);
		tabuleiro = game.getTabuleiro();
		
		// Create UI
		stage = new Stage(new FitViewport(1200, 800));

		table = new Table();
		table.defaults().size(64).pad(5); // set the default size and padding of each cell

		for (int row = 0; row < 10; row++) {
			for (int col = 0; col < 10; col++) {
				com.mygdx.game.BatalhaNaval.Cell cell = tabuleiro.getCell(row, col);
				BoardCellActor cellTest = new BoardCellActor(assetManager, cell);
				table.add(cellTest); 
			}
			table.row();
		}

		table.pack(); // resize the table to fit its contents
		table.setPosition(
				(stage.getWidth() - table.getWidth()) / 2f,
				(stage.getHeight() - table.getHeight()) / 2f
		); // center the table on the stage

		stage.addActor(table); // add the table to the stage

		Gdx.input.setInputProcessor(stage); // set the stage as the input processor
	}

	@Override 
	public void render() {
		Gdx.gl.glClearColor(255, 255, 255, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
	}

	@Override
	public void dispose(){
		stage.dispose();
		assetManager.dispose();
	}
	
	@Override
	public void resize(int width, int height){
		stage.getViewport().update(width, height, true);
	}
}