package com.gdx.game.entities;

/**
 *
 * @author natal
 */
public abstract class DamageSkill extends PlayerSkill {

    public DamageSkill(float coolDown, MortalEntity caster) {
        super(coolDown, caster);
    }


}
