package com.gdx.game.entities.enemies;

import com.badlogic.gdx.math.Vector2;
import com.gdx.game.entities.MortalEntity;

/**
 *
 * @author ammanas
 */
public abstract class Enemy extends MortalEntity {

    public Enemy(String name, Integer life, float width, float height, Vector2 position) {
        super(name, life, width, height, position);
    }
}
