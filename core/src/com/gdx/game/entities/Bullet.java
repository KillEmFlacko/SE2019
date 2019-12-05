/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdx.game.entities;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.World;
import com.gdx.game.entities.Entity;


/**
 * Uses prototype pattern, our prototype interface is bullet.
 * @author Armando
 */
public abstract class Bullet extends Entity implements Cloneable{
    
    public Bullet(World world, float radius, Vector2 position) {
        super(world, radius, radius, position);
    }
    public abstract int getDamage();
    public abstract float getInitalSpeed();
    public abstract void setFilter(Filter f);
    public abstract void init();
    @Override
    public abstract Bullet clone();
    @Override
    public abstract void setLinearVelocity(Vector2 velocity);

    @Override
    public abstract void setLinearVelocity(float x, float y);

    @Override
    public abstract Vector2 getLinearVelocity();
    
    public abstract void setInitialPosition(Vector2 initialPos);
}
