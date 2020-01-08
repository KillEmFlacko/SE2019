package com.gdx.game.entities.enemies;

import com.badlogic.gdx.math.Vector2;

/**
 *
 * @author ammanas
 */
public abstract class Minion extends Enemy {

    public Minion(String name, Integer life, float width, float height, Vector2 position) {
        super(name, life, width, height, position);
    }

}
