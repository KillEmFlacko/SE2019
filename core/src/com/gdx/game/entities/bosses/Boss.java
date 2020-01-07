package com.gdx.game.entities.bosses;

import com.gdx.game.entities.Enemy;
import com.badlogic.gdx.math.Vector2;

/**
 *
 * @author ammanas
 */
public abstract class Boss extends Enemy {

    public Boss(String name, Integer life, float width, float height, Vector2 position) {
        super(name, life, width, height, position);
    }

}
