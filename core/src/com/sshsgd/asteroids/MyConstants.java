package com.sshsgd.asteroids;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class MyConstants {
	public static enum States {
		Splash, Title, Play,
		GameOver
	}
	
	public static final String saveFile = "sueno_americano.sav";
	
	public static enum Direction {
		LEFT, RIGHT
	}
	
	public static final int WOLRD_WIDTH = 1280;
	public static final int WORLD_HEIGHT = sixteenNineResolution(WOLRD_WIDTH);
	
	public static Vector2 randomVelocity(float speed) {
		return velocityFromAngle(MathUtils.random(MathUtils.PI2), speed);
	}
	
	public static Vector2 velocityFromAngle(float radians, float speed) {
		float x = MathUtils.cos(radians) * speed;
		float y = MathUtils.sin(radians) * speed;
		return new Vector2(x, y);
	}
	
	public static Direction randomDirection() {
		if(MathUtils.randomBoolean()) {
			return Direction.RIGHT;
		} else {
			return Direction.LEFT;
		}
	}
	
	public static float distance(float x1,float y1,float x2,float y2) {
		float a = (x2 - x1);
		float b = (y2 - y1);
		return (float) Math.sqrt((Math.pow(a, 2)) + (Math.pow(b, 2)));
	}
	
	public static float distance(Vector2 p1, Vector2 p2) {
		return distance(p1.x, p1.y, p2.x, p2.y);
	}
	
	public static Color randomColor() {
		return new Color(MathUtils.random(), MathUtils.random(), MathUtils.random(), 1);
	}
	
	public static Color rgba(float r, float g, float b, float a) {
		return new Color(r / 255f, g / 255f, b / 255f, a / 255f);
	}
	
	public static boolean booleanArrayContains(boolean b, boolean[] array) {
		for(boolean bool : array) {
			if(bool == b) return true;
		}
		return false;
	}
	
	public static float clamp(float n, float min, float max) {
		float r = n;
		if(r < min) {
			r = min;
		}
		if(r > max) {
			r = max;
		}
		return r;
	}
	
	public static int clamp(int n, int min, int max) {
		int r = n;
		if(r < min) {
			r = min;
		}
		if(r > max) {
			r = max;
		}
		return r;
	}
	
	public static boolean wouldWrap(float n, float min, float max) {
		return n < min || n > max;
	}
	
	public static boolean wouldWrap(int n, int min, int max) {
		return n < min || n > max;
	}
	
	public static float wrap(float n, float min, float max) {
		float r = n;
		if(r < min) {
			r = max;
		}
		if(r > max) {
			r = min;
		}
		return r;
	}
	
	public static int wrap(int n, int min, int max) {
		int r = n;
		if(r < min) {
			r = max;
		}
		if(r > max) {
			r = min;
		}
		return r;
	}
	
	public static boolean inBetween(float n, float min, float max, boolean includeMin, boolean includeMax) {
		boolean greater, less;
		if(includeMin) {
			greater = n >= min;
		} else {
			greater = n > min;
		}
		if(includeMax) {
			less = n <= max;
		} else {
			less = n < max;
		}
		return greater && less;
	}

	public static String getScore(int score) {
		return String.format("%07f", score);
	}
	
	public static String getTime(float currentTime) {
		float seconds = currentTime;
		float minutes = seconds / 60;
		float remainingSeconds = seconds % 60;
		float fmilliseconds = (float) (seconds - Math.floor(seconds));
		fmilliseconds *= 10000;
		int intmilliseconds = (int) fmilliseconds;
		String timeS;
		if(remainingSeconds >= 10) {
			timeS = (int) minutes + ":" + (int) remainingSeconds;
		} else {
			if(seconds < 0) {
				timeS = "-" + (int) minutes + ":0" + (int) Math.abs(remainingSeconds);
			} else {
				timeS = (int) minutes + ":0" + (int) remainingSeconds;
			}
		}
		timeS = timeS + "." + intmilliseconds;
		return timeS;
	}
	
	public static float sixteenNineResolution(float width) {
		return (width * 9f) / 16f;
	}
	
	public static int sixteenNineResolution(int width) {
		return (int) ((width * 9f) / 16f);
	}
	
	public static void drawShapeFromVertexArray(ShapeRenderer sr, Vector2[] vertices) {
		for (int i = 1; i < vertices.length; i++) {
			sr.line(vertices[i - 1], vertices[i]);
		}
		sr.line(vertices[0], vertices[vertices.length - 1]);
	}
	
}
