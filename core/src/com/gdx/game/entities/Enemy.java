/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdx.game.entities;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.gdx.game.entities.MortalEntity;

/**
 *
 * @author ammanas
 */
public abstract class Enemy extends MortalEntity {

    public Enemy(String name, Integer life, World world, float width, float height, Vector2 position) {
        super(name, life, world, width, height, position);
    }


    



    
}
