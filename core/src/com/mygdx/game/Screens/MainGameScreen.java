package com.mygdx.game.Screens;

import java.util.ArrayList;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.game.BattleShipGame;
import com.mygdx.game.Actors.CellActor;
import com.mygdx.game.Actors.ShipActor;
import com.mygdx.game.Logic.Dificuldade;
import com.mygdx.game.Logic.LogicGame;
import com.mygdx.game.Logic.Ship;
import com.mygdx.game.Logic.Tabuleiro;

public class MainGameScreen extends ScreenAdapter {
	/* Constantes */
	private static final float DELAY_CHECK_END = 0.5f;
	private static final float END_SOUND_DELAY = 1f;
	private static final float CHANGE_SCREEN_DELAY = 3f;
	private static final int VIEWPORT_WIDTH = 1200;
	private static final int VIEWPORT_HEIGHT = 800;
	private static final int PAD_BETWEEN_CELLS = 5;
	private static final float BACKGROUND_MUSIC_VOLUME = 0.3f;
	private static final String BOMBAS_MSG = "Quantidade de bombas: "; 

	/* Parte lógica do jogo */
	private LogicGame logicGame;
	private Tabuleiro tabuleiro;
	private Dificuldade dificuldade;

	/* LibGDX */
	private BattleShipGame game;
	private Stage stage;
	private Table table;
	private AssetManager assetManager;
	private Music backgroundMusic;
	private Sound effectSoundFinal;
	private Timer timer;
	private Label labelQntdBombas; 

	/**
	 * Construtor da classe MainGame.
	 *
	 * @param game        Instância do jogo BattleShipGame.
	 * @param dificuldade Nível de dificuldade escolhido.
	 */
	public MainGameScreen(BattleShipGame game, Dificuldade dificuldade) {
		this.game = game;
		this.dificuldade = dificuldade;
	}

	/**
	 * Inicializa os recursos necessários para o jogo, como os assets, a lógica do
	 * jogo e a interface do usuário.
	 */
	@Override
	public void show() {
		loadAssets();
		createGameLogic();
		createUI();
		createTimer();
	}

	/**
	 * Carrega todos os assets necessários para o jogo.
	 */
	private void loadAssets() {
		assetManager = new AssetManager();
		/* Texturas */
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

		/* Sons e músicas */
		assetManager.load("maingame/sound/sound_miss.wav", Sound.class);
		assetManager.load("maingame/sound/sound_hit.wav", Sound.class);
		assetManager.load("maingame/sound/sound_win.wav", Sound.class);
		assetManager.load("maingame/sound/sound_lose.wav", Sound.class);
		assetManager.load("maingame/sound/sound_explosionship.wav", Sound.class);
		assetManager.load("maingame/sound/backgroundsound.wav", Music.class);
		assetManager.finishLoading();

		/* Definir parametros para a música de fundo */
		backgroundMusic = assetManager.get("maingame/sound/backgroundsound.wav");
		backgroundMusic.setVolume(BACKGROUND_MUSIC_VOLUME);
	}

	/**
	* Método responsável por criar a lógica do jogo, incluindo o tabuleiro e os navios.
	*/
	private void createGameLogic() {
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
		this.logicGame = new LogicGame(10, 10, navios, this.dificuldade);
		this.tabuleiro = logicGame.getTabuleiro();
	}

	/**
	 * 
	 * Método responsável por criar e iniciar a interface do usuário, incluindo o stage e a
	 * table.
	 */
	private void createUI() {
		stage = new Stage(new FitViewport(VIEWPORT_WIDTH, VIEWPORT_HEIGHT));
		backgroundMusic.play();

		table = new Table();
		table.defaults().pad(PAD_BETWEEN_CELLS);

		/* Tabuleiro é desenhado aqui, junto com os navios */
		for (int row = 0; row < 10; row++) {
			for (int col = 0; col < 10; col++) {
				com.mygdx.game.Logic.Cell cell = tabuleiro.getCell(row, col);
				CellActor cellTest = new CellActor(assetManager, cell, logicGame);
				table.add(cellTest).size(64);
			}
			ShipActor shipActor = new ShipActor(assetManager, logicGame.getNavios().get(row));
			table.add(shipActor).height(48).width(256);
			table.row();
		}

		LabelStyle labelStyle = new LabelStyle (new BitmapFont(), Color.BLACK);
		labelStyle.font.getData().setScale(2.0f);
		labelQntdBombas = new Label(BOMBAS_MSG, labelStyle);
		table.add(labelQntdBombas).colspan(10).expand();

		// Ajusta o tamanho da table para caber todos os seus elementos
		table.pack();

		// Centralizar a table no stage
		table.setPosition(
				(stage.getWidth() - table.getWidth()) / 2f,
				(stage.getHeight() - table.getHeight()) / 2f
		);

		stage.addActor(table);
		Gdx.input.setInputProcessor(stage);
	}
	/*
	 * Método responsável por criar o timer que verifica se o jogo terminou a cada DELAY_CHECK_END.
	 */
	private void createTimer() {
		timer = new Timer();
		Task task = new com.badlogic.gdx.utils.Timer.Task() {
			@Override
			public void run() {
				handleGameEnd();
			}
		};
		timer.scheduleTask(task, 0f, DELAY_CHECK_END);
	}

	/*
	 * Método responsável por verificar se o jogo terminou e, caso tenha terminado, tocar o som de vitória ou derrota.
	 * Também troca de tela com a instância do jogo para a tela final. 
	 */
	private void handleGameEnd() {
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
			}, END_SOUND_DELAY);

			Timer.schedule(new Task() {
				@Override
				public void run() {
					game.setScreen(new GameOverScreen(game, logicGame));
				}
			}, CHANGE_SCREEN_DELAY);

			timer.clear();
		}
	}

	@Override
	public void render(float delta) {
		labelQntdBombas.setText(BOMBAS_MSG + logicGame.getQntdBombas()); // Atualiza a quantidade de bombas

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