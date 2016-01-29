package com.sshsgd.asteroids.managers.input;

public class MyInput {

	private static boolean[] keys;
	private static boolean[] pkeys;

	private static final int NUM_KEYS = 7;
	public static final int LEFT = 0;
	public static final int RIGHT = 1;
	public static final int UP = 2;
	public static final int DOWN = 3;
	public static final int SHOOT = 4;
	public static final int START = 5;
	public static final int BACK = 6;
	
	static {
		keys = new boolean[NUM_KEYS];
		pkeys = new boolean[NUM_KEYS];
	}
	
	public static void update() {
		for(int i = 0; i < NUM_KEYS; i++) {
			pkeys[i] = keys[i];
		}
	}
	
	public static void setKey(int k, boolean b) {
		keys[k] = b;
	}
	
	public static boolean keyDown(int k) {
		return keys[k];
	}
	
	public static boolean keyPressed(int k) {
		return keys[k] && !pkeys[k];
	}
	
}
