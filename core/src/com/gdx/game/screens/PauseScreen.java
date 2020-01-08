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
import com.badlogic.gdx.utils.Align;
import com.gdx.game.GdxGame;

/**
 *
 * @author Giovanni
 */
public class PauseScreen implements Screen {

    private Stage stage;
    private GdxGame game;
    private GameScreen previousScreen;
    private Label label1;
    private float colWidth;
    private float rowHeight;
    private TextureRegion textureRegion = new TextureRegion(new Texture(Gdx.files.internal("pause/sfondo.JPG")));

    public PauseScreen(GdxGame game, GameScreen previousScreen) {
        this.game = game;
        this.stage = new Stage(game.vp);
        this.previousScreen = previousScreen;
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

        Image image = new Image(textureRegion.getTexture());
        image.setSize(stage.getWidth(), stage.getHeight());
        image.setPosition(0, 0);
        image.setColor(1, 1, 1, 0.5f);
        stage.addActor(image);

        label1 = new Label("Game Paused", lblStyle);
        label1.setSize(stage.getWidth(), 30);
        label1.setAlignment(Align.center);
        label1.setPosition(0, stage.getHeight() / 2 - 15 + 100);
        label1.setVisible(true);
        stage.addActor(label1);

        colWidth = stage.getWidth() / 5f;
        rowHeight = stage.getHeight() / 15f;
        TextButton btnButton = new TextButton("RESUME", GdxGame.game.skin, "default");
        btnButton.setSize(colWidth, rowHeight);
        btnButton.setPosition(stage.getWidth() / 2 - 80, stage.getHeight() / 2 + 20);
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
        btnButton2.setPosition(stage.getWidth() / 2 - 80, stage.getHeight() / 2 - 80);
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
        btn3Button.setPosition(stage.getWidth() / 2 - 80, stage.getHeight() / 2 - 30);
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
