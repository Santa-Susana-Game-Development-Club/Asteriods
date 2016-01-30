package com.sshsgd.asteroids.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.sshsgd.asteroids.Game;
import com.sshsgd.asteroids.MyConstants;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = MyConstants.WOLRD_WIDTH / 2;
		config.height = MyConstants.WORLD_HEIGHT / 2;
		new LwjglApplication(new Game(), config);
	}
}
