package com.gdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.gdx.game.GdxGame;
import com.gdx.game.entities.Player;
import com.gdx.game.entities.classes.EasternWizard;
import com.gdx.game.entities.classes.NorthernWizard;
import com.gdx.game.entities.classes.SouthernWizard;
import com.gdx.game.entities.classes.WesternWizard;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ciccio
 */
public class PlayerSelectionScreen implements Screen {

    private final Stage stage;
    private final GdxGame game;
    private Label labelTitle;
    private final int padding = 15;
    private final int BUTTON_SPACE = 5;
    private float colWidth;
    private float rowHeight;
    
    private Player player, player2, player3, player4;
    private final World world;

    public PlayerSelectionScreen(GdxGame aGame) {
        this.game = aGame;
        this.stage = new Stage(aGame.vp);

        world = new World(Vector2.Zero, true);
        
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

        final Label.LabelStyle lblStyle = new Label.LabelStyle();
        lblStyle.font = font;

        labelTitle = new Label("Choose Your Wizard", lblStyle);
        labelTitle.setSize(Gdx.graphics.getWidth(), 30);
        labelTitle.setAlignment(Align.center);
        labelTitle.setPosition(0, Gdx.graphics.getHeight() - labelTitle.getHeight()*2);
        stage.addActor(labelTitle);
        
        /*
        
        TextButton btnButton = new TextButton("Northern Wizard", game.skin, "default");
        btnButton.setSize(Gdx.graphics.getWidth() / 5, Gdx.graphics.getHeight() / 15);
        btnButton.setPosition(Gdx.graphics.getWidth() / 2 - btnButton.getWidth() / 2, Gdx.graphics.getHeight() / 2 - btnButton.getHeight() / 2 - padding);
        btnButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                PlayerSelectionScreen.this.dispose();
                game.setScreen(new GameScreen(game, new NorthernWizard()));
                return true;
            }
        });
        stage.addActor(btnButton);
 
        TextButton btnButton2 = new TextButton("Southern Wizard", GdxGame.game.skin, "default");
        btnButton2.setSize(Gdx.graphics.getWidth() / 5, Gdx.graphics.getHeight() / 15);
        btnButton2.setPosition(Gdx.graphics.getWidth() / 2 - btnButton.getWidth() / 2, Gdx.graphics.getHeight() / 2 - btnButton.getHeight() / 2 - padding*BUTTON_SPACE);
        btnButton2.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                PlayerSelectionScreen.this.dispose();
                game.setScreen(new GameScreen(game, new SouthernWizard()));
                return true;
            }
        });
        stage.addActor(btnButton2);
        
        TextButton btnButton3 = new TextButton("Eastern Wizard", GdxGame.game.skin, "default");
        btnButton3.setSize(Gdx.graphics.getWidth() / 5, Gdx.graphics.getHeight() / 15);
        btnButton3.setPosition(Gdx.graphics.getWidth() / 2 - btnButton.getWidth() / 2, Gdx.graphics.getHeight() / 2 - btnButton.getHeight() / 2 - padding*(BUTTON_SPACE*2));
        btnButton3.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                PlayerSelectionScreen.this.dispose();
                game.setScreen(new GameScreen(game, new EasternWizard()));
                return true;
            }
        });
        stage.addActor(btnButton3);
        
        TextButton btnButton4 = new TextButton("Western Wizard", GdxGame.game.skin, "default");
        btnButton4.setSize(Gdx.graphics.getWidth() / 5, Gdx.graphics.getHeight() / 15);
        btnButton4.setPosition(Gdx.graphics.getWidth() / 2 - btnButton.getWidth() / 2, Gdx.graphics.getHeight() / 2 - btnButton.getHeight() / 2 - padding*(BUTTON_SPACE*3));
        btnButton4.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                PlayerSelectionScreen.this.dispose();
                game.setScreen(new GameScreen(game, new WesternWizard()));
                return true;
            }
        });
        stage.addActor(btnButton4);

        */
        
        colWidth = Gdx.graphics.getWidth() / 5f;
        rowHeight = Gdx.graphics.getHeight() / 15f;
        TextButton btnButton5 = new TextButton("Back", GdxGame.game.skin, "default");
        btnButton5.setSize(colWidth, rowHeight);
        btnButton5.setPosition(padding, padding);
        btnButton5.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                PlayerSelectionScreen.this.dispose();
                game.setScreen(new TitleScreen(game));
                return true;
            }
        });
        stage.addActor(btnButton5);
        
        //image
        
        float playerWorldWidth = 16 / GdxGame.SCALE * 20;
        float playerWorldHeight = 28 / GdxGame.SCALE * 20;
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
        //(14.623319,19.27667)  (15, 15 * (h / w))
        
        game.vp.setWorldSize(800, 800 * (h / w)); // 30 * aspectRatio

        
        player = new Player("uajono", world, playerWorldWidth, playerWorldHeight, new Vector2(400,500), new NorthernWizard());
        // Constructs a new OrthographicCamera, using the given viewport width and height
        // Height is multiplied by aspect ratio.
        player.setBounds(player.getPosition().x - player.getWorldWidth() / 2, player.getPosition().y - player.getWorldHeight() / 2, player.getWorldWidth(), player.getWorldHeight());
        player.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                PlayerSelectionScreen.this.dispose();
                game.setScreen(new GameScreen(game, new NorthernWizard()));
                return true;
            }
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
            }
            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor fromActor) {
            }
        });
        stage.addActor(player);
        
        player2 = new Player("uajono", world, playerWorldWidth, playerWorldHeight, new Vector2(400,200), new SouthernWizard());
        player2.setBounds(player2.getPosition().x - player2.getWorldWidth() / 2, player2.getPosition().y - player2.getWorldHeight() / 2, player.getWorldWidth(), player.getWorldHeight());
        player2.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                PlayerSelectionScreen.this.dispose();
                game.setScreen(new GameScreen(game, new SouthernWizard()));
                return true;
            }
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
            }
            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor fromActor) {
            }
        });
        stage.addActor(player2);
        
        player3 = new Player("uajono", world, playerWorldWidth, playerWorldHeight, new Vector2(600,350), new EasternWizard());
        player3.setBounds(player3.getPosition().x - player3.getWorldWidth() / 2, player3.getPosition().y - player3.getWorldHeight() / 2, player.getWorldWidth(), player.getWorldHeight());
        player3.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                PlayerSelectionScreen.this.dispose();
                game.setScreen(new GameScreen(game, new EasternWizard()));
                return true;
            }
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
            }
            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor fromActor) {
            }
        });
        stage.addActor(player3);
        
        player4 = new Player("uajono", world, playerWorldWidth, playerWorldHeight, new Vector2(200,350), new WesternWizard());
        player4.setBounds(player4.getPosition().x - player4.getWorldWidth() / 2, player4.getPosition().y - player4.getWorldHeight() / 2, player.getWorldWidth(), player.getWorldHeight());
        player4.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                PlayerSelectionScreen.this.dispose();
                game.setScreen(new GameScreen(game, new WesternWizard()));
                return true;
            }
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
            }
            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor fromActor) {
            }
        });
        stage.addActor(player4);
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
