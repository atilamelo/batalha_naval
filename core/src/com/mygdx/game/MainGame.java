package com.mygdx.game;

import java.util.ArrayList;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.game.Actors.CellActor;
import com.mygdx.game.Actors.ShipActor;
import com.mygdx.game.BatalhaNaval.LogicGame;
import com.mygdx.game.BatalhaNaval.Ship;
import com.mygdx.game.BatalhaNaval.Tabuleiro;

public class MainGame extends ScreenAdapter {
	private static final float TIMER_DELAY = 0.5f;
    private static final float SOUND_DELAY = 1f;
    private static final float SCREEN_DELAY = 3f;
    private static final int VIEWPORT_WIDTH = 1200;
    private static final int VIEWPORT_HEIGHT = 800;
    private static final float BACKGROUND_MUSIC_VOLUME = 0.1f;

	private Stage stage;
	private Table table;
	private LogicGame logicGame;
	private Tabuleiro tabuleiro;
	private AssetManager assetManager;
	private Music backgroundMusic;
	private Sound effectSoundFinal;
	private Dificuldade dificuldade;
	private Timer timer;
	private BattleShipGame game;

	public MainGame(BattleShipGame game, Dificuldade dificuldade) {
		this.game = game;
		this.dificuldade = dificuldade;
	}

	@Override
	public void show() {
		// Loads assets for the game
		assetManager = loadAssets();

		timer = new Timer();
		Task task = new com.badlogic.gdx.utils.Timer.Task() {
			@Override
			public void run() {
				if (logicGame.isJogoTerminado()) {
					backgroundMusic.stop();

					if (logicGame.jogadorVenceu()) {
						effectSoundFinal = assetManager.get("maingame/sound/sound_win.wav", Sound.class);
						Gdx.app.log("Status", "Jogador venceu!");
					} else {
						effectSoundFinal = assetManager.get("maingame/sound/sound_lose.wav", Sound.class);
						Gdx.app.log("Status", "Jogador perdeu!");
					}

					Timer.schedule(new Task() {
						@Override
						public void run() {
							effectSoundFinal.play();
						}
					}, 1);

					Timer.schedule(new Task() {
						@Override
						public void run() {
							game.setScreen(new GameOverScreen(game, logicGame));
						}
					}, 3);

					this.cancel();

				}
			}
		};
		timer.scheduleTask(task, 0f, 0.5f);

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
		logicGame = new LogicGame(10, 10, navios, dificuldade);
		tabuleiro = logicGame.getTabuleiro();
		tabuleiro.printTabuCells();

		/* Background music */
		backgroundMusic = assetManager.get("maingame/sound/backgroundsound.wav", Music.class);
		backgroundMusic.setLooping(true);
		backgroundMusic.setVolume(0.1f);
		backgroundMusic.play();

		// Create UI
		stage = new Stage(new FitViewport(1200, 800));

		table = new Table();
		table.defaults().pad(5); // set the default size and padding of each cell
		// table.debug();

		for (int row = 0; row < 10; row++) {
			for (int col = 0; col < 10; col++) {
				com.mygdx.game.BatalhaNaval.Cell cell = tabuleiro.getCell(row, col);
				CellActor cellTest = new CellActor(assetManager, cell, logicGame);
				table.add(cellTest).size(64);
			}
			ShipActor shipActor = new ShipActor(assetManager, logicGame.getNavios().get(row));
			table.add(shipActor).height(48).width(256);
			table.row();
		}

		table.pack(); // resize the table to fit its contents
		table.setPosition(
				(stage.getWidth() - table.getWidth()) / 2f,
				(stage.getHeight() - table.getHeight()) / 2f); // center the table on the stage

		stage.addActor(table); // add the table to the stage

		Gdx.input.setInputProcessor(stage); // set the stage as the input processor
	}

	private AssetManager loadAssets() {
		AssetManager assetManager = new AssetManager();
		assetManager.load("maingame/board/water.png", Texture.class);
		assetManager.load("maingame/board/miss.png", Texture.class);
		assetManager.load("maingame/board/hit.png", Texture.class);
		assetManager.load("maingame/navios/navio_2_explodido.png", Texture.class);
		assetManager.load("maingame/navios/navio_2.png", Texture.class);
		assetManager.load("maingame/navios/navio_3_explodido.png", Texture.class);
		assetManager.load("maingame/navios/navio_3.png", Texture.class);
		assetManager.load("maingame/navios/navio_4_explodido.png", Texture.class);
		assetManager.load("maingame/navios/navio_4.png", Texture.class);
		assetManager.load("maingame/navios/navio_5_explodido.png", Texture.class);
		assetManager.load("maingame/navios/navio_5.png", Texture.class);
		assetManager.load("maingame/sound/sound_miss.wav", Sound.class);
		assetManager.load("maingame/sound/sound_hit.wav", Sound.class);
		assetManager.load("maingame/sound/sound_win.wav", Sound.class);
		assetManager.load("maingame/sound/sound_lose.wav", Sound.class);
		assetManager.load("maingame/sound/sound_explosionship.wav", Sound.class);
		assetManager.load("maingame/sound/backgroundsound.wav", Music.class);
		assetManager.finishLoading();

		return assetManager;
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(255, 255, 255, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.draw();
		stage.act(Gdx.graphics.getDeltaTime());
	}

	@Override
	public void dispose() {
		stage.dispose();
		assetManager.dispose();
	}

	@Override
	public void resize(int width, int height) {
		stage.getViewport().update(width, height, true);
	}

	@Override
	public void hide() {
		Gdx.input.setInputProcessor(null);
		dispose();
	}

}