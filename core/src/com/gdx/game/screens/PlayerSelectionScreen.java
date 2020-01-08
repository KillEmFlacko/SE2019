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

        Image image = new Image(textureRegion.getTexture());
        image.setSize(stage.getWidth(), stage.getHeight());
        image.setPosition(0, 0);
        stage.addActor(image);

        labelTitle = new Label("Select your character", lblStyle);
        labelTitle.setColor(Color.RED);
        labelTitle.setSize(stage.getWidth(), 30);
        labelTitle.setAlignment(Align.center);
        labelTitle.setPosition(0, stage.getHeight() - labelTitle.getHeight() * 2);
        stage.addActor(labelTitle);
        /*
        
        TextButton btnButton = new TextButton("Northern Wizard", game.skin, "default");
        btnButton.setSize(stage.getWidth() / 5, stage.getHeight() / 15);
        btnButton.setPosition(stage.getWidth() / 2 - btnButton.getWidth() / 2, stage.getHeight() / 2 - btnButton.getHeight() / 2 - padding);
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
        btnButton2.setSize(stage.getWidth() / 5, stage.getHeight() / 15);
        btnButton2.setPosition(stage.getWidth() / 2 - btnButton.getWidth() / 2, stage.getHeight() / 2 - btnButton.getHeight() / 2 - padding*BUTTON_SPACE);
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
        btnButton3.setSize(stage.getWidth() / 5, stage.getHeight() / 15);
        btnButton3.setPosition(stage.getWidth() / 2 - btnButton.getWidth() / 2, stage.getHeight() / 2 - btnButton.getHeight() / 2 - padding*(BUTTON_SPACE*2));
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
        btnButton4.setSize(stage.getWidth() / 5, stage.getHeight() / 15);
        btnButton4.setPosition(stage.getWidth() / 2 - btnButton.getWidth() / 2, stage.getHeight() / 2 - btnButton.getHeight() / 2 - padding*(BUTTON_SPACE*3));
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
        colWidth = stage.getWidth() / 5f;
        rowHeight = stage.getHeight() / 15f;
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
        float w = stage.getWidth();
        float h = stage.getHeight();
        //(14.623319,19.27667)  (15, 15 * (h / w))
        /*
        
        game.vp.setWorldSize(800, 800 * (h / w)); // 30 * aspectRatio

        
        player = new Player("uajono", world, playerWorldWidth, playerWorldHeight, new Vector2(400,500), new NorthernWizard());
        // Constructs a new OrthographicCamera, using the given viewport width and height
        // Height is multiplied by aspect ratio.
        // player.setBounds(player.getPosition().x - player.getWidth() / 2, player.getPosition().y - player.getHeight() / 2, player.getWidth(), player.getHeight());

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
       // player2.setBounds(player2.getPosition().x - player2.getWidth() / 2, player2.getPosition().y - player2.getHeight() / 2, player.getWidth(), player.getHeight());

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
      //  player3.setBounds(player3.getPosition().x - player3.getWidth() / 2, player3.getPosition().y - player3.getHeight() / 2, player.getWidth(), player.getHeight());

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

       // player4.setBounds(player4.getPosition().x - player4.getWidth() / 2, player4.getPosition().y - player4.getHeight() / 2, player.getWidth(), player.getHeight());
        player4.addListener(new ClickListener() {

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

         */

        nca = new NorthernCharacterAnimation();
        stage.addActor(nca);
        nca.setPosition(stage.getWidth() / 2 - 25, stage.getHeight() / 2 + 150);
        nca.setSize(playerWorldWidth, playerWorldHeight);
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
        sca.setPosition(stage.getWidth() / 2 - 25, stage.getHeight() / 2 - 150);
        sca.setSize(playerWorldWidth, playerWorldHeight);
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
        wca.setPosition(stage.getWidth() / 2 - 275, stage.getHeight() / 2);
        wca.setSize(playerWorldWidth, playerWorldHeight);
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
        eca.setPosition(stage.getWidth() / 2 + 235, stage.getHeight() / 2);
        eca.setSize(playerWorldWidth, playerWorldHeight);
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
