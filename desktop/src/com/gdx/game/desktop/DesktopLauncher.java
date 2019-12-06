package com.gdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.gdx.game.GdxGame;

public class DesktopLauncher {

    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
//        config.width = 1024;
//        config.height = 576;
        config.width = 800;
        config.height = 600;
        //config.fullscreen = true;
        new LwjglApplication(new GdxGame(new FitViewport(config.width, config.height)), config);
    }
}
