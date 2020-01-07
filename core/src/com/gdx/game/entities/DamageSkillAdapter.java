package com.gdx.game.entities;

/**
 *
 * @author ammanas
 */
public abstract class DamageSkillAdapter extends PlayerSkill {

    private SkillBullet b;

    public DamageSkillAdapter(float coolDown, MortalEntity caster) {
        super(coolDown, caster);
    }

    public float getDamageSpellMultiplier() {
        return getCaster().getDamageSpellMultiplier();
    }

    public SkillBullet getB() {
        return b;
    }

    public void setB(SkillBullet b) {
        this.b = b;
    }

}
