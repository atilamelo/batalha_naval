package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.mygdx.game.Screen.GameScreen;

public class MyGdxGame extends ApplicationAdapter {	
	private OrthographicCamera camera;
    private AssetManager assetManager; 
    private GameScreen mainGameScene;

    public MyGdxGame(){
        mainGameScene = new GameScreen(this);
    }

	public AssetManager getAssetManager() {
        return assetManager;
    }

    @Override
	public void create () {
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);
        assetManager = new AssetManager();
        mainGameScene.show();
	}

	@Override
	public void render () {
        // Clear screen
        Gdx.gl.glClearColor(255, 255, 255, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        mainGameScene.render(Gdx.graphics.getDeltaTime());

	}
	
	@Override
	public void dispose () {
        assetManager.dispose();
	}
}
