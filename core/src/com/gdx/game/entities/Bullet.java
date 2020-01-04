package com.gdx.game.entities;

import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.utils.Disposable;
import com.gdx.game.factories.FilterFactory;

/**
 * Uses prototype pattern, our prototype interface is bullet.
 * @author Armando
 */
public class Bullet extends Entity implements Cloneable,Disposable {

    private Filter filter;
    
    public Bullet(BulletDef bulletDef, Filter filter) {
        
        super(bulletDef);
        this.filter = filter;
        FilterFactory ff = new FilterFactory();
        ff.copyFilter(bulletDef.getFixtureDefs().get("colliding").filter, filter);
        
    }

    @Override
    public BulletDef getEntityDef() {
        return (BulletDef)super.getEntityDef();
    }   
    

    @Override
    public Bullet clone(){
        return new Bullet(this.getEntityDef(),filter);
    }
    
    public int getDamage(){
        return this.getEntityDef().getDamage();
    }
    
    public float getInitialSpeed(){
        return this.getEntityDef().getInitialSpeed();
    }
    
}
