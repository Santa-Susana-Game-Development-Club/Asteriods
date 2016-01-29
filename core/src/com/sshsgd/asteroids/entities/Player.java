package com.sshsgd.asteroids.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.sshsgd.asteroids.MyConstants;
import com.sshsgd.asteroids.managers.input.MyInput;

public class Player extends Entity {

	private Vector2[] vertices;
	private float radians;
	private float movementR;
	
	private float speed; 
	private final float maxSpeed = 500;
	
	public Player() {
		super();
		
		vertices = new Vector2[4];
		for (int i = 0; i < vertices.length; i++) {
			vertices[i] = new Vector2();
		}
		
		pos.set(MyConstants.WOLRD_WIDTH * .5f, MyConstants.WORLD_HEIGHT * .5f);
		
	}
	
	private void setVertices() {
		vertices[0].x = pos.x + (MathUtils.cos(radians) * 50);
		vertices[0].y = pos.y + (MathUtils.sin(radians) * 50);

		vertices[1].x = pos.x + (MathUtils.cos(radians + ((5 * MathUtils.PI) / 6)) * 50);
		vertices[1].y = pos.y + (MathUtils.sin(radians + ((5 * MathUtils.PI) / 6)) * 50);
		
		vertices[2].x = pos.x + (MathUtils.cos(radians + MathUtils.PI) * 25);
		vertices[2].y = pos.y + (MathUtils.sin(radians + MathUtils.PI) * 25);

		vertices[3].x = pos.x + (MathUtils.cos(radians + ((7 * MathUtils.PI) / 6)) * 50);
		vertices[3].y = pos.y + (MathUtils.sin(radians + ((7 * MathUtils.PI) / 6)) * 50);
	}
	
	public void handleInput() {
		if(MyInput.keyDown(MyInput.LEFT)) {
			radians += (MathUtils.PI * Gdx.graphics.getDeltaTime());
		}
		if(MyInput.keyDown(MyInput.RIGHT)) {
			radians -= (MathUtils.PI * Gdx.graphics.getDeltaTime());
		}
		if(MyInput.keyDown(MyInput.UP)) {
			speed += 5;
			movementR = radians;
		}
		if(MyInput.keyDown(MyInput.DOWN)) {
			speed -= 5;
			movementR = radians;
		}
		speed = MathUtils.clamp(speed, -maxSpeed, maxSpeed);
		speed *= .999f;
		System.out.println(speed);
	}
	
	public void update(float dt) {
		setVertices();
		setVel(MyConstants.velocityFromAngle(movementR, speed * dt));
		
		pos.x += vel.x;
		pos.y += vel.y;

		pos.x = MyConstants.wrap(pos.x, -50, MyConstants.WOLRD_WIDTH + 50);
		pos.y = MyConstants.wrap(pos.y, -50, MyConstants.WORLD_HEIGHT + 50);
		
	}

	@Override
	public void draw(SpriteBatch sb, ShapeRenderer sr, float dt) {
		for (int i = 1; i < vertices.length; i++) {
			sr.line(vertices[i - 1], vertices[i]);
		}
		sr.line(vertices[0], vertices[3]);
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}
