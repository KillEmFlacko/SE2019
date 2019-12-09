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
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ciccio
 */
public class TitleScreen implements Screen {

    private final Stage stage;
    private final GdxGame game;
    private Label label1;
    private final int padding = 15;
    private final int BUTTON_SPACE = 5;

    public TitleScreen(GdxGame aGame) {
        this.game = aGame;
        this.stage = new Stage(aGame.vp);
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

        label1 = new Label("Welcome to Tramutola", lblStyle);
        label1.setSize(Gdx.graphics.getWidth(), 30);
        label1.setAlignment(Align.center);
        label1.setPosition(0, Gdx.graphics.getHeight() / 2 - 15 + 30);
        stage.addActor(label1);

        TextButton btnButton = new TextButton("Play!", game.skin, "default");
        btnButton.setSize(Gdx.graphics.getWidth() / 5, Gdx.graphics.getHeight() / 15);
        btnButton.setPosition(Gdx.graphics.getWidth() / 2 - btnButton.getWidth() / 2, Gdx.graphics.getHeight() / 2 - btnButton.getHeight() / 2 - padding);
        btnButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                TitleScreen.this.dispose();
                game.setScreen(new GameScreen(game));
                return true;
            }
        });
        stage.addActor(btnButton);
 
        TextButton btnButton2 = new TextButton("Score", GdxGame.game.skin, "default");
        btnButton2.setSize(Gdx.graphics.getWidth() / 5, Gdx.graphics.getHeight() / 15);
        btnButton2.setPosition(Gdx.graphics.getWidth() / 2 - btnButton.getWidth() / 2, Gdx.graphics.getHeight() / 2 - btnButton.getHeight() / 2 - padding*BUTTON_SPACE);
        btnButton2.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                TitleScreen.this.dispose();
                try {
                    game.setScreen(new ScoreScreen(game));
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(TitleScreen.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(TitleScreen.class.getName()).log(Level.SEVERE, null, ex);
                }
                return true;
            }
        });
        stage.addActor(btnButton2);
    }

    private void initPhy() {

    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float f) {
        Gdx.gl.glClearColor(0, 1, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
    }

    @Override
    public void dispose() {
        stage.dispose();
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
}
