package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;

public class MyGdxGame extends ApplicationAdapter {	
    private BitmapFont font;
    private SpriteBatch batch;
	private OrthographicCamera camera;
	private ShapeRenderer shapeRenderer;
    private int numRows = 10;
    private int numCols = 10;
    private float squareSize;
    private float padding;


	@Override
	public void create () {
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);  
		
		shapeRenderer = new ShapeRenderer();

		// Calculate the square size and padding based on the screen size
		squareSize = Gdx.graphics.getWidth() / (numCols + 15);
		padding = squareSize / 10f;

		font = new BitmapFont();
        batch = new SpriteBatch();

	}

	@Override
	public void render () {
        // Clear screen
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        // Set the color for the squares
        shapeRenderer.setColor(Color.BLUE);

        // Draw a grid of squares that adapts to the screen size
        float startX = padding;
        float startY = padding;
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                float x = startX + col * (squareSize + padding);
                float y = startY + row * (squareSize + padding);
                shapeRenderer.rect(x, y, squareSize, squareSize);
            }
        }

        // End drawing
        shapeRenderer.end();
        batch.end();
	}
	
	@Override
	public void dispose () {
	}
}
