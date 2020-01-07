/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdx.game.entities;

import com.gdx.game.exceptions.NotCastableException;
import com.gdx.game.exceptions.NotSelfCastableException;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

/**
 *
 * @author natal
 */
public abstract class Skill{
    
    private float coolDown;
    private TextureAtlas atlas;
    private TextureRegion region;
    private MortalEntity caster;

    public Skill(float coolDown,MortalEntity caster) {
        
        this.coolDown = coolDown;
        this.caster = caster;
    }
    
    public abstract void cast(Vector2 direction);
    public abstract void castOn(MortalEntity toCastOn);
    
    
 
    public float getCoolDown() {
        return coolDown;
    }

    public void setCoolDown(float coolDown) {
        this.coolDown = coolDown;
    }

    public MortalEntity getCaster() {
        return caster;
    }
    
    
}
