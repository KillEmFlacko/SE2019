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
 * @author ammanas
 */
public abstract class DamageSkillAdapter extends PlayerSkill {

    private SkillBullet b;

    public DamageSkillAdapter(float coolDown, World world, float width, float height, Vector2 initialPosition,Player caster,int damage) {
        super(coolDown, world, width, height, initialPosition,caster);
        //b is not initialized here, left to subclasses
    }

    public float getDamageSpellMultiplier() {
        return caster.getDamageSpellMultiplier();
    }

    public Bullet getB() {
        return b;
    }

    public void setB(SkillBullet b) {
        this.b = b;
    }
    

}
