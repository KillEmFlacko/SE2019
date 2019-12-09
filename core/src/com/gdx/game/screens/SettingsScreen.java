/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.utils.Align;
import com.gdx.game.GdxGame;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author Giovanni
 */
public class SettingsScreen implements Screen {

    private Stage stage;
    private GdxGame game;
    private Label label1;
    private float colWidth;
    private float rowHeight;
    private final int padding = 15;
    private Label audio;

    SettingsScreen(GdxGame game) throws FileNotFoundException, IOException {
        this.game = game;
        this.stage = new Stage(game.vp);
        initUI();
    }

    private void initUI() {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("ARCADE_N.TTF"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameters = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameters.size = 30;
        parameters.color = Color.YELLOW;
        parameters.borderWidth = 1;
        parameters.borderColor = Color.BLACK;
        BitmapFont font = generator.generateFont(parameters);
        generator.dispose();

        Label.LabelStyle lblStyle = new Label.LabelStyle();
        lblStyle.font = font;

        label1 = new Label("SETTINGS", lblStyle);
        label1.setSize(Gdx.graphics.getWidth(), 30);
        label1.setAlignment(Align.center);
        label1.setPosition(0, Gdx.graphics.getHeight() / 2 - 15 + 250);
        stage.addActor(label1);

        colWidth = Gdx.graphics.getWidth() / 5f;
        rowHeight = Gdx.graphics.getHeight() / 15f;
        TextButton btnButton = new TextButton("Back", GdxGame.game.skin, "default");
        btnButton.setSize(colWidth, rowHeight);
        btnButton.setPosition(padding, padding);
        btnButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                SettingsScreen.this.dispose();
                game.setScreen(new TitleScreen(game));
                return true;
            }
        });
        stage.addActor(btnButton);

        audio = new Label("AUDIO", lblStyle);
        audio.setSize(Gdx.graphics.getWidth(), 30);
        audio.setAlignment(Align.center);
        audio.setPosition(Gdx.graphics.getWidth() / 2 - 15 - 675, Gdx.graphics.getHeight() / 2 + 150);
        stage.addActor(audio);
        
        
        final TextButton btna0 = new TextButton("0", GdxGame.game.skin, "default");
        
        final TextButton btna25 = new TextButton("25", GdxGame.game.skin, "default");
        
        final TextButton btna50 = new TextButton("50", GdxGame.game.skin, "default");
         
        final TextButton btna75 = new TextButton("75", GdxGame.game.skin, "default");

        final TextButton btna100 = new TextButton("100", GdxGame.game.skin, "default");

        btna0.setSize(Gdx.graphics.getWidth() / 20, Gdx.graphics.getHeight() / 15);
        btna0.setPosition(Gdx.graphics.getWidth() / 2 - 145, Gdx.graphics.getHeight() / 2 + 145);
        btna0.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                 btna0.setColor(Color.RED);
                btna25.setColor(Color.WHITE);
                btna50.setColor(Color.WHITE);
                btna75.setColor(Color.WHITE);
                btna100.setColor(Color.WHITE);
                game.getMusic().setVolume(0.0f);
                return true;
            }
        });
        stage.addActor(btna0);
        
        btna25.setSize(Gdx.graphics.getWidth() / 20, Gdx.graphics.getHeight() / 15);
        btna25.setPosition(Gdx.graphics.getWidth() / 2 - 100, Gdx.graphics.getHeight() / 2 + 145);
        btna25.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                btna0.setColor(Color.WHITE);
                btna25.setColor(Color.RED);
                btna50.setColor(Color.WHITE);
                btna75.setColor(Color.WHITE);
                btna100.setColor(Color.WHITE);
                game.getMusic().setVolume(0.25f);
                return true;
            }
        });
        stage.addActor(btna25);
        
        btna50.setSize(Gdx.graphics.getWidth() / 20, Gdx.graphics.getHeight() / 15);
        btna50.setPosition(Gdx.graphics.getWidth() / 2 - 55, Gdx.graphics.getHeight() / 2 + 145);
       
        btna50.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                btna0.setColor(Color.WHITE);
                btna25.setColor(Color.WHITE);
                btna50.setColor(Color.RED);
                btna75.setColor(Color.WHITE);
                btna100.setColor(Color.WHITE);
                game.getMusic().setVolume(0.50f);
                return true;
            }
        });
        stage.addActor(btna50);
        
        btna75.setSize(Gdx.graphics.getWidth() / 20, Gdx.graphics.getHeight() / 15);
        btna75.setPosition(Gdx.graphics.getWidth() / 2 - 10, Gdx.graphics.getHeight() / 2 + 145);
        btna75.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                
               btna0.setColor(Color.WHITE);
                btna25.setColor(Color.WHITE);
                btna50.setColor(Color.WHITE);
                btna75.setColor(Color.RED);
                btna100.setColor(Color.WHITE);
              
                game.getMusic().setVolume(0.75f);
                return true;
            }
        });
        stage.addActor(btna75);
        
        btna100.setSize(Gdx.graphics.getWidth() / 20, Gdx.graphics.getHeight() / 15);
        btna100.setPosition(Gdx.graphics.getWidth() / 2 + 35, Gdx.graphics.getHeight() / 2 + 145);
        btna100.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                btna0.setColor(Color.WHITE);
                btna25.setColor(Color.WHITE);
                btna50.setColor(Color.WHITE);
                btna75.setColor(Color.WHITE); 
                btna100.setColor(Color.RED);
                game.getMusic().setVolume(1.0f);
                return true;
            }
        });
        stage.addActor(btna100);
        
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 1, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int arg0, int arg1) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();
    }

}
