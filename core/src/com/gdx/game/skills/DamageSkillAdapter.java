package com.gdx.game.skills;

import com.gdx.game.entities.MortalEntity;
import com.gdx.game.entities.bullets.SkillBullet;

/**
 *
 * @author ammanas
 */
public abstract class DamageSkillAdapter extends PlayerSkill {

    private SkillBullet b;

    public DamageSkillAdapter(float coolDown, MortalEntity caster) {
        super(coolDown, caster);
    }

    public SkillBullet getB() {
        return b;
    }

    public void setB(SkillBullet b) {
        this.b = b;
    }

}
