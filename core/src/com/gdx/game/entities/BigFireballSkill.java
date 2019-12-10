/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdx.game.entities;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.gdx.game.factories.FilterFactory;

/**
 *
 * @author ammanas
 */
public final class BigFireballSkill extends DamageSkillAdapter {

    public BigFireballSkill(float coolDown, World world, float width, float height, Vector2 initialPosition, float initSpeed, Player caster, int damage) {
        super(coolDown, world, width, height, initialPosition, caster,damage);
        this.setB(new BigFireballSkillBullet(damage,initSpeed , world, worldWidth, initialPosition));
        
    }


    @Override
    protected void initPhysics() {
        this.getB().initPhysics();
    }

    @Override
    protected void initGraphics() {
        this.getB().initGraphics();
    }


}
