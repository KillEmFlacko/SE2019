
package com.gdx.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
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
import com.gdx.game.score.HighScoreEntry;
import com.gdx.game.score.HighScoreTable;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;


public class ScoreScreen implements Screen{
    
    private final GdxGame game;
    private final Stage stage;
    private final ArrayList<Label> labelArray;
    private Label labelTitle;
    private final int padding = 15;
    private final HighScoreTable hst;
    private float colWidth;
    private float rowHeight;
    private Sound sound;

    
    public ScoreScreen(GdxGame game) throws FileNotFoundException, IOException {
        this.hst = new HighScoreTable();
        this.game = game;
        this.stage = new Stage();    
        this.labelArray = new ArrayList();
        initUI();
    }
    
// a family of related product objects is designed to be used together, and you need to enforce this constraint.   
    
    private void initUI() throws IOException {
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

        labelTitle = new Label("Score:", lblStyle);
        labelTitle.setSize(Gdx.graphics.getWidth(), 30);
        labelTitle.setAlignment(Align.center);
        labelTitle.setPosition(0, Gdx.graphics.getHeight()/2 + 300);
        stage.addActor(labelTitle);

        colWidth = Gdx.graphics.getWidth() / 5f;
        rowHeight = Gdx.graphics.getHeight() / 15f;
        TextButton btnButton = new TextButton("Back", GdxGame.game.skin, "default");
        btnButton.setSize(colWidth, rowHeight);
        btnButton.setPosition(padding, padding);
        btnButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                ScoreScreen.this.dispose();
                game.setScreen(new TitleScreen(game));
                return true;
            }
        });
        stage.addActor(btnButton);
        createLabel(lblStyle, hst);
        
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

    private void createLabel(Label.LabelStyle lblStyle, HighScoreTable hst) {
        int i=0;
        for (HighScoreEntry x: hst) {
            labelArray.add(new Label(x.getNickname() + "..." + x.getScore(), lblStyle));
            labelArray.get(i).setSize(Gdx.graphics.getWidth(), 30);
            labelArray.get(i).setAlignment(Align.center);
            labelArray.get(i).setPosition(0, Gdx.graphics.getHeight() / 2 + 300 - (i+1)*50);
            stage.addActor(labelArray.get(i));
            i++;
        }
    }
    
}
