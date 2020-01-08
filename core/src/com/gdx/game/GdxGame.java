package com.gdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TiledDrawable;
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
    public final Settings settings;
    public TextButton.TextButtonStyle txtBtnStyle;
    public TextureRegion[][] splittedTiles;
    public BitmapFont buttonFont;
    public BitmapFont titleFont;
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

    public void setMusic(String file, boolean looping) {
        if (music != null) {
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
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("ARCADE_N.TTF"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameters = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameters.size = 20;
        parameters.borderColor = Color.BLACK;
        parameters.borderWidth = 1;
        parameters.color = Color.ORANGE;
        buttonFont = generator.generateFont(parameters);
        parameters.size = 50;
        parameters.color = Color.RED;
        parameters.borderWidth = 3;
        parameters.shadowOffsetX = 1;
        parameters.shadowColor = Color.BROWN;
        titleFont = generator.generateFont(parameters);
        generator.dispose();

        Texture tiles = new Texture(Gdx.files.internal("mappa_text_low_res/rogueliketilesx3.png"));
        splittedTiles = TextureRegion.split(tiles, 25, 25);

        TiledDrawable wallBrightDraw = new TiledDrawable(splittedTiles[1][2]);
        TiledDrawable wallNormalDraw = new TiledDrawable(splittedTiles[1][1]);
        TiledDrawable wallDarkDraw = new TiledDrawable(splittedTiles[1][3]);
        txtBtnStyle = new TextButton.TextButtonStyle(null, null, null, buttonFont);
        txtBtnStyle.up = wallBrightDraw;
        txtBtnStyle.over = wallNormalDraw;
        txtBtnStyle.down = wallDarkDraw;
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
