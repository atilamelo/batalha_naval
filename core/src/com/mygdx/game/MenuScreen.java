package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.StretchViewport;


public class MenuScreen extends ScreenAdapter{
    private Stage stage;
    private Table table_background;
    private AssetManager assetManager;
    private BattleShipGame game;
    private ImageTextButton startButton; 
    // private ImageTextButton quitButton; 
    private Skin skin; 
    private Table table_menu;

    public MenuScreen(BattleShipGame game) {
        this.game = game;
    }

    @Override
    public void show() {
        // Loads assets for the game
        assetManager = new AssetManager();
        assetManager.load("menuscreen/background_menu.png", Texture.class);
        assetManager.finishLoading();

        /* Create UI */
        stage = new Stage(new StretchViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));

        // Fill background 
        table_background = new Table();
        table_background.setFillParent(true);
        table_background.background(new TextureRegionDrawable(new TextureRegion(assetManager.get("menuscreen/background_menu.png", Texture.class))));
        stage.addActor(table_background);


        /* Create menu buttons */
        skin = new Skin(Gdx.files.internal("menuscreen/uiskin/uiskin.json"));
        startButton = new ImageTextButton(" Iniciar ", skin);
        startButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                game.setScreen(new MainGame(game));
            }
        });

        startButton.setSize(300, 200);
        table_menu = new Table();
        table_menu.debug();
        table_menu.padTop(100);
        table_menu.add(startButton);
        table_menu.pack();

        table_menu.setPosition(
            (stage.getWidth() - table_menu.getWidth()) / 2f,
            (stage.getHeight() - table_menu.getHeight())
        ); // center the table on the stage
        stage.addActor(table_menu);

        Gdx.input.setInputProcessor(stage); // set the stage as the input processor
    }

    @Override 
    public void render(float delta) {
        Gdx.gl.glClearColor(255, 255, 255, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(delta);
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
        stage.getViewport().apply(true); // apply the viewport changes
        stage.getCamera().position.set(stage.getWidth() / 2, stage.getHeight() / 2, 0); // center the camera on the stage
    
    }

    @Override
    public void hide(){
        Gdx.input.setInputProcessor(null);
    }
}
