package com.gdx.game.screens;

import com.badlogic.gdx.Game;
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
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Raffaele
 */
public class SettingsScreen implements Screen {

    private static Stage stage;
    private static GdxGame game;
    private static Label label1;
    private static float colWidth;
    private static float rowHeight;
    private static final int padding = 15;
    private static Label audio;
    private static SettingsScreen single_instance=null;
    private static Slider volume;

    private SettingsScreen(GdxGame game)  {
        
        game=getGame();
       
    
        
    }
    public static SettingsScreen getInstance() throws IOException{
        if(single_instance==null)
            single_instance=new SettingsScreen(game);
           
        
         return single_instance;
    }
    
    public static GdxGame getGame(){
        game=GdxGame.game;
        return game;
    }

    public static Stage getStage(GdxGame game){
        stage=new Stage(game.vp);
        
        initUI();
       
        return stage;
    }
    
 

    private static void initUI() {
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

        label1 = new Label("SETTINGS", lblStyle);
        label1.setSize(Gdx.graphics.getWidth(), 30);
        label1.setAlignment(Align.center);
        label1.setPosition(0, Gdx.graphics.getHeight() / 2 - 15 + 250);
        stage.addActor(label1);

        colWidth = Gdx.graphics.getWidth() / 5f;
        rowHeight = Gdx.graphics.getHeight() / 15f;
        TextButton btnButton = new TextButton("Back", GdxGame.game.skin, "default");
        btnButton.setSize(colWidth, rowHeight);
        btnButton.setPosition(padding, padding);
        btnButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                try {
                    SettingsScreen.getInstance().dispose();
                } catch (IOException ex) {
                    Logger.getLogger(SettingsScreen.class.getName()).log(Level.SEVERE, null, ex);
                }
              
                game.setScreen(new TitleScreen(game));
               
           
                return true;
            }
        });
        stage.addActor(btnButton);

        audio = new Label("AUDIO", lblStyle);
        audio.setSize(Gdx.graphics.getWidth(), 30);
        audio.setAlignment(Align.center);
        audio.setPosition(Gdx.graphics.getWidth() / 2 - 15 - 675, Gdx.graphics.getHeight() / 2 + 150);
        stage.addActor(audio);

        Skin skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
         final Slider volume = new Slider(0.0f, 1.0f, 0.1f, false, skin);
        volume.setValue(0.5f);
        volume.setPosition(Gdx.graphics.getWidth() / 2 - 140, Gdx.graphics.getHeight() / 2 + 155);

        stage.addActor(volume);

        volume.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                game.getMusic().setVolume(volume.getValue());
            }
        });

    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 1, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int arg0, int arg1) {

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
