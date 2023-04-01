package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ButtonGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.mygdx.game.BattleShipGame;
import com.mygdx.game.Logic.Dificuldade;

public class DificuldadeMenuScreen extends ScreenAdapter {
    private Stage stage;
    private Table table_background;
    private AssetManager assetManager;
    private BattleShipGame game;
    private ImageTextButton easyButton;
    private ImageTextButton mediumButton;
    private ImageTextButton hardButton;
    private ImageTextButton okButton;
    private ButtonGroup buttonGroup;
    private Skin skin;
    private Table table_menu;

    public DificuldadeMenuScreen(BattleShipGame game) {
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
        table_menu = new Table();
        // table_menu.debug();
        table_menu.setWidth(stage.getWidth());
        table_menu.align(Align.center | Align.top);

        table_menu.setPosition(0, Gdx.graphics.getHeight());

        skin = new Skin(Gdx.files.internal("menuscreen/dificulty_assets/assets.json"));
        
        easyButton = new ImageTextButton("Fácil", skin, "dificuldadefacil");
        mediumButton = new ImageTextButton("Médio", skin, "dificuldademedia");
        hardButton = new ImageTextButton("Difícil", skin, "dificuldadedificil");
        okButton = new ImageTextButton(" Ok ", skin, "default");
        
        
        buttonGroup = new ButtonGroup(easyButton, mediumButton, hardButton);

        okButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                Button button_checked = DificuldadeMenuScreen.this.buttonGroup.getChecked();
                Dificuldade dificuldade = null; 

                if(button_checked == DificuldadeMenuScreen.this.easyButton){
                    dificuldade = Dificuldade.FACIL; 
                } else if(button_checked == DificuldadeMenuScreen.this.mediumButton){
                    dificuldade = Dificuldade.MEDIO; 
                } else if(button_checked == DificuldadeMenuScreen.this.hardButton){
                    dificuldade = Dificuldade.DIFICIL; 
                }

                game.setScreen(new MainGameScreen(game, dificuldade));
            }
        });

        Image logo = new Image(new Texture("menuscreen/logo.png"));

        table_menu.padTop(20);
        table_menu.add(logo).width(500).height(130);
        table_menu.row();  
        table_menu.add(easyButton).width(300).height(50).padTop(50);
        table_menu.row();
        table_menu.add(mediumButton).width(300).height(50);
        table_menu.row();
        table_menu.add(hardButton).width(300).height(50);
        table_menu.row();
        table_menu.add(okButton).width(100).height(25);

        table_menu.pack();

        table_menu.setPosition( 
            (stage.getWidth() - table_menu.getWidth()) / 2f,
            (stage.getHeight() - table_menu.getHeight())
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
        assetManager.dispose();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
        stage.getViewport().apply(true); // apply the viewport changes
        stage.getCamera().position.set(stage.getWidth() / 2, stage.getHeight() / 2, 0); // center the camera on the
                                                                                        // stage

    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }
}
