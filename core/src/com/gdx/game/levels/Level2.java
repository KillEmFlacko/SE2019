package com.gdx.game.levels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Array;
import com.gdx.game.GdxGame;
import com.gdx.game.contact_listeners.EndDemoGameListener;
import com.gdx.game.contact_listeners.events.DeathEvent;
import com.gdx.game.entities.Enemy;
import com.gdx.game.entities.Player;
import com.gdx.game.entities.bosses.DemoBoss;
import com.gdx.game.movements.MovementSetFactory;

/**
 *
 * @author Armando
 */
public final class Level2 extends Level{

    private final TiledMap map;
    private Vector2 initPlayerPos;
    private final Array<Enemy> enemies;

    public Level2(Player p) {
        super(p);
        initPlayerPos = new Vector2(12, 4);
        
        //////////// MAP ///////////
        map = new TmxMapLoader().load("maps/level2/map.tmx");
        ////////////////////////////
        
        //////////// ENTITIES ///////////////
        MovementSetFactory mvsf = MovementSetFactory.instanceOf();
        Vector2 v = new Vector2(25, 35);
        DemoBoss db = new DemoBoss("Wandering Demon", 150, 32 / GdxGame.SCALE, 36 / GdxGame.SCALE, v, mvsf.build("Slow", "Square", false, v, 3), p);
        enemies = Array.with((Enemy) db);
        /////////////////////////////////////
        
        
    }

    @Override
    public void start() {
        player.setPosition(initPlayerPos);
        addListener(new EndLevel1Listener());
        mapRenderer = new OrthogonalTiledMapRenderer(getMap(), 1f / (getPixelPerTile() * getTilePerMeter()));
        instantiateStaticObjects("walls");
        addActor(player);
        for (Enemy enemy : enemies) {
            addActor(enemy);
        }
    }

    @Override
    public void end() {
        fire(new EndDemoGameListener.EndDemoEvent());
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
