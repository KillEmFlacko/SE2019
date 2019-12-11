package com.gdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.gdx.game.GameStage;
import com.gdx.game.GdxGame;
import com.gdx.game.entities.MapLimits;
import com.gdx.game.entities.Player;
import com.gdx.game.entities.bosses.DemoBoss;
import com.gdx.game.contact_listeners.BulletDamageContactListener;
import com.gdx.game.contact_listeners.EndDemoGameListener;
import com.gdx.game.contact_listeners.IncreaseScoreListener;
import com.gdx.game.movements.MovementSetFactory;
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

    private final TiledMap map;
    private final OrthogonalTiledMapRenderer mapRenderer;
    private final Box2DDebugRenderer debugRenderer;
    private final Player player;
    private final World world;
    private final GdxGame game;
    private final GameStage stage;
    public Label label1;

    private final ScoreCounter scoreCounter;

    public GameScreen(GdxGame aGame) {
        this.game = aGame;
        stage = new GameStage();
        stage.setViewport(aGame.vp);
        world = new World(Vector2.Zero, true);
        stage.setWorld(world);
        world.setContactListener(new ContactMultiplexer(new BulletDamageContactListener()));
        ////////// MAPPA /////////////
        map = new TmxMapLoader().load("mappa_text_low_res/mappa_low_res.tmx");
        float pixelsPerUnit = 25f;
        float unitPerMeters = 16f / 30f; // 16 tiles in uno schermo di larghezza 30 metri
        mapRenderer = new OrthogonalTiledMapRenderer(map, 1 / (pixelsPerUnit * unitPerMeters));
        //////////////////////////////
        debugRenderer = new Box2DDebugRenderer();
        float playerWorldWidth = 16 / GdxGame.SCALE;
        float playerWorldHeight = 28 / GdxGame.SCALE;
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
        //(14.623319,19.27667)  (15, 15 * (h / w))

        player = new Player("uajono", 100, playerWorldWidth, playerWorldHeight, new Vector2(15, 15 * (h / w)));
        // Constructs a new OrthographicCamera, using the given viewport width and height
        // Height is multiplied by aspect ratio.
        player.addListener(new EndDemoGameListener(this));

        OrthographicCamera cam = (OrthographicCamera) stage.getCamera();
        game.vp.setWorldSize(30, 30 * (h / w)); // 30 * aspectRatio
        cam.position.set(player.getPosition(), stage.getCamera().position.z);
        cam.update();
        
        MovementSetFactory mvsf = MovementSetFactory.instanceOf();
        Vector2 v = player.getPosition().add(5, 5);
        DemoBoss db = new DemoBoss("Wandering Demon", 150,  32 / GdxGame.SCALE, 36 / GdxGame.SCALE, v, mvsf.build("Slow", "Square", false, v, 3), player);
        db.addListener(new EndDemoGameListener(this));
        
        // Gestione dello score IncreaseScoreListener
        scoreCounter = new ScoreCounter();
        IncreaseScoreListener scoreListener = new IncreaseScoreListener(scoreCounter);
        stage.getRoot().addListener(scoreListener);
        
        
        stage.addActor(player);
        stage.addActor(db);

        System.out.println(Gdx.graphics.getWidth());

//        MapLimits left = new MapLimits(
//                world,
//                2 / unitPerMeters,
//                30 * (h / w),
//                new Vector2(0, 15 * (h / w))
//        );
//
//        MapLimits right = new MapLimits(
//                world,
//                2 / unitPerMeters,
//                30 * (h / w),
//                new Vector2(30, 15 * (h / w))
//        );
//
//        MapLimits up = new MapLimits(
//                world,
//                30,
//                2 / unitPerMeters,
//                new Vector2(15, 30 * (h / w))
//        );
//
//        MapLimits down = new MapLimits(
//                world,
//                30,
//                2 / unitPerMeters,
//                new Vector2(15, 0)
//        );
        initLabel();
//        stage.addActor(left);
//        stage.addActor(right);
//        stage.addActor(up);
//        stage.addActor(down);

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
        //label1.setSize((0.9f*game.vp.getWorldWidth()), 0.2f);
        label1.setPosition(0.08f * game.vp.getWorldWidth(), 0.2f * game.vp.getWorldHeight());
        label1.setVisible(false);

        stage.addActor(label1);

    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float f) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        world.step(1 / 60f, 6, 2);
        ////////////////REMOVING BODIES//////////////
        for (Body b : GdxGame.game.bodyToRemove) {
            world.destroyBody(b);
        }
        GdxGame.game.bodyToRemove.removeAll(GdxGame.game.bodyToRemove);
        /////////////////////////////////////////////
        stage.act();
        mapRenderer.setView((OrthographicCamera) stage.getCamera());
//        decommentare per seguire il player
        //stage.getCamera().position.set(player.getPosition(), stage.getCamera().position.z);
        mapRenderer.render();
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
                                    System.out.println("questo è lo score prima dell'inserimento"+GameScreen.this.scoreCounter.getScore());
                                    hst.insertHighScore(text,scoreCounter.getScore());
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
