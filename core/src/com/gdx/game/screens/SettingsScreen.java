package com.gdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.gdx.game.GdxGame;

/**
 *
 * @author RaffaeleB
 */
public class SettingsScreen implements Screen {

    private Stage stage;
    private GdxGame game;
    private Label label1;
    private float colWidth;
    private float rowHeight;
    private final int padding = 15;
    private Label audio;
    private TitleScreen previousScreen;
    //private TextureRegion textureRegion = new TextureRegion(new Texture(Gdx.files.internal("menu/back.jpg")));

    public SettingsScreen(GdxGame game, TitleScreen previousS) {
        this.game = game;
        this.stage = new Stage(game.vp);
        previousScreen = previousS;
        //ts = new TitleScreen(game);//commento
        initUI();

    }

    private void initUI() {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("ARCADE_N.TTF"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameters = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameters.size = 30;
        parameters.color = Color.RED;
        parameters.borderWidth = 1;
        parameters.borderColor = Color.BLACK;
        BitmapFont font = generator.generateFont(parameters);
        generator.dispose();

        Label.LabelStyle lblStyle = new Label.LabelStyle();
        lblStyle.font = font;
        /*
        Image image = new Image(textureRegion.getTexture());
        image.setSize(stage.getWidth(), stage.getWidth());
        image.setPosition(0,0);
        stage.addActor(image);
         */
        label1 = new Label("SETTINGS", lblStyle);
        label1.setSize(stage.getWidth(), 30);
        label1.setAlignment(Align.center);
        label1.setPosition(0, stage.getWidth() / 2 - 15 + 250);
        stage.addActor(label1);

        colWidth = stage.getWidth() / 5f;
        rowHeight = stage.getWidth() / 15f;
        TextButton backButton = new TextButton("Back", GdxGame.game.skin, "default");
        backButton.setSize(colWidth, rowHeight);
        backButton.setPosition(padding, padding);
        backButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                //   SettingsScreen.this.dispose();
                SettingsScreen.this.dispose();
                game.setScreen(previousScreen);
                return true;
            }
        });
        stage.addActor(backButton);

        audio = new Label("AUDIO", lblStyle);
        audio.setSize(stage.getWidth(), 30);
        audio.setAlignment(Align.center);
        audio.setPosition(stage.getWidth() / 2 - 15 - 675, stage.getWidth() / 2 + 150);
        stage.addActor(audio);

        Skin skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
        final Slider volumeSlider = new Slider(0.0f, 1.0f, 0.1f, false, skin);

        volumeSlider.setValue(game.settings.getVolume());
        volumeSlider.setPosition(stage.getWidth() / 2 - 140, stage.getWidth() / 2 + 155);

        stage.addActor(volumeSlider);

        volumeSlider.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                game.settings.setVolume(volumeSlider.getValue());
                game.getMusic().setVolume(game.settings.getVolume());

                //volumeSlider.setValue(volumeSlider.getValue());
                System.out.println("il volume è VolumeSetValue: " + volumeSlider.getValue());
            }
        });

        TextButton defaultButton = new TextButton("Default Settings", GdxGame.game.skin, "default");
        defaultButton.setSize(colWidth, rowHeight);
        defaultButton.setPosition(padding + 600, padding);

        stage.addActor(defaultButton);

        defaultButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                game.settings.setDefault();
                game.getMusic().setVolume(game.settings.getVolume());
                volumeSlider.setValue(game.settings.getVolume());

                return true;
            }
        });
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 0);
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

}
