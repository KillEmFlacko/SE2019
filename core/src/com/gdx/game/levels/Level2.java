package com.gdx.game.levels;

import com.badlogic.gdx.Gdx;
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

    public Level2(Player p) {
        super(p);
        //////////// MAP ///////////
        map = new TmxMapLoader().load("maps/level2/map.tmx");
        ////////////////////////////

        //////////// ENTITIES ///////////////
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
        player.setPosition(new Vector2(15, 15 * (h / w)));
        Vector2 v = player.getPosition().add(5, 5);
        /////////////////////////////////////
    }

    @Override
    public void start() {
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
