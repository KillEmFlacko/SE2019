package com.gdx.game.entities;

/**
 *
 * @author natal
 */
public abstract class DamageSkill extends PlayerSkill {

    public DamageSkill(MortalEntity caster, float coolDown) {
        super(caster, coolDown);
    }

}
