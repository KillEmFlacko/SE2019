package com.gdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.ObjectSet;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.gdx.game.screens.TitleScreen;
import com.gdx.game.settings.Settings;

public class GdxGame extends Game {

    public Skin skin;
    public Viewport vp;
    public AssetManager assetManager;
    public static GdxGame game;
    public static final float SCALE = 6.0f;
    private Music music = null;
    public Settings settings;
    // private static final float DEFAULT_VOLUME = 0.5f;

    public ObjectSet<Body> bodyToRemove = new ObjectSet<Body>();

    public GdxGame(Viewport vp) {
        this.vp = vp;
        assetManager = new AssetManager();
        game = this;
        this.settings = new Settings();

    }

    /*
    public static float getDEFAULT_VOLUME() {
        return DEFAULT_VOLUME;
    }*/
    public Music getMusic() {
        return music;
    }
    
    public void setMusic(String file, boolean looping){
        if(music != null){
            music.stop();
            music.dispose();
        }
        music = Gdx.audio.newMusic(Gdx.files.internal(file));
        music.setLooping(looping);
        music.setVolume(this.settings.getVolume());
        music.play();
    }
    
    @Override
    public void create() {
        //skin = new Skin(Gdx.files.internal("skin/expee-ui.json"));
        skin = new Skin(Gdx.files.internal("flat/skin/skin.json"));

        this.setScreen(new TitleScreen(this));

    }

    @Override
    public void render() {
        super.render();
        ////////////////REMOVING BODIES//////////////
        for (Body b : GdxGame.game.bodyToRemove) {
            b.getWorld().destroyBody(b);
        }
        bodyToRemove.clear();
        /////////////////////////////////////////////
    }

    @Override
    public void dispose() {
    }

    @Override
    public void resize(int width, int height) {
        vp.update(width, height);
        getScreen().resize(width, height);
    }

}
