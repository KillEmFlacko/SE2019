package com.gdx.game.levels;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Array;
import com.gdx.game.contact_listeners.events.DeathEvent;
import com.gdx.game.entities.Enemy;
import com.gdx.game.entities.Player;

/**
 *
 * @author Armando
 */
public final class Level2 extends Level{

    private final TiledMap map;
    private Vector2 initPlayerPos;

    public Level2(Player p) {
        super(p);
        initPlayerPos = new Vector2(12, 4);
        
        //////////// MAP ///////////
        map = new TmxMapLoader().load("maps/level2/map.tmx");
        ////////////////////////////
    }

    @Override
    public void start() {
        player.setPosition(initPlayerPos);
        addListener(new EndLevel1Listener());
        mapRenderer = new OrthogonalTiledMapRenderer(getMap(), 1f / (getPixelPerTile() * getTilePerMeter()));
        instantiateStaticObjects("walls");
        addActor(player);
    }

    @Override
    public void end() {
        fire(new EndLevelEvent());
    }

    @Override
    public TiledMap getMap() {
        return map;
    }

    @Override
    public Array<Enemy> getEnemies() {
        return null;
    }

    @Override
    protected float getTilePerMeter() {
        return 25f;
    }

    @Override
    protected float getPixelPerTile() {
        return 16f / 30f;
    }

    private class EndLevel1Listener extends EndLevelListener {

        @Override
        public void changed(ChangeListener.ChangeEvent arg0, Actor arg1) {
            if(arg0 instanceof DeathEvent){
                Level2.this.end();
            }
        }
        
    }
}
