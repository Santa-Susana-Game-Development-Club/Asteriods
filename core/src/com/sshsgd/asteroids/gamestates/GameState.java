package com.sshsgd.asteroids.gamestates;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.sshsgd.asteroids.managers.GameStateManager;

public abstract class GameState {

	protected GameStateManager gsm;

	public GameState(GameStateManager gsm) {
		super();
		this.gsm = gsm;
		init();
	}

	protected abstract void init();
	public abstract void handleInput();
	public abstract void update(float dt);
	public abstract void draw(SpriteBatch sb, ShapeRenderer sr, float dt);
	public abstract void resize(int width, int height);
	public abstract void dispose();
	
}
