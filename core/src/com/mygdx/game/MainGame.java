package com.mygdx.game;

import java.util.ArrayList;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.game.BatalhaNaval.LogicGame;
import com.mygdx.game.BatalhaNaval.Ship;
import com.mygdx.game.BatalhaNaval.Tabuleiro;

public class MainGame extends ScreenAdapter{
	private Stage stage;
	private Table table;
	private LogicGame logicGame;
	private Tabuleiro tabuleiro;
	private AssetManager assetManager;
	private BattleShipGame game; 

	public MainGame(BattleShipGame game) {
		this.game = game;
	}

	@Override
	public void show() {
		// Loads assets for the game
		assetManager = new AssetManager();
		assetManager.load("maingame/board/water.png", Texture.class);
		assetManager.load("maingame/board/ship.png", Texture.class);
		assetManager.load("maingame/board/miss.png", Texture.class);
		assetManager.load("maingame/board/hit.png", Texture.class);
		assetManager.load("maingame/sound/sound_miss.wav", Sound.class);
		assetManager.load("maingame/sound/sound_hit.wav", Sound.class);
		assetManager.load("maingame/navios/navio_2_explodido.png", Texture.class);
		assetManager.load("maingame/navios/navio_2.png", Texture.class);
		assetManager.load("maingame/navios/navio_3_explodido.png", Texture.class);
		assetManager.load("maingame/navios/navio_3.png", Texture.class);
		assetManager.load("maingame/navios/navio_4_explodido.png", Texture.class);
		assetManager.load("maingame/navios/navio_4.png", Texture.class);
		assetManager.load("maingame/navios/navio_5_explodido.png", Texture.class);
		assetManager.load("maingame/navios/navio_5.png", Texture.class);
		
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
		logicGame = new LogicGame(10, 10, navios);
		tabuleiro = logicGame.getTabuleiro();
		
		// Create UI
		stage = new Stage(new FitViewport(1200, 800));

		table = new Table();
		table.defaults().pad(5); // set the default size and padding of each cell
		// table.debug();

		for (int row = 0; row < 10; row++) {
			for (int col = 0; col < 10; col++) {
				com.mygdx.game.BatalhaNaval.Cell cell = tabuleiro.getCell(row, col);
				BoardCellActor cellTest = new BoardCellActor(assetManager, cell);
				table.add(cellTest).size(64); 
			}
			BoardShipActor shipActor = new BoardShipActor(assetManager, logicGame.getNavios().get(row));
			table.add(shipActor).height(48).width(256);
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
	public void render(float delta) {
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

	@Override
    public void hide(){
        Gdx.input.setInputProcessor(null);
    }

}