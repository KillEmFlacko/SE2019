package com.gdx.game.entities.bosses;

import com.gdx.game.entities.Enemy;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

/**
 *
 * @author ammanas
 */
public abstract class Minion extends Enemy{

    public Minion(String name, Integer life,  float width, float height, Vector2 position) {
        super(name, life,width, height, position);
    }
    

    
    
    
}
