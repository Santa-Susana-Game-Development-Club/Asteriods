package com.sshsgd.asteroids;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector2;
import com.sshsgd.asteroids.MyConstants.States;
import com.sshsgd.asteroids.managers.*;

public class Game extends ApplicationAdapter {
	
	public static String Title = "Asteroids";
	
	private int frames;
	public static int fps;
	private float fpsTime;
	
	public static Vector2 SIZE, CENTER;
	
	private GameStateManager gsm;
	
	private final States defaultState = States.Play;
	
	@Override
	public void create () {
		
		SIZE = new Vector2();
		CENTER = new Vector2();
		int width = Gdx.graphics.getWidth();
		int height = Gdx.graphics.getHeight();
		SIZE.set(width, height);
		CENTER.set(width * .5f, height * .5f);
		
		frames = 0;
		fpsTime = 0;
		
		gsm = new GameStateManager(defaultState);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		float dt = Gdx.graphics.getDeltaTime();
		
		gsm.handleInput();
		gsm.update(dt);
		gsm.draw(dt);
		
		fpsTime += dt;
		frames++;
		if(fpsTime > 1) {
			Game.fps = frames;
			frames = 0;
			fpsTime = 0;
		}
		Gdx.graphics.setTitle(String.format("%s | FPS: %d", Game.Title, Game.fps));
	}

	@Override
	public void resize(int width, int height) {
		SIZE.set(width, height);
		CENTER.set(width * .5f, height * .5f);
	}

	@Override
	public void dispose() {
		
	}
}
