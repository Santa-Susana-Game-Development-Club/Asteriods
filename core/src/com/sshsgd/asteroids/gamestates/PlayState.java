package com.sshsgd.asteroids.gamestates;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sshsgd.asteroids.Game;
import com.sshsgd.asteroids.MyCamera;
import com.sshsgd.asteroids.MyConstants;
import com.sshsgd.asteroids.entities.*;
import com.sshsgd.asteroids.managers.GameStateManager;

public class PlayState extends GameState {

	private MyCamera cam;
	private Viewport view;
	
	private Player p;
	
	public PlayState(GameStateManager gsm) {
		super(gsm);
	}

	@Override
	protected void init() {
		cam = new MyCamera();
		view = new StretchViewport(MyConstants.WOLRD_WIDTH, MyConstants.WORLD_HEIGHT, cam);
		view.apply(true);
		view.update((int)Game.SIZE.x, (int)Game.SIZE.y, true);
		p = new Player();
	}

	@Override
	public void handleInput() {
		p.handleInput();
	}

	@Override
	public void update(float dt) {
		p.update(dt);

	}

	@Override
	public void draw(SpriteBatch sb, ShapeRenderer sr, float dt) {
		// TODO Auto-generated method stub
		sr.begin(ShapeType.Line);
		sr.setProjectionMatrix(cam.combined);
		p.draw(sb, sr, dt);
		sr.end();
	}

	@Override
	public void resize(int width, int height) {
		view.update(width, height, true);
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}
