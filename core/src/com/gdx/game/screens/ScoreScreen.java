package com.gdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TiledDrawable;
import com.badlogic.gdx.utils.Align;
import com.gdx.game.GdxGame;
import com.gdx.game.score.HighScoreEntry;
import com.gdx.game.score.HighScoreTable;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class ScoreScreen implements Screen {

    private final GdxGame game;
    private final Stage stage;
    private final ArrayList<Label> labelArray;
    private Label labelTitle;
    private final int padding = 15;
    private final HighScoreTable hst;
    private float colWidth;
    private float rowHeight;
    private Screen previousScreen;

    public ScoreScreen(GdxGame game, Screen previousScreen) throws IOException {
        this.hst = new HighScoreTable();
        this.game = game;
        this.stage = new Stage(GdxGame.game.vp);
        this.labelArray = new ArrayList();
        this.previousScreen = previousScreen;
        if (previousScreen == null) {
            game.getMusic().stop();
        }
        initUI();
    }

    public ScoreScreen(GdxGame game) throws IOException {
        this(game, null);
    }

// a family of related product objects is designed to be used together, and you need to enforce this constraint.   
    private void initUI() throws IOException {
        /////////////// BACKGROUND IMAGE /////////////////
        Texture wallTileTexture = new Texture(Gdx.files.internal("mappa_text_low_res/background_tile.png"));
        wallTileTexture.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
        TextureRegion wallTileRegion = new TextureRegion(wallTileTexture);
        wallTileRegion.setRegion(0, 0, wallTileTexture.getWidth() * 8, wallTileTexture.getHeight() * 6);
        Image backgroundImage = new Image(wallTileRegion);
        backgroundImage.setSize(stage.getWidth(), stage.getHeight());
        stage.addActor(backgroundImage);
        //////////////////////////////////////////////////

        Label.LabelStyle lblTitleStyle = new Label.LabelStyle();
        lblTitleStyle.font = GdxGame.game.titleFont;

        Label.LabelStyle lblStyle = new Label.LabelStyle();
        lblStyle.font = GdxGame.game.buttonFont;

        labelTitle = new Label("Score", lblTitleStyle);
        labelTitle.setSize(stage.getWidth(), lblTitleStyle.font.getLineHeight());
        labelTitle.setAlignment(Align.center);
        labelArray.add(labelTitle);

        colWidth = stage.getWidth() / 5f;
        rowHeight = stage.getHeight() / 15f;
        TextButton backButton = new TextButton("Back", GdxGame.game.txtBtnStyle);
        backButton.setSize(colWidth, rowHeight);
        backButton.setPosition(padding, padding);
        backButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                ScoreScreen.this.dispose();
                if (previousScreen == null) {
                    game.setScreen(new TitleScreen(game));
                } else {
                    game.setScreen(previousScreen);
                }
                return true;
            }
        });
        stage.addActor(backButton);
        createLabel(lblStyle, hst);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float f) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
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

    private void createLabel(Label.LabelStyle lblStyle, HighScoreTable hst) {
        int i = 0;
        int acc = padding;
        for (HighScoreEntry x : hst) {
            labelArray.add(new Label(x.getNickname() + "..." + x.getScore(), lblStyle));
            labelArray.get(i).setSize(stage.getWidth(), 30);
            labelArray.get(i).setAlignment(Align.center);
            acc += labelArray.get(i).getHeight() + padding;
            labelArray.get(i).setPosition(0, stage.getHeight() - acc);
            stage.addActor(labelArray.get(i));
            i++;
        }
    }

}
