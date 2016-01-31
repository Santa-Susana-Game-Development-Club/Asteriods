package com.sshsgd.asteroids.entities;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.sshsgd.asteroids.MyConstants;

public class Bullet {

	private float time;
	private boolean shouldRemove;
	private Vector2 pos, vel;
	
	public static final float speed = MyConstants.WOLRD_WIDTH * .8f;
	
	public Bullet(Entity e) {
		vel = new Vector2(MyConstants.velocityFromAngle(e.getRadians(), speed));
		pos = new Vector2(e.getPos());
	}

	public void update(float dt) {
		time += dt;
		if(time > .6f) {
			shouldRemove = true;
		}
		
		pos.x += vel.x * dt;
		pos.y += vel.y * dt;

		pos.x = MyConstants.wrap(pos.x, 0, MyConstants.WOLRD_WIDTH);
		pos.y = MyConstants.wrap(pos.y, 0, MyConstants.WORLD_HEIGHT);
	}
	
	public void draw(ShapeRenderer sr) {
		sr.setColor(MyConstants.randomColor());
		sr.rect(pos.x, pos.y, 2, 2);
	}
	
	public float getTime() {
		return time;
	}

	public void setTime(float time) {
		this.time = time;
	}

	public boolean isShouldRemove() {
		return shouldRemove;
	}

	public void setShouldRemove(boolean shouldRemove) {
		this.shouldRemove = shouldRemove;
	}

	public Vector2 getPos() {
		return pos;
	}

	public void setPos(Vector2 pos) {
		this.pos = pos;
	}

	public Vector2 getVel() {
		return vel;
	}

	public void setVel(Vector2 vel) {
		this.vel = vel;
	}
	
	
}
