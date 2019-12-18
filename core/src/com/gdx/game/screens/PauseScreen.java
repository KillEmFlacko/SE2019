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
import com.badlogic.gdx.utils.Align;
import com.gdx.game.GdxGame;

/**
 *
 * @author raffaele.barbato1997
 */
public class PauseScreen implements Screen {

    private Stage stage;
    private GdxGame game;
    private GameScreen previousScreen;
    private Label label1;
    private float colWidth;
    private float rowHeight;
    private final int padding = 15;

    public PauseScreen(GdxGame game, GameScreen previousS) {
        this.game = game;
        this.stage = new Stage();
        previousScreen = previousS;
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

        label1 = new Label("Game Paused", lblStyle);
        label1.setSize(Gdx.graphics.getWidth(), 30);
        label1.setAlignment(Align.center);
        label1.setPosition(0, Gdx.graphics.getHeight() / 2 - 15 + 100);
        label1.setVisible(true);
        stage.addActor(label1);

        colWidth = Gdx.graphics.getWidth() / 5f;
        rowHeight = Gdx.graphics.getHeight() / 15f;
        TextButton btnButton = new TextButton("RESUME", GdxGame.game.skin, "default");
        btnButton.setSize(colWidth, rowHeight);
        btnButton.setPosition(Gdx.graphics.getWidth() / 2 - 80, Gdx.graphics.getHeight() / 2 + 20);
        btnButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(previousScreen);
                return true;
            }
        });
        stage.addActor(btnButton);

        TextButton btnButton2 = new TextButton("QUIT", GdxGame.game.skin, "default");
        btnButton2.setSize(colWidth, rowHeight);
        btnButton2.setPosition(Gdx.graphics.getWidth() / 2 - 80, Gdx.graphics.getHeight() / 2 - 80);
        btnButton2.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                PauseScreen.this.dispose();
                Gdx.app.exit();
                return true;
            }
        });
        stage.addActor(btnButton2);
        
        
        TextButton btn3Button = new TextButton("MAIN MENU", GdxGame.game.skin, "default");
        btn3Button.setSize(colWidth, rowHeight);
        btn3Button.setPosition(Gdx.graphics.getWidth() / 2 - 80, Gdx.graphics.getHeight() / 2 - 30);
        btn3Button.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                PauseScreen.this.dispose();
                game.setScreen(new TitleScreen(game));
                return true;
            }
        });
        stage.addActor(btn3Button);

    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(this.stage);
    }

    @Override
    public void render(float f) {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int i, int i1) {
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
