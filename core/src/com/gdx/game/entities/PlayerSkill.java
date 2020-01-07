package com.gdx.game.entities;

/**
 *
 * @author ammanas
 */
public abstract class PlayerSkill extends Skill {

    public PlayerSkill(float coolDown, MortalEntity caster) {
        super(coolDown, caster);
    }
}
