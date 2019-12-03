/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdx.game.entities;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

/**
 *
 * @author Giovanni
 */
public class Player extends Entity {

    public Player(World world, float width, float height, Vector2 position) {
        super(world, width, height, position);
    }

    @Override
    protected void initPhysics() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void initGraphics() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    //ci servono i metodi ACT e DRAW
    //richiama nel costruttore i metodi initPhysics initGraphics
    
    
    
    
}
