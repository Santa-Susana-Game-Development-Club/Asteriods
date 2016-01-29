package com.sshsgd.asteroids.managers;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.sshsgd.asteroids.MyConstants.States;
import com.sshsgd.asteroids.gamestates.*;

public class GameStateManager {

	private SpriteBatch sb;
	private ShapeRenderer sr;
	
	private GameState currentState;
	
	public GameStateManager(States state) {
		sb = new SpriteBatch();
		sr = new ShapeRenderer();
		setState(state);
	}
	
	public void setState(States state) {
		if(currentState != null) currentState.dispose();
		if(state == States.Splash) {
			currentState = new SplashState(this);
		}
		if(state == States.Title) {
			currentState = new TitleState(this);
		}
		if(state == States.Play) {
			currentState = new PlayState(this);
		}
		if(state == States.GameOver) {
			currentState = new GameOverState(this);
		}
	}
	
	public void handleInput() {
		currentState.handleInput();
	}
	
	public void update(float dt) {
		currentState.update(dt);
	}
	
	public void draw(float dt) {
		currentState.draw(sb, sr, dt);
	}
	
	public void resize(int width, int height) {
		currentState.resize(width, height);
	}
	
	public void dispose() {
		currentState.dispose();
	}
	
}
