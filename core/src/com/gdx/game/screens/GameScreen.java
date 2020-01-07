package com.gdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import static com.badlogic.gdx.utils.Align.center;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.gdx.game.GameStage;
import com.gdx.game.GdxGame;
import com.gdx.game.entities.Player;
import com.gdx.game.contact_listeners.BulletDamageContactListener;
import com.gdx.game.contact_listeners.IncreaseScoreListener;
import com.gdx.game.contact_listeners.UpdateHUDListener;
import com.gdx.game.entities.classes.CharacterClass;
import com.gdx.game.levels.Level;
import com.gdx.game.levels.Level.EndLevelEvent;
import com.gdx.game.levels.Level1;
import com.gdx.game.levels.Level2;
import com.gdx.game.score.HighScoreTable;
import com.gdx.game.score.ScoreCounter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import net.dermetfan.gdx.physics.box2d.ContactMultiplexer;

/**
 *
 * @author Raffaele & Giovanni
 */
public class GameScreen implements Screen, EventListener {

    private final Box2DDebugRenderer debugRenderer;
    private final Player player;
    private final World world;
    private final GdxGame game;
    private final Stage hudStage;
    private TextField text;
    private TextButton btn;
    private PauseScreen pauseScreen;
    private boolean isPaused = false;
    private final GameStage gameStage;
    private final Array<Level> levels;
    private Button btnButton;
    public Label label1;
    private boolean isEnded = false;
    private final ScoreCounter scoreCounter;
    private ArrayList<Heart> life;

    private Texture texture, blank;
    private Image image1;
    private Image image, image2;

    public GameScreen(GdxGame aGame, CharacterClass characterClass) {
        debugRenderer = new Box2DDebugRenderer();
        this.game = aGame;

        /////////// STAGE /////////////
        gameStage = new GameStage();
        hudStage = new Stage();
        gameStage.setViewport(aGame.vp);
        gameStage.getRoot().addListener(this);
        //////////////////////////////

        ////////// WORLD //////////////
        world = gameStage.getWorld();
        world.setContactListener(new ContactMultiplexer(new BulletDamageContactListener()));
        ///////////////////////////////

        /////////// PLAYER ////////////
        float playerWorldWidth = 16 / GdxGame.SCALE;
        float playerWorldHeight = 28 / GdxGame.SCALE;
        player = new Player("uajono", playerWorldWidth, playerWorldHeight, Vector2.Zero, characterClass);
        //////////////////////////////

        /////////// LEVEL1 //////////
        levels = new Array<>();
        levels.add(new Level1(player), new Level2(player));
        gameStage.addActor(levels.get(0));
        gameStage.addActor(levels.get(1));
        ////////////////////////////

        ////////// SCORE //////////
        scoreCounter = new ScoreCounter();
        IncreaseScoreListener scoreListener = new IncreaseScoreListener(scoreCounter);
        gameStage.addListener(scoreListener);
//        initLabel();
        //////////////////////////

        ///////////SET CAMERA///////////
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
        //(14.623319,19.27667)  (15, 15 * (h / w))

        OrthographicCamera cam = (OrthographicCamera) gameStage.getCamera();
        game.vp.setWorldSize(30, 30 * (h / w)); // 30 * aspectRatio
        cam.position.set(player.getPosition(), gameStage.getCamera().position.z);
        cam.update();
//        ////////////////////////////////
//        level1.addListener(new EndDemoGameListener(this,player,(DemoBoss) level1.getEnemies().get(0)));
//
//        // Gestione dello score IncreaseScoreListener
//        scoreCounter = new ScoreCounter();
//        IncreaseScoreListener scoreListener = new IncreaseScoreListener(scoreCounter);
////        initLabel();
        initHUD();
//        pauseScreen = new PauseScreen(game, this);
//        gameStage.getRoot().addListener(scoreListener);
        pauseScreen = new PauseScreen(game, this);
        

    }

