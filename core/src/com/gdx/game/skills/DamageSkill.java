package com.gdx.game.skills;

import com.gdx.game.entities.MortalEntity;

/**
 *
 * @author natal
 */
public abstract class DamageSkill extends PlayerSkill {

    public DamageSkill(float coolDown, MortalEntity caster) {
        super(coolDown, caster);
    }

}
