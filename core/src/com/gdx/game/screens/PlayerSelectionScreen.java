package com.gdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TiledDrawable;
import com.badlogic.gdx.utils.Align;
import com.gdx.game.GdxGame;
import com.gdx.game.entities.Player;
import com.gdx.game.screens.animated_actors.PlaybleCharacterAnimation;
import com.gdx.game.player_classes.EasternWizard;
import com.gdx.game.screens.animated_actors.NorthernCharacterAnimation;
import com.gdx.game.player_classes.NorthernWizard;
import com.gdx.game.player_classes.SouthernWizard;
import com.gdx.game.player_classes.WesternWizard;
import com.gdx.game.screens.animated_actors.EasternCharacterAnimation;
import com.gdx.game.screens.animated_actors.SouthernCharacterAnimation;
import com.gdx.game.screens.animated_actors.WesternCharacterAnimation;

/**
 *
 * @author Ciccio
 */
public class PlayerSelectionScreen implements Screen {

    private final Stage stage;
    private final GdxGame game;
    private final Box2DDebugRenderer debugRenderer;
    private Label labelTitle, labelName, labelStats;
    private final int padding = 15;
    private final int BUTTON_SPACE = 5;
    private float colWidth;
    private float rowHeight;
    //private TextureRegion textureRegion = new TextureRegion(new Texture(Gdx.files.internal("menu/back.jpg")));
    private TextureRegion textureRegion = new TextureRegion(new Texture(Gdx.files.internal("back.png")));

    private Player player, player2, player3, player4;

    public Animation<TextureRegion> idleAnimation;
    public TextureAtlas atlas;
    public PlaybleCharacterAnimation nca, sca, wca, eca;

