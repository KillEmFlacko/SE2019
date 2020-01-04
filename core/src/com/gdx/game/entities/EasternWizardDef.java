/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdx.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.utils.ObjectMap;
import com.gdx.game.GdxGame;
import com.gdx.game.factories.FilterFactory;

/**
 *
 * @author david
 */
public class EasternWizardDef implements PlayerDef {
    
    private final float STRENGHT = 1.5f;
    private final float ATTACK_RATE = 3;
    private final float SPEED = 9f;
    private final int LIFE_POINTS = 100;
    private final float BULLET_SPEED = 7f * 1.5f;
    
    private BodyDef bodyDef;
    private ObjectMap<String, FixtureDef> fixtureDefs;
    private ObjectMap<String, Animation> animations;

    public EasternWizardDef() {
        bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(16 / GdxGame.SCALE, 16 / GdxGame.SCALE);
        
        fixtureDefs = new ObjectMap<>();
        
        FilterFactory ff = new FilterFactory();
        FixtureDef fixDef = new FixtureDef();
        fixDef.shape = shape;
        ff.copyFilter(fixDef.filter, ff.getPlayerFilter());
        fixDef.isSensor = false;
        fixDef.restitution = 0f;
        fixDef.density = 0f;
        
        fixtureDefs.put("colliding", fixDef);
        
        shape.dispose();
        
        animations = new ObjectMap<>();
        
        TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("texture/player/wizzard/Ewizzard.atlas"));
        animations.put("idle", new Animation(0.2f, atlas.findRegions("m_idle"), Animation.PlayMode.LOOP));
        animations.put("run", new Animation(0.09f, atlas.findRegions("m_run"), Animation.PlayMode.LOOP));
        
    }

    @Override
    public int getBaseHP() {
        return LIFE_POINTS;
    }

    @Override
    public float getStrength() {
        return STRENGHT;
    }

    @Override
    public float getSpeed() {
        return SPEED;
    }

    @Override
    public float getAttackRate() {
        return ATTACK_RATE;
    }

    @Override
    public float getBulletSpeed() {
        return BULLET_SPEED;
    }

    @Override
    public BodyDef getBodyDef() {
        return bodyDef;
    }

    @Override
    public ObjectMap<String, FixtureDef> getFixtureDefs() {
        return fixtureDefs;
    }

    @Override
    public ObjectMap<String, Animation> getAnimations() {
        return animations;
    }

    @Override
    public float getWidth() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setWidth(float width) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public float getHeight() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setWidtHeight(float width) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setHeight(float height) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public float getCustomScale() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setCustomScale(float customScale) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
