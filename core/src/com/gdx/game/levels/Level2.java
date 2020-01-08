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
import com.gdx.game.listeners.event.events.DeathEvent;
import com.gdx.game.entities.enemies.Enemy;
import com.gdx.game.entities.Player;
import com.gdx.game.entities.enemies.DemoBoss;
import com.gdx.game.factories.MovementSetFactory;

/**
 *
 * @author Armando
 */
public final class Level2 extends Level {

    private TiledMap map;
    private Vector2 initPlayerPos;
    private Array<Enemy> enemies;

    public Level2(Player p) {
        super(p);
        setName("Level2");
    }

    @Override
    public void start() {
        initPlayerPos = new Vector2(11, 4);

        //////////// MAP ///////////
        map = new TmxMapLoader().load("maps/level2/map.tmx");
        ////////////////////////////

        //////////// ENTITIES ///////////////
        MovementSetFactory mvsf = MovementSetFactory.instanceOf();
        Vector2 v = new Vector2(25, 35);
        DemoBoss db = new DemoBoss("Wandering Demon", 150, 32 / GdxGame.SCALE, 36 / GdxGame.SCALE, v, mvsf.build("Slow", "Square", false), getPlayer());
        enemies = Array.with((Enemy) db);
        /////////////////////////////////////

        instantiateStaticObjects("walls");
        mapRenderer = new OrthogonalTiledMapRenderer(getMap(), 1f / (getPixelPerTile() * getTilePerMeter()));
        Gdx.app.log("Level2", "setting player position");
        player.setPosition(initPlayerPos);
        addListener(new EndLevel2Listener());
        addActor(player);
        for (Enemy enemy : enemies) {
            addActor(enemy);
        }
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

    private class EndLevel2Listener extends EndLevelListener {

        @Override
        public void changed(ChangeListener.ChangeEvent arg0, Actor arg1) {
            if (arg0 instanceof DeathEvent) {
                dispose();
                fire(new EndLevelEvent(!(arg1 instanceof Player)));
            }
        }
    }
}
