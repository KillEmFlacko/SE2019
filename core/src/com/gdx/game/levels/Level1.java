/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdx.game.levels;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.gdx.game.GdxGame;
import com.gdx.game.entities.Enemy;
import com.gdx.game.entities.Player;
import com.gdx.game.entities.bosses.DemoBoss;
import com.gdx.game.movements.MovementSetFactory;
import java.util.ArrayList;

/**
 *
 * @author Armando
 */
public class Level1 extends Level {

    private TiledMap map;
    private Array<Enemy> enemies;

    public Level1(Player p) {
        super(p);
        map = new TmxMapLoader().load("mappa_text_low_res/mappa_low_res.tmx");
        MovementSetFactory mvsf = MovementSetFactory.instanceOf();
        Vector2 v = player.getPosition().add(5, 5);
        DemoBoss db = new DemoBoss("Wandering Demon", 150, 32 / GdxGame.SCALE, 36 / GdxGame.SCALE, v, mvsf.build("Slow", "Square", false, v, 3), player);
    }

    public void start() {
        this.addListener(new EndLevelListener());
    }

    public void end() {

    }

    @Override
    public TiledMap getMap() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Enemy> getEnemies() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
