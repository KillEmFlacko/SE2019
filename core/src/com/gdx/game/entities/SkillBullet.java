/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdx.game.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

/**
 *
 * @author ammanas
 */
public abstract class SkillBullet extends Bullet {

    protected final int damage;
    protected final float initialSpeed;
    protected Texture texture;
    protected Animation<TextureRegion> movingAnimation;
    protected Animation<TextureRegion> explosionAnimation;
    protected float stateTime = 0f;

    public SkillBullet(int damage, float initialSpeed, World world, float radius, Vector2 initialPosition) {
        super(world, radius, initialPosition);
        this.damage = damage;
        this.initialSpeed = initialSpeed;
        //do not call init here
    }

    @Override
    public int getDamage() {
        return this.damage;
    }

    @Override
    public float getInitalSpeed() {
        return this.initialSpeed;
    }

    @Override
    public abstract SkillBullet clone();

    
    
}
