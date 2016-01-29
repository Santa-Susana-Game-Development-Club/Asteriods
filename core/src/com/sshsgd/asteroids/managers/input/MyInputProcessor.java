package com.sshsgd.asteroids.managers.input;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Input.Keys;

public class MyInputProcessor extends InputAdapter {

	@Override
	public boolean keyDown(int keycode) {
		if(keycode == Keys.LEFT || keycode == Keys.A) {
			MyInput.setKey(MyInput.LEFT, true);
		}
		if(keycode == Keys.RIGHT || keycode == Keys.D) {
			MyInput.setKey(MyInput.RIGHT, true);
		}
		if(keycode == Keys.UP || keycode == Keys.W) {
			MyInput.setKey(MyInput.UP, true);
		}
		if(keycode == Keys.DOWN || keycode == Keys.S) {
			MyInput.setKey(MyInput.DOWN, true);
		}
		if(keycode == Keys.SPACE) {
			MyInput.setKey(MyInput.SHOOT, true);
		}
		if(keycode == Keys.ENTER) {
			MyInput.setKey(MyInput.START, true);
		}
		if(keycode == Keys.ESCAPE) {
			MyInput.setKey(MyInput.BACK, true);
		}
		return true;
	}

	@Override
	public boolean keyUp(int keycode) {
		if(keycode == Keys.LEFT || keycode == Keys.A) {
			MyInput.setKey(MyInput.LEFT, false);
		}
		if(keycode == Keys.RIGHT || keycode == Keys.D) {
			MyInput.setKey(MyInput.RIGHT, false);
		}
		if(keycode == Keys.UP || keycode == Keys.W) {
			MyInput.setKey(MyInput.UP, false);
		}
		if(keycode == Keys.DOWN || keycode == Keys.S) {
			MyInput.setKey(MyInput.DOWN, false);
		}
		if(keycode == Keys.SPACE) {
			MyInput.setKey(MyInput.SHOOT, false);
		}
		if(keycode == Keys.ENTER) {
			MyInput.setKey(MyInput.START, false);
		}
		if(keycode == Keys.ESCAPE) {
			MyInput.setKey(MyInput.BACK, false);
		}
		return true;
	}

}
