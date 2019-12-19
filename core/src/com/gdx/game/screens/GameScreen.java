package com.gdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.loaders.AssetLoader;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.Align;
import static com.badlogic.gdx.utils.Align.center;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.gdx.game.GdxGame;
import com.gdx.game.entities.MapLimits;
import com.gdx.game.entities.Player;
import com.gdx.game.entities.bosses.DemoBoss;
import com.gdx.game.contact_listeners.BulletDamageContactListener;
import com.gdx.game.contact_listeners.EndDemoGameListener;
import com.gdx.game.contact_listeners.IncreaseScoreListener;
import com.gdx.game.contact_listeners.UpdateHUDListener;
import com.gdx.game.entities.classes.CharacterClass;
import com.gdx.game.entities.classes.NorthernWizard;
import com.gdx.game.movements.MovementSetFactory;
import com.gdx.game.score.HighScoreTable;
import com.gdx.game.score.ScoreCounter;
import com.gdx.game.screens.assets.Heart;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    private final DemoBoss db;
    private final World world;
    private final GdxGame game;
    private final Stage gameStage;
    private final Stage hudStage;
    private Label label1;
    private TextField text;
    private TextButton btn;
    
    private ArrayList<Heart> life;
    private ProgressBar bossLife;
    
    private Texture texture;
    private Image image1;
    

    private final ScoreCounter scoreCounter;
    
    
    public GameScreen(GdxGame aGame, CharacterClass characterClass) {
        this.game = aGame;
        gameStage = new Stage(aGame.vp);
        hudStage = new Stage();
        world = new World(Vector2.Zero, true);
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

        player = new Player("uajono", world, playerWorldWidth, playerWorldHeight, new Vector2(15,15*(h/w)), characterClass);

        // Constructs a new OrthographicCamera, using the given viewport width and height
        // Height is multiplied by aspect ratio.
        player.addListener(new EndDemoGameListener(this));

        OrthographicCamera cam = (OrthographicCamera) gameStage.getCamera();
        game.vp.setWorldSize(30, 30 * (h / w)); // 30 * aspectRatio
        cam.position.set(player.getPosition(), gameStage.getCamera().position.z);
        cam.update();

        MovementSetFactory mvsf = MovementSetFactory.instanceOf();
        Vector2 v = player.getPosition().add(5, 5);
        db = new DemoBoss("Wandering Demon", 150, this.world, 32 / GdxGame.SCALE, 36 / GdxGame.SCALE, v, mvsf.build("Slow", "Square", false, v, 3), player);
        db.addListener(new EndDemoGameListener(this));
        // Gestione dello score IncreaseScoreListener
        scoreCounter = new ScoreCounter();
        IncreaseScoreListener scoreListener = new IncreaseScoreListener(scoreCounter);
        db.addListener(scoreListener);
        player.addListener(scoreListener);

        gameStage.addActor(player);
        gameStage.addActor(db);

        System.out.println(Gdx.graphics.getWidth());

        MapLimits left = new MapLimits(
                world,
                2 / unitPerMeters,
                30 * (h / w),
                new Vector2(0, 15 * (h / w))
        );

        MapLimits right = new MapLimits(
                world,
                2 / unitPerMeters,
                30 * (h / w),
                new Vector2(30, 15 * (h / w))
        );

        MapLimits up = new MapLimits(
                world,
                30,
                2 / unitPerMeters,
                new Vector2(15, 30 * (h / w))
        );

        MapLimits down = new MapLimits(
                world,
                30,
                2 / unitPerMeters,
                new Vector2(15, 0)
        );
        initHUD();
        
        gameStage.addActor(left);
        gameStage.addActor(right);
        gameStage.addActor(up);
        gameStage.addActor(down);

    }
    public void initLabel(Color color){
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
        
        if(color.equals(Color.RED)){
            label1.setText("GAME LOSE");
            label1.setPosition(Gdx.graphics.getWidth() / 2 - 0.9f*label1.getWidth(), (Gdx.graphics.getHeight() / 2 - label1.getHeight() / 2) + 0.1f * Gdx.graphics.getHeight());
        }else if(color.equals(Color.GREEN)){
            label1.setText("VICTORY");
            label1.setPosition(Gdx.graphics.getWidth() / 2 - 0.7f*label1.getWidth(), (Gdx.graphics.getHeight() / 2 - label1.getHeight() / 2) + 0.1f * Gdx.graphics.getHeight());
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
                    Logger.getLogger(TitleScreen.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(TitleScreen.class.getName()).log(Level.SEVERE, null, ex);
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
        gameStage.act();
        mapRenderer.setView((OrthographicCamera) gameStage.getCamera());
//        decommentare per seguire il player
        //stage.getCamera().position.set(player.getPosition(), gameStage.getCamera().position.z);
        mapRenderer.render();
        gameStage.draw();
        hudStage.act();
        hudStage.draw();
        debugRenderer.render(world, gameStage.getCamera().combined);
    }

    public void end(Actor actor) {
        if (actor instanceof Player) {
            initLabel(Color.RED);
            //label1.setText("GAME LOSE");
            //label1.setPosition(Gdx.graphics.getWidth() / 2 - 0.9f*label1.getWidth(), (Gdx.graphics.getHeight() / 2 - label1.getHeight() / 2) + 0.1f * Gdx.graphics.getHeight());
        } else if (actor instanceof DemoBoss) {
            initLabel(Color.GREEN);
           // label1.setText("VICTORY");
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
                            Logger.getLogger(TitleScreen.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (IOException ex) {
                            Logger.getLogger(TitleScreen.class.getName()).log(Level.SEVERE, null, ex);
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
        gameStage.dispose();

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

    private void initHUD2() {

        
        bossLife=new ProgressBar(0f,(float) db.getLife(), , true, style);
        
        this.life = new ArrayList();
        
        for(int i=0; i<player.getLife(); i++) {
            life.add(new Heart());
        }
        
        player.addListener(new UpdateHUDListener(life));
        db.addListener(new UpdateHUDListener());
        
        createLifebar();

    }

    private void createLifebar() {
        int i = 0;
        for (Heart h : life) {
            h.getImage().setPosition(8 + 10*i + h.getImage().getWidth()/15 * i  , Gdx.graphics.getHeight() - h.getImage().getHeight()/12);
            h.getImage().setSize(Gdx.graphics.getWidth()/15, Gdx.graphics.getHeight()/12);
            hudStage.addActor(h.getImage());
            i++;
        }
    }
}
