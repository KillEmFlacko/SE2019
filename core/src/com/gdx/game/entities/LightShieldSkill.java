/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdx.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Filter;
import com.gdx.game.factories.FilterFactory;
import java.util.Date;

/**
 *
 * @author ammanas
 */
public final class LightShieldSkill extends DefenseSkill {

    private Date lastFireDate;
    private Entity lsse;
    
  

    public LightShieldSkill(float coolDown, MortalEntity caster) {
        super(coolDown, caster);
        //lsse = new LightShieldSkillEntityDef("shieldSkill", 25, caster.getWorld(), caster.getHeight(), caster.getHeight(), caster.getPosition(), (Player) caster);

        LightShieldSkillEntityDef lssed = new LightShieldSkillEntityDef(this.getCaster(),caster.getFilter());
        lsse = new Entity(lssed);

    }

    @Override
    public void cast(Vector2 direction) {

    }

    /**
     * This class instantiates an Actor representing the skill The entity
     * contains as user-data its own instance. So the clone body has a reference
     * to its own instance.
     *
     * @param toCastOn Who to cast a skill on.
     */
    @Override
    public void castOn(MortalEntity toCastOn) {
        Date ts = new Date();

        if (lastFireDate == null || ts.getTime() - lastFireDate.getTime() > (getCoolDown() * 1000)) {

            Entity clone = new Entity(lsse.getEntityDef());
            clone.getBody().setUserData(clone);
            getCaster().getStage().addActor(clone);

            lastFireDate = new Date();
        }
    }

}