    public void initLabel(Color color) {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("ARCADE_N.TTF"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameters = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameters.size = 24;
        parameters.color = color;
        parameters.borderWidth = 1;
        parameters.borderColor = Color.BLACK;
        BitmapFont font = generator.generateFont(parameters);
        generator.dispose();

        Label.LabelStyle lblStyle = new Label.LabelStyle();
        lblStyle.font = font;
        label1.setStyle(lblStyle);

        if (color.equals(Color.RED)) {
            label1.setText("GAME LOSE");
            label1.setPosition(Gdx.graphics.getWidth() / 2 - 0.9f * label1.getWidth(), (Gdx.graphics.getHeight() / 2 - label1.getHeight() / 2) + 0.1f * Gdx.graphics.getHeight());
        } else if (color.equals(Color.GREEN)) {
            //label1.setText("VICTORY");
            label1.setPosition(Gdx.graphics.getWidth() / 2 - 0.7f * label1.getWidth(), (Gdx.graphics.getHeight() / 2 - label1.getHeight() / 2) + 0.1f * Gdx.graphics.getHeight());
        }
        label1.setVisible(true);
    }

    public final void initHUD() {
        initHUD2();
        //game over label
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("ARCADE_N.TTF"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameters = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameters.size = 24;
        parameters.color = Color.RED;
        parameters.borderWidth = 1;
        parameters.borderColor = Color.BLACK;
        BitmapFont font = generator.generateFont(parameters);
        generator.dispose();

        Label.LabelStyle lblStyle = new Label.LabelStyle();
        lblStyle.font = font;
        label1 = new Label("Prova", lblStyle);

        label1.setSize(label1.getWidth() * 3, label1.getHeight() * 3);
        label1.setFontScale(3);
        label1.setPosition(Gdx.graphics.getWidth() / 2 - label1.getWidth() / 2, (Gdx.graphics.getHeight() / 2 - label1.getHeight() / 2) + 0.1f * Gdx.graphics.getHeight());

        label1.setVisible(false);

        hudStage.addActor(label1);
        //text field
        text = new TextField("", GdxGame.game.skin, "default");
        //text.setScale(1f/GdxGame.SCALE);

        //text.setAlignment(center);
        //text.setSize();
        text.setAlignment(center);
        text.setMaxLength(10);
        text.setMessageText("Enter your nickname");
        text.setSize(Gdx.graphics.getWidth() / 2, text.getHeight() * 2);
        text.setPosition(Gdx.graphics.getWidth() / 2 - text.getWidth() / 2, (Gdx.graphics.getHeight() / 2 - text.getHeight() / 2) - 0.1f * Gdx.graphics.getHeight());
        text.setVisible(false);

        hudStage.addActor(text);

        //enter button
        btn = new TextButton("OK", GdxGame.game.skin, "default");
        btn.setSize(Gdx.graphics.getWidth() / 5, Gdx.graphics.getHeight() / 15);
        btn.setPosition(Gdx.graphics.getWidth() / 2 - btn.getWidth() / 2, Gdx.graphics.getHeight() / 2 - 0.3f * Gdx.graphics.getHeight());
//btn.setPosition(Gdx.graphics.getWidth() / 2 - text.getWidth() / 2, Gdx.graphics.getHeight() / 2 - text.getHeight() / 2);
        btn.setVisible(false);
        btn.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                try {

                    game.setScreen(new ScoreScreen(game));

                } catch (FileNotFoundException ex) {
                } catch (IOException ex) {
                }
                return true;
            }
        });
        hudStage.addActor(btn);

    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(gameStage);
        Gdx.input.setInputProcessor(hudStage);
        gameStage.addListener(this);
        levels.get(0).start();
    }

    @Override
    public void render(float f) {

        if (Gdx.input.isKeyPressed(Keys.ESCAPE)) {
            f = 0;
            this.pause();
        }

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        world.step(1 / 60f, 6, 2);
        gameStage.act();
        gameStage.draw();
        hudStage.act();
        hudStage.draw();
        //      decommentare per seguire il player
        gameStage.getCamera().position.set(player.getPosition(), gameStage.getCamera().position.z);
        gameStage.getCamera().update();
        debugRenderer.render(world, gameStage.getCamera().combined);
    }

    public void end(boolean win) {
        if (!win) {
            initLabel(Color.RED);
            //label1.setText("GAME LOSE");
            //label1.setPosition(Gdx.graphics.getWidth() / 2 - 0.9f*label1.getWidth(), (Gdx.graphics.getHeight() / 2 - label1.getHeight() / 2) + 0.1f * Gdx.graphics.getHeight());
        } else {
            initLabel(Color.GREEN);
            label1.setText("VICTORY");
            //label1.setPosition(Gdx.graphics.getWidth() / 2 - 0.7f*label1.getWidth(), (Gdx.graphics.getHeight() / 2 - label1.getHeight() / 2) + 0.1f * Gdx.graphics.getHeight());
        }
        label1.setVisible(true);
        try {
            final HighScoreTable hst = new HighScoreTable();
            if (hst.isInTop(scoreCounter.getScore())) {
                text.setVisible(true);
                btn.setVisible(true);
                btn.addListener(new InputListener() {
                    @Override
                    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                        try {
                            String nick = text.getText().replaceAll("\\s+", "");
                            if (!nick.equals("")) {
                                hst.insertHighScore(nick, scoreCounter.getScore());
                                game.setScreen(new ScoreScreen(game));
                                GameScreen.this.dispose();
                                game.setScreen(new ScoreScreen(game));

                            }
                        } catch (FileNotFoundException ex) {
                        } catch (IOException ex) {
                        }
                        return true;
                    }
                });
            } else {
                Timer.schedule(new Task() {
                    @Override
                    public void run() {
                        try {
                            game.setScreen(new ScoreScreen(game));
                            GameScreen.this.dispose();
                        } catch (IOException ex) {
                            game.setScreen(new TitleScreen(game));
                            GameScreen.this.dispose();
                        }

                    }
                }, 5);

            }
        } catch (IOException ex) {
            game.setScreen(new TitleScreen(game));
        }
