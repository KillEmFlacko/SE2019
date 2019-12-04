package com.gdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.gdx.game.screens.TitleScreen;

public class GdxGame extends Game {

    public static Skin skin;

    @Override
    public void create() {
        skin = new Skin(Gdx.files.internal("skin/expee-ui.json"));
        this.setScreen(new TitleScreen(this));
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
    }
}

