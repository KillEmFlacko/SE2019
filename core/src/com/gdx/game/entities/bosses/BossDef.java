/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdx.game.entities.bosses;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.utils.ObjectMap;
import com.gdx.game.entities.EntityDef;
import com.gdx.game.entities.Player;
import com.gdx.game.factories.Weapon;
import com.gdx.game.movements.Movement;
import com.gdx.game.movements.MovementSet;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author ammanas
 */
public class BossDef implements EntityDef {

    private BodyDef bd;
    private ObjectMap<String, FixtureDef> fixtureDefs = new ObjectMap<>();
    private ObjectMap<String, Animation> animations = new ObjectMap<>();
    private float width;
    private float height;
    private float customScale;

    private Float timeAcc = 2f;
    //per farlo muovere subito senza dover istanziare un movimento
    private float stateTime = 0f;
    private Texture regions;
    private Animation<TextureRegion> movementAnimation;
    private TextureAtlas atlas;
    private MovementSet movementQ;
    private BossState bossState;
    private Player player;
    private Weapon weapon;
    private Vector2 actVelocity = new Vector2(0, 0);

    private Movement prevMovement;

    public BossDef(BodyDef bd) {
        this.bd = new BodyDef();

        bd.type = BodyDef.BodyType.DynamicBody;
        //bd.position.set(getPosition());

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

        
        animations.put("moving", movementAnimation);
        fixtureDefs.put("basicHitbox", fixtureDef);
        

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

}
