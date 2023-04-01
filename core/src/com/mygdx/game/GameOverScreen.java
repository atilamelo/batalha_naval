package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
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
import com.mygdx.game.BatalhaNaval.LogicGame;

public class GameOverScreen extends ScreenAdapter {
    private Stage stage;
    private Table table_background;
    private BattleShipGame game;
    private Skin skin;
    private Table table_menu;
    private ImageTextButton restartButton; 
    private ImageTextButton exitButton;
    private LogicGame logicGame; 

    public GameOverScreen(BattleShipGame game, LogicGame logicGame) {
        this.game = game;
        this.logicGame = logicGame;
    }

    @Override
    public void show() {
        /* Create UI */
        stage = new Stage(new StretchViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));

        // Fill background 
        table_background = new Table();
        table_background.setFillParent(true);
        table_background.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        // if(logicGame.jogadorVenceu()){
            table_background.background(new TextureRegionDrawable(new TextureRegion(new Texture("gameoverscreen/win_background.png"))));
        // }else{  
        //     table_background.background(new TextureRegionDrawable(new TextureRegion(new Texture("gameoverscreen/lose_background.png"))));
        // }
        stage.addActor(table_background);


        table_menu = new Table(); 

        skin = new Skin(Gdx.files.internal("menuscreen/dificulty_assets/assets.json"));        
        restartButton = new ImageTextButton("Jogar Novamente", skin);
        
        restartButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                game.setScreen(new MenuDificuldade(game));
            }
        });

        exitButton = new ImageTextButton("Sair", skin, "default");

        exitButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                Gdx.app.exit();
            }
        });

        table_menu.add(restartButton).width(200).height(75);
        table_menu.add(exitButton).width(200).height(75);
        table_menu.pack();
        table_menu.setPosition( 
            (stage.getWidth() - table_menu.getWidth()) / 2f,
            (stage.getHeight() - table_menu.getHeight()) / 8f
        ); 
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
    public void dispose() {
        stage.dispose();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
        stage.getViewport().apply(true); // apply the viewport changes
        stage.getCamera().position.set(stage.getWidth() / 2, stage.getHeight() / 2, 0); // center the camera on the
        table_background.setSize(width, height);

    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }
}
