package com.gdx.game.entities;

import com.badlogic.gdx.math.Vector2;

/**
 *
 * @author natal
 */
public abstract class Skill {

    private float coolDown;
    private MortalEntity caster;

    public Skill(MortalEntity caster, float cooldown) {

        this.coolDown = coolDown;
        this.caster = caster;
    }

    public abstract void cast(Vector2 direction);
    public abstract void cast();
    

    public float getCoolDown() {
        return coolDown;
    }

    public void setCoolDown(float coolDown) {
        this.coolDown = coolDown;
    }

    public MortalEntity getCaster() {
        return caster;
    }

}
