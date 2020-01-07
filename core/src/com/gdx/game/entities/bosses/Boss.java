/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdx.game.entities.bosses;

import com.gdx.game.entities.Enemy;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

/**
 *
 * @author ammanas
 */
public abstract class Boss extends Enemy {

    public Boss(String name, Integer life, float width, float height, Vector2 position) {
        super(name, life,  width, height, position);
    }



    

}
