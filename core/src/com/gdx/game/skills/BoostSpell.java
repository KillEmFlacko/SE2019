package com.gdx.game.skills;

import com.gdx.game.entities.MortalEntity;

/**
 *
 * @author natal
 */
public abstract class BoostSpell extends PlayerSkill {

    public BoostSpell(float coolDown, MortalEntity caster) {
        super(coolDown, caster);
    }

    public float getBoostSpellMultiplier() {
        return getCaster().getBoostSpellMultiplier();
    }
}
