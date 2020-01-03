package com.gdx.game.entities;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Disposable;
import com.gdx.game.GdxGame;


/**
 * Uses prototype pattern, our prototype interface is bullet.
 * @author Armando
 */
public abstract class Bullet extends Entity implements Cloneable,Disposable {
    protected Filter filter;

    public Bullet(EntityDef entityDef) {
        super(entityDef);
    }

    public Bullet(Filter filter, EntityDef entityDef) {
        super(entityDef);
        this.filter = filter;
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
    
    /*
    @Override
    public void dispose() {
        if(getStage() != null){
            getStage().getRoot().removeActor(this);
            GdxGame.game.bodyToRemove.add(this.getBody());
        }
    }
    */
}
