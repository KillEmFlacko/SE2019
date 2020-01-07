package com.gdx.game.entities;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.utils.Disposable;
import com.gdx.game.GdxGame;


/**
 * Uses prototype pattern, our prototype interface is bullet.
 * @author Armando
 */
public abstract class Bullet extends Entity implements Cloneable {
    protected Filter filter;
    public Bullet( float radius, Vector2 initialPosition) {
        super( radius*2, radius*2, initialPosition);
    }
    public abstract int getDamage();
    public abstract float getInitalSpeed();
    public void setFilter(Filter f){
        this.filter = f;
    }
    public abstract void initPhysics();
    public abstract void initGraphics();
    
    @Override
    public abstract Bullet clone();
    
    @Override
    public void setLinearVelocity(Vector2 velocity){
        super.setLinearVelocity(velocity);
    }

    @Override
    public void setLinearVelocity(float x, float y){
        super.setLinearVelocity(x, y);
    }

    @Override
    public Vector2 getLinearVelocity(){
        return super.getLinearVelocity();
    }
}
