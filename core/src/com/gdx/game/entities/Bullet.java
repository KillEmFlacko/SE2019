package com.gdx.game.entities;

import com.badlogic.gdx.physics.box2d.Filter;
import com.gdx.game.actions.GameAction;
import com.gdx.game.factories.FilterFactory;

/**
 * Uses prototype pattern, our prototype interface is bullet.
 *
 * @author Armando
 */
public class Bullet extends Entity implements Cloneable {

    private Filter filter;
    private float damage;
    private float damageMultiplicator;
    private float speed;

    public Bullet(BulletDef bulletDef, Filter filter, float speed) {
        super(bulletDef);
        this.filter = filter;
        FilterFactory ff = new FilterFactory();
        ff.copyFilter(bulletDef.getFixtureDefs().get("colliding").filter, filter);

        this.damage = bulletDef.getBaseDamage();
        this.speed = speed;
    }

    @Override
    public BulletDef getEntityDef() {
        return (BulletDef) super.getEntityDef();
    }

    @Override
    public Bullet clone() {
        return new Bullet(this.getEntityDef(), filter, speed);
    }

    public void setDamageMultiplicator(float dm){
        damageMultiplicator = dm;
        damage *= damageMultiplicator;
    }
    
    public float getSpeed() {
        return speed;
    }

    public float getDamage() {
        return damage;
    }

    private class BulletAction extends GameAction {

        @Override
        public boolean act(float delta) {
            stateTime += delta;
            setRegionToDraw(getEntityDef().getAnimations().get("run").getKeyFrame(stateTime, true));
            return true;
        }
    }
}
