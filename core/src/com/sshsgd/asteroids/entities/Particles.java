package com.sshsgd.asteroids.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.sshsgd.asteroids.MyConstants;

public class Particles {

	private float length;
	private Vector2 pos;
	private boolean shouldRemove;
	private float time;
	
	public Particles(Entity e) {
		super();
		
		this.length = e.getRadius() * 2;
		this.pos = new Vector2(e.getPos());
		time = 0;
		shouldRemove = false;
	}
	
	public void draw(ShapeRenderer sr, float dt) {
		time += dt;
		shouldRemove = time >= .25f;
		float area = length * length;
		float numPoints = area * .005f;
		for(int i = 0; i < numPoints; i++) {
			sr.setColor(MyConstants.randomColor());
			float x = MathUtils.random(pos.x - (length * MathUtils.random(.5f)), pos.x + (length * MathUtils.random(.5f)));
			float y = MathUtils.random(pos.y - (length * MathUtils.random(.5f)), pos.y + (length * MathUtils.random(.5f)));
			sr.rect(MyConstants.wrap(x, 0, MyConstants.WOLRD_WIDTH), MyConstants.wrap(y, 0, MyConstants.WORLD_HEIGHT), 1, 1);
		}
		sr.setColor(Color.WHITE);
		
	}

	public boolean isShouldRemove() {
		return shouldRemove;
	}

	public void setShouldRemove(boolean shouldRemove) {
		this.shouldRemove = shouldRemove;
	}

	
	
}
