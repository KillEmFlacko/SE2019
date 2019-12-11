package com.gdx.game.levels;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.gdx.game.entities.Enemy;
import com.gdx.game.entities.Player;
import java.util.ArrayList;

/**
 *
 * @author Davide
 */
public abstract class Level extends Group{
    protected Player player;
    
    public Level(Player p){
        this.player = p;
    }
    
    public void start(){
        this.addListener(new EndLevelListener());
    }
    
    public void end(){
        
    }
    
    public Player getPlayer(){
        return player;
    }
    
    public abstract TiledMap getMap();
    
    public abstract ArrayList<Enemy> getEnemies();
    
    protected class EndLevelListener extends ChangeListener{

        @Override
        public void changed(ChangeEvent ce, Actor actor) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }
}
