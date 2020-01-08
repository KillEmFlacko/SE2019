package com.gdx.game.skills;

import com.gdx.game.entities.MortalEntity;

/**
 *
 * @author natal
 */
public abstract class DefenseSkill extends PlayerSkill {

    public DefenseSkill(float coolDown, MortalEntity caster) {
        super(coolDown, caster);
    }

}
