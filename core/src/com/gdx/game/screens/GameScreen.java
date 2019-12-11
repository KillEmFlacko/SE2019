package com.gdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.gdx.game.GameStage;
import com.gdx.game.GdxGame;
import com.gdx.game.entities.Player;
import com.gdx.game.contact_listeners.BulletDamageContactListener;
import com.gdx.game.contact_listeners.EndDemoGameListener;
import com.gdx.game.contact_listeners.IncreaseScoreListener;
import com.gdx.game.levels.Level1;
import com.gdx.game.score.HighScoreTable;
import com.gdx.game.score.ScoreCounter;
import de.tomgrill.gdxdialogs.core.GDXDialogs;
import de.tomgrill.gdxdialogs.core.dialogs.GDXTextPrompt;
import de.tomgrill.gdxdialogs.core.listener.TextPromptListener;
import java.io.IOException;
import net.dermetfan.gdx.physics.box2d.ContactMultiplexer;

/**
 *
 * @author Armando
 */
public class GameScreen implements Screen {

    private final Box2DDebugRenderer debugRenderer;
    private final Player player;
    private final World world;
    private final GdxGame game;
    private final GameStage stage;
    public Label label1;

    private final ScoreCounter scoreCounter;
    private final Level1 level1;

    public GameScreen(GdxGame aGame) {
        this.game = aGame;
        
        /////////// STAGE /////////////
        stage = new GameStage(aGame.vp);
        stage.addListener(new EndDemoGameListener(this));
        //////////////////////////////

        ////////// WORLD //////////////
        world = stage.getWorld();
        debugRenderer = new Box2DDebugRenderer();
        world.setContactListener(new ContactMultiplexer(new BulletDamageContactListener()));
        ///////////////////////////////
        
        /////////// PLAYER ////////////
        float playerWorldWidth = 16 / GdxGame.SCALE;
        float playerWorldHeight = 28 / GdxGame.SCALE;
        player = new Player("uajono", 100, playerWorldWidth, playerWorldHeight, Vector2.Zero);
        //////////////////////////////
        
        /////////// LEVEL1 //////////
        level1 = new Level1(player);
        stage.addActor(level1);
        ////////////////////////////


        ////////// SCORE //////////
        scoreCounter = new ScoreCounter();
        IncreaseScoreListener scoreListener = new IncreaseScoreListener(scoreCounter);
        stage.addListener(scoreListener);
        initLabel();
        //////////////////////////

        ///////////SET CAMERA///////////
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
        OrthographicCamera cam = (OrthographicCamera) stage.getCamera();
        game.vp.setWorldSize(30, 30 * (h / w)); // 30 * aspectRatio
        cam.position.set(player.getPosition(), stage.getCamera().position.z);
        cam.update();
        ////////////////////////////////
    }

    public final void initLabel() {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("ARCADE_N.TTF"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameters = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameters.size = 16;
        parameters.color = Color.RED;
        parameters.borderWidth = 1;
        parameters.borderColor = Color.BLACK;
        BitmapFont font = generator.generateFont(parameters);
        generator.dispose();

        Label.LabelStyle lblStyle = new Label.LabelStyle();
        lblStyle.font = font;
        float a = 1f / (5 / GdxGame.SCALE), b = 1f / (16 / GdxGame.SCALE);
        label1 = new Label("GAME OVER", lblStyle);
        label1.setFontScale(1f / GdxGame.SCALE);
//        label1.setSize((0.9f*game.vp.getWorldWidth()), 0.2f);
        label1.setPosition(0.08f * game.vp.getWorldWidth(), 0.2f * game.vp.getWorldHeight());
        label1.setVisible(false);

        stage.addActor(label1);
    }

    @Override
    public void show() {
        level1.start();
//        instantiateWalls(level1);
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float f) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        world.step(1 / 60f, 6, 2);
        stage.act();
        stage.draw();
        debugRenderer.render(world, stage.getCamera().combined);
    }

    public void end() {
        label1.setVisible(true);
        Timer.schedule(new Task() {
            @Override
            public void run() {
                try {
                    final HighScoreTable hst = new HighScoreTable();

                    if (hst.isInTop(scoreCounter.getScore())) {

                        GDXDialogs dialogs = game.getDialogMgr();
                        GDXTextPrompt textPrompt = dialogs.newDialog(GDXTextPrompt.class);

                        textPrompt.setTitle("New High Score!");
                        textPrompt.setMessage("Please, insert your name.");

                        textPrompt.setCancelButtonLabel("Cancel");
                        textPrompt.setConfirmButtonLabel("OK");

                        textPrompt.setTextPromptListener(new TextPromptListener() {

                            @Override
                            public void confirm(String text) {
                                try {
                                    System.out.println("questo è lo score prima dell'inserimento" + GameScreen.this.scoreCounter.getScore());
                                    hst.insertHighScore(text, scoreCounter.getScore());
                                    game.setScreen(new ScoreScreen(game));
                                } catch (IOException ex) {
                                    game.setScreen(new TitleScreen(game));
                                } finally {
                                    GameScreen.this.dispose();
                                }
                            }

                            @Override
                            public void cancel() {
                                try {
                                    game.setScreen(new ScoreScreen(game));
                                } catch (IOException ex) {
                                    game.setScreen(new TitleScreen(game));
                                } finally {
                                    GameScreen.this.dispose();
                                }
                            }
                        });

                        textPrompt.build().show();
                    } else {
                        game.setScreen(new ScoreScreen(game));
                        GameScreen.this.dispose();
                    }
                } catch (IOException ex) {
                    game.setScreen(new TitleScreen(game));
                }
            }
        }, 5);
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