//                Timer.schedule(new Task() {
//                    
//
//                    
//                    else {
//                    game.setScreen(new ScoreScreen(game));
//                    GameScreen.this.dispose();
//                }
//                } catch (IOException ex) {
//                        game.setScreen(new TitleScreen(game));
//                        }
//            }
//        } catch (FileNotFoundException ex) {
//            Logger.getLogger(GameScreen.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//     , 
//5);

    }

    @Override
    public void dispose() {
    }

    @Override
    public void resize(int i, int i1) {
    }

    @Override
    public void pause() {
        isPaused = true;
        game.setScreen(pauseScreen);
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }

    @Override
    public boolean handle(Event event) {
        if (event instanceof EndLevelEvent) {
            if (!((EndLevelEvent) event).hasWon()) {
                end(((EndLevelEvent) event).hasWon());
            } else if (event.getTarget() == levels.get(0)) {
                levels.get(1).start();
            } else {
                end(((EndLevelEvent) event).hasWon());
                player.dispose();
            }
        }
        return true;
    }

    private void initHUD2() {
        this.life = new ArrayList();
        for (int i = 0; i < player.getLife(); i++) {
            life.add(new Heart());
        }
        createLifebar();

        blank = new Texture(Gdx.files.internal("texture/enemy/bosses/red.jpg"));

        image2 = new Image(blank);
        image2.setSize(Gdx.graphics.getWidth(), 30);
        image2.setPosition(0, 0);
        image2.setColor(Color.DARK_GRAY);
        hudStage.addActor(image2);

        image = new Image(blank);
        image.setSize(Gdx.graphics.getWidth() - 5, 20);
        image.setPosition(5, 5);
        image.setColor(Color.RED);
        hudStage.addActor(image);

        gameStage.addListener(new UpdateHUDListener(life,image));

    }

    private void createLifebar() {
        int i = 0;
        for (Heart h : life) {
            h.getImage().setPosition(8 + 10 * i + h.getImage().getWidth() / 15 * i, Gdx.graphics.getHeight() - h.getImage().getHeight() / 12);
            h.getImage().setSize(Gdx.graphics.getWidth() / 15, Gdx.graphics.getHeight() / 12);
            hudStage.addActor(h.getImage());
            i++;
        }
    }
}
