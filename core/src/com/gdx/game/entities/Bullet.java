package com.gdx.game.entities;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.utils.Disposable;

/**
 * Uses prototype pattern, our prototype interface is bullet.
 * @author Armando
 */
public class Bullet extends Entity implements Cloneable,Disposable {
    protected Filter filter;

    public Bullet(BulletDef entityDef) {
        super(entityDef);
    }

    public Bullet(Filter filter, BulletDef entityDef) {
        super(entityDef);
        this.filter = filter;
    }

    public void setFilter(Filter f){
        this.filter = f;
    }

    @Override
    public BulletDef getEntityDef() {
        return (BulletDef)super.getEntityDef();
    }   
    

    @Override
    public Bullet clone(){
        return new Bullet(filter, this.getEntityDef());
    }
    
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
