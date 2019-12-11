package com.gdx.game.levels;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Array;
import com.gdx.game.GdxGame;
import com.gdx.game.entities.Enemy;
import com.gdx.game.entities.Player;

/**
 * A single game level with his entities and map.
 * A lavel is a Group, which extends Actor, so it knows how to draw itself.
 * That is, it knows how to draw the map and how to draw the other entities.
 * Also, it is a container for those entities.
 * Knows how to start and when has ended.
 * 
 * @author Davide
 */
public abstract class Level extends Group{
    protected Player player;
    
    public Level(Player p){
        this.player = p;
    }
    
    /**
     * If no player is passed we create a player of our own.
     */
    public Level() {
        /////////// PLAYER ////////////
        float playerWorldWidth = 16 / GdxGame.SCALE;
        float playerWorldHeight = 28 / GdxGame.SCALE;
        player = new Player("uajono", 100, playerWorldWidth, playerWorldHeight, Vector2.Zero);
        ///////////////////////////////
    }
    
    /**
     * Firstly, we set the player and the enemies initial positions.
     * Here we actually start the level, that is we add the actors to this Group
     * in order to start drawing them, also we start drawing the map.
     */
    public abstract void start();
    
    /**
     * Should do everything is needed to end the level and must fire the
     * EndLevel event.
     */
    public abstract void end();
    
    public Player getPlayer(){
        return player;
    }
    
    ///////////// DA TOGLIERE ?????? /////////////
    public abstract TiledMap getMap();
    
    public abstract Array<Enemy> getEnemies();
    //////////////////////////////////////////////
    
    /**
     * Should listen for events from the actors of the concrete level.
     * Every level know when ends so must listen for the events which cause it
     * to be considered finisced.
     */
    protected abstract class EndLevelListener extends ChangeListener{

    }
    
    /**
     * Every level is an actor, so he can fire events, particularly when the
     * level ends it fires and EndLevel event (ChangeEvent because we need the
     * target?)
     */
    public class EndEvent extends ChangeListener.ChangeEvent {
        
    }
}
