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
        lsse = new LightShieldSkillEntity("shieldSkill", 1, caster.getWorld(), caster.getHeight(), caster.getHeight(), caster.getPosition(), (Player) caster);
        LightShieldSkillEntity.setN_instances(0);

    }

    @Override
    public void cast(Vector2 direction) {
        
    }
    /**
     * This class instantiates an Actor representing the skill
     * The entity contains as userdata its own instance. 
     * So the clone body has a reference to its own instance.
     * @param toCastOn Who to cast a skill on. 
     */
    @Override
    public void castOn(MortalEntity toCastOn) {
        Date ts = new Date();

        if (lastFireDate == null || ts.getTime() - lastFireDate.getTime() > (getCoolDown() * 1000)) {

            FilterFactory ff = new FilterFactory();

            if (getCaster() instanceof Player && LightShieldSkillEntity.getN_instances() < 1) {

                LightShieldSkillEntity clone = new LightShieldSkillEntity(lsse.name, lsse.life, lsse.world, lsse.getHeight(), lsse.getHeight(), getCaster().getPosition(), (Player)getCaster());
                clone.initPhysics();
                clone.initGraphics();
                LightShieldSkillEntity.setN_instances(1);
                clone.body.setUserData(clone);
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
