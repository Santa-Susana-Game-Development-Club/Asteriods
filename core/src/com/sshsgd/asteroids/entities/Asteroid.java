package com.sshsgd.asteroids.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.sshsgd.asteroids.MyConstants;

public class Asteroid extends Entity {

	public enum Size {
		Small, Medium, Large
	}
	
	private Size size;
	private int numVertices;
	private float[] lengths;
	private float rotation;
	
	public Asteroid(float x, float y, Size s) {
		super();
		this.size = s;
		pos.set(x, y);
		
		switch (size) {
		case Large:
			numVertices = 12;
			radius = 75;
			speed = MathUtils.random(30, 40);
			break;
		case Medium:
			numVertices = 10;
			radius = 50;
			speed = MathUtils.random(60, 70);
			break;
		case Small:
			numVertices = 8;
			radius = 25;
			speed = MathUtils.random(80, 110);
			break;
		}
		
		vertices = new Vector2[numVertices];
		lengths = new float[numVertices];
		
		for (int i = 0; i < numVertices; i++) {
			vertices[i] = new Vector2();
			lengths[i] = MathUtils.random(radius * .5f, radius);
		}
		
		movementR = MathUtils.random(MathUtils.PI * 2);
		
		do {
			rotation = MathUtils.random(-1, 1);
		} while(rotation == 0);
		
		setVertices();
		
	}
	
	private void setVertices() {
		float currentAngle = 0;
		for (int i = 0; i < vertices.length; i++) {
			vertices[i].x = pos.x + MathUtils.cos(radians + currentAngle) * lengths[i];
			vertices[i].y = pos.y + MathUtils.sin(radians + currentAngle) * lengths[i];
			currentAngle += (MathUtils.PI * 2) / numVertices;
		}
	}
	
	public void update(float dt) {
		radians += rotation * dt;
		vel = MyConstants.velocityFromAngle(movementR, speed * dt);
		
		pos.x += vel.x;
		pos.y += vel.y;
		
		setVertices();

		pos.x = MyConstants.wrap(pos.x, - radius, MyConstants.WOLRD_WIDTH + radius);
		pos.y = MyConstants.wrap(pos.y, - radius, MyConstants.WORLD_HEIGHT + radius);
	}
	
	@Override
	public void draw(SpriteBatch sb, ShapeRenderer sr, float dt) {
		sr.setColor(Color.WHITE);
		MyConstants.drawShapeFromVertexArray(sr, vertices);

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	public Size getSize() {
		return size;
	}

	public void setSize(Size size) {
		this.size = size;
	}

}
