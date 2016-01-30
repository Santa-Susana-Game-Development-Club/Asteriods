package com.sshsgd.asteroids.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.sshsgd.asteroids.MyConstants;
import com.sshsgd.asteroids.managers.input.MyInput;

public class Player extends Entity {

	private Vector2[] fire;
	
	private final float maxSpeed = 500;
	
	boolean thrust;
	
	public Player() {
		super();
		
		vertices = new Vector2[4];
		for (int i = 0; i < vertices.length; i++) {
			vertices[i] = new Vector2();
		}
		
		
		radius = 40;
		
		fire = new Vector2[3];
		for (int i = 0; i < fire.length; i++) {
			fire[i] = new Vector2();
		}
		
		pos.set(MyConstants.WOLRD_WIDTH * .5f, MyConstants.WORLD_HEIGHT * .5f);
		
	}
	
	private void setVertices() {
		vertices[0].x = pos.x + (MathUtils.cos(radians) * radius);
		vertices[0].y = pos.y + (MathUtils.sin(radians) * radius);

		vertices[1].x = pos.x + (MathUtils.cos(radians + ((5 * MathUtils.PI) / 6)) * radius);
		vertices[1].y = pos.y + (MathUtils.sin(radians + ((5 * MathUtils.PI) / 6)) * radius);
		
		vertices[2].x = pos.x + (MathUtils.cos(radians + MathUtils.PI) * radius * .5f);
		vertices[2].y = pos.y + (MathUtils.sin(radians + MathUtils.PI) * radius * .5f);

		vertices[3].x = pos.x + (MathUtils.cos(radians + ((7 * MathUtils.PI) / 6)) * radius);
		vertices[3].y = pos.y + (MathUtils.sin(radians + ((7 * MathUtils.PI) / 6)) * radius);

		fire[0].x = pos.x + (MathUtils.cos(radians + ((11 * MathUtils.PI) / 12)) * radius * .65f);
		fire[0].y = pos.y + (MathUtils.sin(radians + ((11 * MathUtils.PI) / 12)) * radius * .65f);

		float distance = MathUtils.random(radius - 5, radius + 10);

		fire[1].x = pos.x + (MathUtils.cos(radians + MathUtils.PI) * distance);
		fire[1].y = pos.y + (MathUtils.sin(radians + MathUtils.PI) * distance);
		
		fire[2].x = pos.x + (MathUtils.cos(radians + ((13 * MathUtils.PI) / 12)) * radius * .65f);
		fire[2].y = pos.y + (MathUtils.sin(radians + ((13 * MathUtils.PI) / 12)) * radius * .65f);
	}
	
	
	
	public void handleInput() {
		thrust = false;
		if(MyInput.keyDown(MyInput.LEFT)) {
			radians += (MathUtils.PI * Gdx.graphics.getDeltaTime());
		}
		if(MyInput.keyDown(MyInput.RIGHT)) {
			radians -= (MathUtils.PI * Gdx.graphics.getDeltaTime());
		}
		if(MyInput.keyDown(MyInput.UP)) {
			thrust = true;
			if(movementR != radians && !(MyInput.keyDown(MyInput.LEFT) || MyInput.keyDown(MyInput.RIGHT))) {
				speed = 0;
			}
			movementR = radians;
			speed += 5;
		}
		if(MyInput.keyDown(MyInput.DOWN)) {
			if(movementR != radians && !(MyInput.keyDown(MyInput.LEFT) || MyInput.keyDown(MyInput.RIGHT))) {
				speed = 0;
			}
			movementR = radians;
			speed -= 5;
		}
		speed *= .999f;
		speed = MathUtils.clamp(speed, -maxSpeed, maxSpeed);
	}
	
	public void update(float dt) {
		setVertices();
		setVel(MyConstants.velocityFromAngle(movementR, speed * dt));
		
		pos.x += vel.x;
		pos.y += vel.y;

		pos.x = MyConstants.wrap(pos.x, - radius, MyConstants.WOLRD_WIDTH + radius);
		pos.y = MyConstants.wrap(pos.y, - radius, MyConstants.WORLD_HEIGHT + radius);
		
	}

	@Override
	public void draw(SpriteBatch sb, ShapeRenderer sr, float dt) {
		sr.setColor(Color.WHITE);
		MyConstants.drawShapeFromVertexArray(sr, vertices);
		sr.setColor(MyConstants.randomColor());
		if(thrust) {
			for (int i = 1; i < fire.length; i++) {
				sr.line(fire[i - 1], fire[i]);
			}
		}
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}
