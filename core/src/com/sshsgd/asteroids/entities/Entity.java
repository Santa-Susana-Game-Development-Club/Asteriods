package com.sshsgd.asteroids.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public abstract class Entity {

	protected Vector2 vel;
	protected Vector2 pos;
	protected float radians, movementR, speed, radius;
	protected Vector2[] vertices;
	
	public Entity() {
		vel = new Vector2();
		pos = new Vector2();
	}
	
	public abstract void draw(SpriteBatch sb, ShapeRenderer sr, float dt);
	public abstract void dispose();
	
	public Vector2 getVel() {
		return vel;
	}
	
	public void setVel(Vector2 vel) {
		this.vel = vel;
	}
	
	public float getVelX() {
		return vel.x;
	}
	
	public void setVelX(float x) {
		this.vel.x = x;
	}
	
	public float getVelY() {
		return vel.y;
	}
	
	public void setVelY(float y) {
		this.vel.y = y;
	}
	
	public Vector2 getPos() {
		return pos;
	}
	public void setPos(Vector2 pos) {
		this.pos.set(pos);
	}
	
	public void setPos(float x, float y) {
		this.pos.set(x, y);
	}
	
	public float getX() {
		return pos.x;
	}
	
	public void setX(float x) {
		pos.x = x;
	}
	
	public float getY() {
		return pos.y;
	}
	
	public void setY(float y) {
		pos.y = y;
	}

	public float getRadius() {
		return radius;
	}

	public void setRadius(float radius) {
		this.radius = radius;
	}
	
	public Vector2[] getVertices() {
		return vertices;
	}

	public float getRadians() {
		return radians;
	}

	public void setRadians(float radians) {
		this.radians = radians;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	public boolean intersects(Entity e) {
		Vector2[] otherV = e.getVertices();
		for(Vector2 v : otherV) {
			if(contains(v)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean contains(Vector2 point) {
		return contains(point.x, point.y);
	}
	
	public boolean contains(float x, float y) {
		boolean b = false;
		for(int i = 0, j = vertices.length - 1; i < vertices.length; j = i++) {
			if((vertices[i].y > y) != (vertices[j].y > y) && (x < (vertices[j].x - vertices[i].x) * (y - vertices[i].y) / (vertices[j].y - vertices[i].y) + vertices[i].x)) {
				b = !b;
			}
		}
		return b;
	}
	
}
