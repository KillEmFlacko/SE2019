/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdx.game.entities.bosses;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.utils.ObjectMap;
import com.gdx.game.entities.EntityDef;
import java.util.Map;

/**
 *
 * @author ammanas
 */
public class BossDef implements EntityDef {

    private BodyDef bd;
    private ObjectMap<String, FixtureDef> fixtureDefs;
    private Map<String, Animation> animations;
    private float width;
    private float height;
    private float customScale;

    public BossDef(BodyDef bd, ObjectMap<String, FixtureDef> fixtureDefs, Map<String, Animation> animations) {
        this.bd = bd;
        this.fixtureDefs = fixtureDefs;
        this.animations = animations;
    }

    @Override
    public BodyDef getBodyDef() {
        return bd;
    }

    @Override
    public ObjectMap<String, FixtureDef> getFixtureDefs() {
        return fixtureDefs;
    }

    @Override
    public Map<String, Animation> getAnimations() {
        return animations;
    }

    @Override
    public float getWidth() {
        return width;
    }

    @Override
    public void setWidth(float width) {
        this.width = width;
    }

    @Override
    public float getHeight() {
        return height;
    }

    @Override
    public void setWidtHeight(float width) {
        this.width = width;
        this.height = width;
    }

    @Override
    public float getCustomScale() {
        return customScale;
    }

    @Override
    public void setCustomScale(float customScale) {
        this.customScale = customScale;
    }

    @Override
    public void setHeight(float height) {
        this.height = height;
    }

}
