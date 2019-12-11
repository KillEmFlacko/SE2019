/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdx.game.entities;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Filter;
import com.gdx.game.factories.FilterFactory;
import java.util.Date;

/**
 *
 * @author ammanas
 */
public final class LightShieldSkill extends DefenseSkill {

    private Date lastFireDate;
    private LightShieldSkillEntity lsse;

    public LightShieldSkill(float coolDown, MortalEntity caster) {
        super(coolDown, caster);
        lsse = new LightShieldSkillEntity("shieldSkill", 25, caster.getWorld(), caster.getHeight(), caster.getHeight(), caster.getPosition(), (Player) caster);

    }

    @Override
    public void cast(Vector2 direction) {
    }

    @Override
    public void castOn(MortalEntity toCastOn) {
        Date ts = new Date();

        if (lastFireDate == null || ts.getTime() - lastFireDate.getTime() > (getCoolDown() * 1000)) {

            FilterFactory ff = new FilterFactory();

            if (getCaster() instanceof Player) {

                LightShieldSkillEntity clone = new LightShieldSkillEntity(lsse.name, lsse.life, lsse.world, lsse.getHeight(), lsse.getHeight(), getCaster().getPosition(), (Player)getCaster());
                clone.initPhysics();
                clone.initGraphics();   

                getCaster().getStage().addActor(clone);

                
                

            } else {
                //.setFilter(ff.getEnemyBulletFilter());
            }

            //CALl TO GRAPHICS  and PHYSICS
            lastFireDate = new Date();
        }
    }

    public LightShieldSkillEntity getLsse() {
        return lsse;
    }
    
    

}
