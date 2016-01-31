package com.sshsgd.asteroids.gamestates;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sshsgd.asteroids.Game;
import com.sshsgd.asteroids.MyCamera;
import com.sshsgd.asteroids.MyConstants;
import com.sshsgd.asteroids.entities.*;
import com.sshsgd.asteroids.entities.Asteroid.Size;
import com.sshsgd.asteroids.managers.GameStateManager;
import com.sshsgd.asteroids.managers.input.MyInput;

public class PlayState extends GameState {

	private MyCamera cam;
	private Viewport view;
	private Player player;
	private Array<Asteroid> asteroids;
	private Array<Particles> particles;
	private Array<Bullet> bullets;
	
	private int level;
	
	private float deadTime;
	
	public PlayState(GameStateManager gsm) {
		super(gsm);
	}

	@Override
	protected void init() {
		cam = new MyCamera();
		view = new StretchViewport(MyConstants.WOLRD_WIDTH, MyConstants.WORLD_HEIGHT, cam);
		view.apply(true);
		view.update((int)Game.SIZE.x, (int)Game.SIZE.y, true);
		player = new Player();
		asteroids = new Array<Asteroid>();
		particles = new Array<Particles>();
		bullets = new Array<Bullet>();
		
		level = 0;
		
		deadTime = 0;
	}

	@Override
	public void handleInput() {
		if(player.isAlive())player.handleInput();
		if(MyInput.keyPressed(MyInput.SHOOT)) {
			if(player.isAlive() && bullets.size < 5) {
				bullets.add(new Bullet(player));
			}
		}
	}

	@Override
	public void update(float dt) {
		if(asteroids.size < 1) {
			level++;
			createAsteroids();
		}
		if(player.isAlive()) {
			deadTime = 0;
			player.update(dt);
		} else {
			deadTime += dt;
			if(deadTime > 1) {
				player.setPos(MyConstants.WOLRD_WIDTH * .5f, MyConstants.WORLD_HEIGHT * .5f);
				player.setRadians(0);
				player.setSpeed(0);
				player.update(0);
				player.setAlive(true);
			}
		}
		for (Asteroid asteroid : asteroids) {
			asteroid.update(dt);
			if(player.isAlive() && asteroid.intersects(player)) {
				killPlayer();
				destroyAsteroid(asteroid);
			}
		}
		for (Bullet bullet : bullets) {
			bullet.update(dt);
			if(bullet.isShouldRemove()) {
				bullets.removeValue(bullet, true);
				continue;
			}
		}
		for (Asteroid asteroid : asteroids) {
			for (Bullet bullet : bullets) {
				if(asteroid.contains(bullet.getPos())) {
					destroyAsteroid(asteroid);
					bullets.removeValue(bullet, true);
					continue;
				}
			}
		}
	}

	@Override
	public void draw(SpriteBatch sb, ShapeRenderer sr, float dt) {
		sr.begin(ShapeType.Line);
		sr.setProjectionMatrix(cam.combined);
		if(player.isAlive())player.draw(sb, sr, dt);
		for (Asteroid asteroid : asteroids) {
			asteroid.draw(sb, sr, dt);
		}
		sr.end();
		sr.begin(ShapeType.Filled);
		for (Particles particle : particles) {
			particle.draw(sr, dt);
			if(particle.isShouldRemove()) {
				particles.removeValue(particle, true);
				break;
			}
		}
		for (Bullet bullet : bullets) {
			bullet.draw(sr);
		}
		sr.end();
	}

	@Override
	public void resize(int width, int height) {
		view.update(width, height, true);
	}

	@Override
	public void dispose() {
		particles.clear();
		asteroids.clear();
	}
	
	private void killPlayer() {
		particles.add(new Particles(player));
		player.setAlive(false);
		player.setPos(MyConstants.WOLRD_WIDTH * .5f, MyConstants.WORLD_HEIGHT * .5f);
		player.setRadians(0);
		player.setSpeed(0);
	}
	
	private void destroyAsteroid(Asteroid asteroid) {
		if(asteroids.size > 0) {
			float tempX = asteroid.getX();
			float tempY = asteroid.getY();
			switch (asteroid.getSize()) {
			case Large:
				asteroids.add(new Asteroid(tempX, tempY, Size.Medium));
				asteroids.add(new Asteroid(tempX, tempY, Size.Medium));
				break;
			case Medium:
				asteroids.add(new Asteroid(tempX, tempY, Size.Small));
				asteroids.add(new Asteroid(tempX, tempY, Size.Small));
				break;
			case Small:
				particles.add(new Particles(asteroid));
				break;
			}
			asteroid.dispose();
			asteroids.removeValue(asteroid, true);
		}
	}
	
	private void createAsteroids() {
		int numToSpawn = 4 + level - 1;
		for(int i = 0; i < numToSpawn; i++) {
			float x, y, dx, dy, dist;
			do {
				 x = MathUtils.random(MyConstants.WOLRD_WIDTH);
				 y = MathUtils.random(MyConstants.WORLD_HEIGHT);
				 
				 dx = x - player.getX();
				 dy = y - player.getY();
				 
				 dist = (float) Math.sqrt(dx * dx + dy * dy);
				 
			} while(dist < 175);
			asteroids.add(new Asteroid(x, y, Size.Large));
		}
	}

}
