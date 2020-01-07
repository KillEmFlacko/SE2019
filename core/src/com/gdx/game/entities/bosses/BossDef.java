/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdx.game.entities.bosses;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.utils.ObjectMap;
import com.gdx.game.entities.MortalEntityDef;

/**
 *
 * @author ammanas
 */
public class BossDef implements MortalEntityDef {

    private BodyDef bodyDef;
    private ObjectMap<String, FixtureDef> fixtureDefs = new ObjectMap<>();
    private ObjectMap<String, Animation> animations = new ObjectMap<>();
    private float width;
    private float height;
    private float customScale;
    
    private final int LIFE_POINTS = 100;

    //per farlo muovere subito senza dover istanziare un movimento
    private Animation<TextureRegion> movementAnimation;
    private TextureAtlas atlas;

    public BossDef(BodyDef bd) {
        this.bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width * 0.6f / 2, width / 2);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.isSensor = false;
        fixtureDef.restitution = 0f;
        fixtureDef.density = 0f;
        shape.dispose();

        atlas = new TextureAtlas(Gdx.files.internal("texture/enemy/bosses/big_demon/big_demon.atlas"));
        movementAnimation = new Animation<TextureRegion>(0.1f, atlas.findRegions("run"), Animation.PlayMode.LOOP);

        animations.put("run", movementAnimation);
        fixtureDefs.put("colliding", fixtureDef);

    }

    @Override
    public BodyDef getBodyDef(Vector2 position) {
        bodyDef.position.set(position);
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

    @Override
    public int getBaseHP() {
        return LIFE_POINTS;
    }

}
