package com.gdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.gdx.game.screens.TitleScreen;
import java.io.IOException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GdxGame extends Game {

    public Skin skin;
    public Viewport vp;
    public AssetManager assetManager;
    public static GdxGame game;
    public static final float SCALE = 6.0f;
    private Music music;
    private static final float DEFAULT_VOLUME = 0.5f;
    
    
    
    
    public LinkedList<Body> bodyToRemove = new LinkedList<Body>();
    

        
    
    public GdxGame(Viewport vp) {
        this.vp = vp;
        assetManager = new AssetManager();
        game = this;
      
       
    }
    
    public void initAudio(){
        music=Gdx.audio.newMusic(Gdx.files.internal("audio/menu/wakawaka.mp3"));
        music.play();  
        music.setLooping(true);
        music.setVolume(DEFAULT_VOLUME); 
    }

    public static float getDEFAULT_VOLUME() {
        return DEFAULT_VOLUME;
    }

    
  
    public Music getMusic() {
        return music;
    }
    

    @Override
    public void create() {
        skin = new Skin(Gdx.files.internal("skin/expee-ui.json"));
       
        try {
            this.setScreen(new TitleScreen(this));
        } catch (IOException ex) {
            Logger.getLogger(GdxGame.class.getName()).log(Level.SEVERE, null, ex);
        }
        
           initAudio();
           
         
    }
  

    @Override
    public void render() {
        vp.apply();
        super.render();
    }

    @Override
    public void dispose() {
    }

    @Override
    public void resize(int width, int height) {
        vp.update(width, height);
    }
}
