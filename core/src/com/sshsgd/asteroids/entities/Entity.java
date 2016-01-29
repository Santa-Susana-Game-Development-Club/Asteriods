package com.sshsgd.asteroids.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public abstract class Entity {

	protected Vector2 vel;
	protected Vector2 pos;
	
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
		this.pos = pos;
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
	
}
