package com.gdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.gdx.game.screens.TitleScreen;
import com.gdx.game.settings.Settings;
import java.util.LinkedList;

public class GdxGame extends Game {

    public Skin skin;
    public Viewport vp;
    public AssetManager assetManager;
    public static GdxGame game;
    public static final float SCALE = 6.0f;
    private Music music;
    // private static final float DEFAULT_VOLUME = 0.5f;

    public LinkedList<Body> bodyToRemove = new LinkedList<Body>();

    public GdxGame(Viewport vp) {
        this.vp = vp;
        assetManager = new AssetManager();
        game = this;

    }

    /*
    public static float getDEFAULT_VOLUME() {
        return DEFAULT_VOLUME;
    }*/
    public Music getMusic() {
        return music;
    }

    @Override
    public void create() {
        skin = new Skin(Gdx.files.internal("skin/expee-ui.json"));

        this.setScreen(new TitleScreen(this));

        Settings.initAudio();

    }

    @Override
    public void render() {
        vp.apply();
        super.render();
    }

    @Override
    public void dispose() {
    }

    @Override
    public void resize(int width, int height) {
        vp.update(width, height);
    }

}
