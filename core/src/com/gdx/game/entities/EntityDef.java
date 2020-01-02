package com.gdx.game.entities;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.utils.ObjectMap;
import java.util.Map;

/**
 *
 * @author david
 */
public interface EntityDef {

    public BodyDef getBodyDef();

    public ObjectMap<String, FixtureDef> getFixtureDefs();

    public Map<String, Animation> getAnimations();
    
    public float getWidth();
    
    public void setWidth(float width);
    
    public float getHeight();
    
    public void setWidtHeight(float width);
    
    public void setHeight(float height);
    
    // !! modificatore d'accesso protected non permesso
    public float getCustomScale();
    
    // !! modificatore d'accesso protected non permesso
    public void setCustomScale(float customScale);
}