    public PlayerSelectionScreen(GdxGame aGame) {
        this.game = aGame;
        this.stage = new Stage(aGame.vp);
        debugRenderer = new Box2DDebugRenderer();
        atlas = new TextureAtlas(Gdx.files.internal("texture/player/wizzard/Nwizzard.atlas"));
        idleAnimation = new Animation(0.2f, atlas.findRegions("m_idle"), Animation.PlayMode.LOOP);
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

        /////////////// BACKGROUND IMAGE /////////////////
        Texture wallTileTexture = new Texture(Gdx.files.internal("mappa_text_low_res/background_tile.png"));
        wallTileTexture.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
        TextureRegion wallTileRegion = new TextureRegion(wallTileTexture);
        wallTileRegion.setRegion(0, 0, wallTileTexture.getWidth() * 8, wallTileTexture.getHeight() * 6);
        Image backgroundImage = new Image(wallTileRegion);
        backgroundImage.setSize(stage.getWidth(), stage.getHeight());
        stage.addActor(backgroundImage);
        //////////////////////////////////////////////////

        labelTitle = new Label("Select your character", lblStyle);
        labelTitle.setColor(Color.RED);
        labelTitle.setSize(stage.getWidth(), 30);
        labelTitle.setAlignment(Align.center);
        labelTitle.setPosition(0, stage.getHeight() - labelTitle.getHeight() - padding);
        stage.addActor(labelTitle);

        colWidth = stage.getWidth() / 5f;
        rowHeight = stage.getHeight() / 15f;
        TextButton btnButton5 = new TextButton("Back", GdxGame.game.txtBtnStyle);
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
        float playerWorldHeight = 150;
        float playerWorldWidth = playerWorldHeight / 28f * 16f;
        float w = stage.getWidth();
        float h = stage.getHeight();

        nca = new NorthernCharacterAnimation();
        stage.addActor(nca);
        nca.setSize(playerWorldWidth, playerWorldHeight);
        nca.setPosition(stage.getWidth() / 2 - nca.getWidth() / 2, labelTitle.getY() - nca.getHeight());
        //prova.setBounds(player4.getPosition().x - player4.getWidth() / 2, player4.getPosition().y - player4.getHeight() / 2, player.getWidth(), player.getHeight());
        nca.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                PlayerSelectionScreen.this.dispose();
                game.setScreen(new GameScreen(game, new NorthernWizard()));
                return true;
            }

            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                labelName = new Label("Northern Wizard", lblStyle);
                labelName.setPosition(stage.getWidth() / 2 - labelName.getWidth() / 2, stage.getHeight() / 2 + labelName.getHeight() * 2);
                stage.addActor(labelName);
                labelStats = new Label("Speed UP - Life DOWN", lblStyle);
                labelStats.setFontScale(0.8f);
                labelStats.setSize(labelStats.getWidth() * 0.8f, labelStats.getHeight() * 0.8f);
                labelStats.setPosition(stage.getWidth() / 2 - labelStats.getWidth() / 2, stage.getHeight() / 2 - labelStats.getHeight() * 2);
                stage.addActor(labelStats);
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                labelName.setText("");
                labelStats.setText("");
            }
        });

        sca = new SouthernCharacterAnimation();
        stage.addActor(sca);
        sca.setSize(playerWorldWidth, playerWorldHeight);
        sca.setPosition(stage.getWidth() / 2 - sca.getWidth() / 2, btnButton5.getTop() + padding);
        //prova.setBounds(player4.getPosition().x - player4.getWidth() / 2, player4.getPosition().y - player4.getHeight() / 2, player.getWidth(), player.getHeight());
        sca.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                PlayerSelectionScreen.this.dispose();
                game.setScreen(new GameScreen(game, new SouthernWizard()));
                return true;
            }

            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                labelName = new Label("Southern Wizard", lblStyle);
                labelName.setPosition(stage.getWidth() / 2 - labelName.getWidth() / 2, stage.getHeight() / 2 + labelName.getHeight() * 2);
                stage.addActor(labelName);
                labelStats = new Label("Life UP - Speed DOWN", lblStyle);
                labelStats.setFontScale(0.8f);
                labelStats.setSize(labelStats.getWidth() * 0.8f, labelStats.getHeight() * 0.8f);
                labelStats.setPosition(stage.getWidth() / 2 - labelStats.getWidth() / 2, stage.getHeight() / 2 - labelStats.getHeight() * 2);
                stage.addActor(labelStats);
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                labelName.setText("");
                labelStats.setText("");
            }
        });

        wca = new WesternCharacterAnimation();
        stage.addActor(wca);
        wca.setSize(playerWorldWidth, playerWorldHeight);
        wca.setPosition(padding, labelTitle.getY() / 2);
        //prova.setBounds(player4.getPosition().x - player4.getWidth() / 2, player4.getPosition().y - player4.getHeight() / 2, player.getWidth(), player.getHeight());
        wca.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                PlayerSelectionScreen.this.dispose();
                game.setScreen(new GameScreen(game, new WesternWizard()));
                return true;
            }

            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                labelName = new Label("Western Wizard", lblStyle);
                labelName.setPosition(stage.getWidth() / 2 - labelName.getWidth() / 2, stage.getHeight() / 2 + labelName.getHeight() * 2);
                stage.addActor(labelName);
                labelStats = new Label("Attack speed UP - Strenght DOWN", lblStyle);
                labelStats.setFontScale(0.8f);
                labelStats.setSize(labelStats.getWidth() * 0.8f, labelStats.getHeight() * 0.8f);
                labelStats.setPosition(stage.getWidth() / 2 - labelStats.getWidth() / 2, stage.getHeight() / 2 - labelStats.getHeight() * 2);
                stage.addActor(labelStats);
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                labelName.setText("");
                labelStats.setText("");
            }
        });

        eca = new EasternCharacterAnimation();
        stage.addActor(eca);
        eca.setSize(playerWorldWidth, playerWorldHeight);
        eca.setPosition(stage.getWidth() - eca.getWidth() - padding, labelTitle.getY() / 2);
        //prova.setBounds(player4.getPosition().x - player4.getWidth() / 2, player4.getPosition().y - player4.getHeight() / 2, player.getWidth(), player.getHeight());
        eca.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                PlayerSelectionScreen.this.dispose();
                game.setScreen(new GameScreen(game, new EasternWizard()));
                return true;
            }

            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                labelName = new Label("Eastern Wizard", lblStyle);
                labelName.setPosition(stage.getWidth() / 2 - labelName.getWidth() / 2, stage.getHeight() / 2 + labelName.getHeight() * 2);
                stage.addActor(labelName);
                labelStats = new Label("Strenght UP - Bullet Speed DOWN", lblStyle);
                labelStats.setFontScale(0.8f);
                labelStats.setSize(labelStats.getWidth() * 0.8f, labelStats.getHeight() * 0.8f);
                labelStats.setPosition(stage.getWidth() / 2 - labelStats.getWidth() / 2, stage.getHeight() / 2 - labelStats.getHeight() * 2);
                stage.addActor(labelStats);
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                labelName.setText("");
                labelStats.setText("");
            }
        });
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
