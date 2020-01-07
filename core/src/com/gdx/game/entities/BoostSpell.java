package com.gdx.game.entities;

/**
 *
 * @author natal
 */
public abstract class BoostSpell extends PlayerSkill {

    public BoostSpell(MortalEntity caster, float coolDown) {
        super(caster, coolDown);
    }
}
