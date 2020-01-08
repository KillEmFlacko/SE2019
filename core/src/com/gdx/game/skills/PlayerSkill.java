package com.gdx.game.skills;

import com.gdx.game.entities.MortalEntity;

/**
 *
 * @author ammanas
 */
public abstract class PlayerSkill extends Skill {

    public PlayerSkill(float coolDown, MortalEntity caster) {
        super(coolDown, caster);
    }
}
