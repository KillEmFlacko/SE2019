package com.gdx.game.entities;

/**
 *
 * @author natal
 */
public abstract class DefenseSkill extends PlayerSkill {

    public DefenseSkill(float coolDown, MortalEntity caster) {
        super(coolDown, caster);
    }

    public float getDefenseSpellMultiplier() {
        return getCaster().getDefenseSpelMultiplier();
    }
}
