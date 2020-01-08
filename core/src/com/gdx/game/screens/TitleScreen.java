package com.gdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TiledDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Queue;
import com.gdx.game.GdxGame;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author GiovanniB
 */
public class TitleScreen implements Screen {

    private final Stage stage;
    private final GdxGame game;
    private Label label1;
    private final int padding = 15;
    private final int BUTTON_SPACE = 5;
    private SettingsScreen ss;
    private SpriteBatch spriteBatch = new SpriteBatch();
    private TextureRegion textureRegion = new TextureRegion(new Texture(Gdx.files.internal("menu/back.jpg")));
    private Queue<Actor> actQueue = new Queue<>(6);

    public TitleScreen(GdxGame aGame) {
        this.game = aGame;
        this.stage = new Stage(GdxGame.game.vp);
        initUI();

    }

    private void initUI() {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("ARCADE_N.TTF"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameters = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameters.size = 20;
        parameters.borderColor = Color.BLACK;
        parameters.borderWidth = 1;
        parameters.color = Color.ORANGE;
        BitmapFont buttonFont = generator.generateFont(parameters);
        parameters.size = 50;
        parameters.color = Color.RED;
        parameters.borderWidth = 3;
        parameters.shadowOffsetX = 1;
        parameters.shadowColor = Color.BROWN;
        BitmapFont titleFont = generator.generateFont(parameters);
        generator.dispose();
        
        Texture tiles = new Texture(Gdx.files.internal("mappa_text_low_res/rogueliketilesx3.png"));
        TextureRegion[][] splittedTiles = TextureRegion.split(tiles, 25, 25);

        /////////////// BACKGROUND IMAGE /////////////////
        TextureRegion wallTileRegion = splittedTiles[2][1];
        TiledDrawable backgroundDraw = new TiledDrawable(wallTileRegion);
        backgroundDraw.tint(Color.WHITE);
        Image backgroundImage = new Image(backgroundDraw);
        backgroundImage.setSize(stage.getWidth(), stage.getHeight());
        stage.addActor(backgroundImage);
        //////////////////////////////////////////////////

        Label.LabelStyle lblStyle = new Label.LabelStyle();
        lblStyle.font = titleFont;
        label1 = new Label("Doors of\nSacrahan", lblStyle);
        label1.setSize(stage.getWidth(), lblStyle.font.getLineHeight()*2);
        label1.setAlignment(Align.center);
        actQueue.addLast(label1);

        TiledDrawable wallBrightDraw = new TiledDrawable(splittedTiles[1][2]);
        TiledDrawable wallNormalDraw = new TiledDrawable(splittedTiles[1][1]);
        TiledDrawable wallDarkDraw = new TiledDrawable(splittedTiles[1][3]);
        TextButton.TextButtonStyle txtStyle = new TextButton.TextButtonStyle(null, null, null, buttonFont);
        txtStyle.up = wallBrightDraw;
        txtStyle.over = wallNormalDraw;
        TextButton btnButton = new TextButton("Play!", txtStyle);
        btnButton.setSize(stage.getWidth() / 5, stage.getHeight() / 15);
        btnButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                TitleScreen.this.dispose();
                game.setScreen(new PlayerSelectionScreen(game));
                return true;
            }
        });
        actQueue.addLast(btnButton);

        TextButton btnButton2 = new TextButton("Score", txtStyle);
        btnButton2.setSize(stage.getWidth() / 5, stage.getHeight() / 15);
        btnButton2.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                try {
                    game.setScreen(new ScoreScreen(game, TitleScreen.this));
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(TitleScreen.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(TitleScreen.class.getName()).log(Level.SEVERE, null, ex);
                }
                return true;
            }
        });
        actQueue.addLast(btnButton2);

        TextButton btnSetting = new TextButton("Options", txtStyle);
        btnSetting.setSize(stage.getWidth() / 5, stage.getHeight() / 15);
        btnSetting.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) { 
                game.setScreen(new SettingsScreen(game, TitleScreen.this));
                return true;
            }
        });
        actQueue.addLast(btnSetting);

        
        TextButton guideButton = new TextButton("Guide", txtStyle);
        guideButton.setSize(stage.getWidth() / 5, stage.getHeight() / 15);
        guideButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new GuideScreen(game,TitleScreen.this));
                return true;
            }
        });
        actQueue.addLast(guideButton);

        TextButton quitButton = new TextButton("Quit", txtStyle);
        quitButton.setSize(stage.getWidth() / 5, stage.getHeight() / 15);
        quitButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                TitleScreen.this.dispose();
                Gdx.app.exit();
                return true;
            }
        });
        actQueue.addLast(quitButton);
        
        int acc = (int) stage.getHeight()/7;
        for(Actor act : actQueue){
            acc += (act.getHeight() + padding);
            act.setPosition(stage.getWidth()/2 - act.getWidth()/2, stage.getHeight() - acc);
            stage.addActor(act);
        }
        
        game.setMusic("audio/menu/toccataefuga.mp3", true);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float f) {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        /*
        stage.getBatch().begin();
        stage.getBatch().draw(textureRegion, stage.getWidth(), stage.getHeight());
        stage.getBatch().end();
         */

        stage.getViewport().apply();
        stage.draw();
    }

    @Override
    public void dispose() {
        stage.dispose();
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
}
