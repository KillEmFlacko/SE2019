package com.gdx.game.entities;

import com.badlogic.gdx.math.Vector2;
import java.util.Date;

/**
 *
 * @author ammanas
 */
public final class ShieldSpell extends DefenseSkill {

    private Date lastFireDate;
    private Entity lsse;

    public ShieldSpell(MortalEntity caster, float cooldown) {
        super(caster, cooldown);
        //lsse = new LightShieldSkillEntityDef("shieldSkill", 25, caster.getWorld(), caster.getHeight(), caster.getHeight(), caster.getPosition(), (Player) caster);

        LightShieldDef lssed = new LightShieldDef();
        lsse = new Entity(lssed);

    }

    /**
     * This class instantiates an Actor representing the skill The entity
     * contains as user-data its own instance. So the clone body has a reference
     * to its own instance.
     *
     */
    @Override
    public void cast() {
        Date ts = new Date();

        if (lastFireDate == null || ts.getTime() - lastFireDate.getTime() > (getCoolDown() * 1000)) {

            Entity clone = new Entity(lsse.getEntityDef());
            clone.getBody().setUserData(clone);
            getCaster().getStage().addActor(clone);

            lastFireDate = new Date();
        }
    }

    @Override
    public void cast(Vector2 direction) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
