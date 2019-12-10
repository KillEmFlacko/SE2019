/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdx.game.entities;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

/**
 *
 * @author natal
 */
public abstract class Skill extends Entity{
    
    private float coolDown;
    private TextureAtlas atlas;
    private TextureRegion region;
    private Entity caster;

    public Skill(float coolDown, World world, float width, float height, Vector2 initialPosition, Entity caster) {
        super(world, width, height, initialPosition);
        this.coolDown = coolDown;
        this.caster = caster;
    }
    
 
    public float getCoolDown() {
        return coolDown;
    }

    public void setCoolDown(float coolDown) {
        this.coolDown = coolDown;
    }    
}
