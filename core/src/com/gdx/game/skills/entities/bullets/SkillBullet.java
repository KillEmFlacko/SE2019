package com.gdx.game.skills.entities.bullets;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.gdx.game.entities.bullets.Bullet;

/**
 *
 * @author ammanas
 */
public abstract class SkillBullet extends Bullet {

    protected final int damage;
    protected final float initialSpeed;
    protected Texture texture;
    protected Animation<TextureRegion> movingAnimation;
    protected Animation<TextureRegion> explosionAnimation;
    protected float stateTime = 0f;

    public SkillBullet(int damage, float initialSpeed, float radius, Vector2 initialPosition) {
        super(radius, initialPosition);
        this.damage = damage;
        this.initialSpeed = initialSpeed;
        //do not call init here
    }

    @Override
    public int getDamage() {
        return this.damage;
    }

    @Override
    public float getInitalSpeed() {
        return this.initialSpeed;
    }

    @Override
    public abstract SkillBullet clone();

}
