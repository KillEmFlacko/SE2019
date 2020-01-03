/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdx.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Joint;
import com.badlogic.gdx.physics.box2d.JointDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJoint;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJointDef;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;
import com.gdx.game.GdxGame;
import com.gdx.game.contact_listeners.events.DeathEvent;
import com.gdx.game.factories.FilterFactory;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Inanimate Object representing the light shield that players can cast.
 * Calling the animation getter will yield an Error.
 * @author ammanas
 */
public class LightShieldSkillEntityDef implements EntityDef {
    //protected Animation<TextureRegion> explosionAnimation;

    private TextureAtlas atlas;
    //private Fixture f;
    private RevoluteJoint revoluteJoint;
    private static int n_instances = 0;

    private BodyDef bd;
    private ObjectMap<String, FixtureDef> fixtureDefs = new ObjectMap<>();
    //private ObjectMap<String, Animation> animations = new ObjectMap<>();
    private float width;
    private float height;
    private float customScale;

    private final Texture texture = new Texture(Gdx.files.internal("texture/player/skill/shield/s420.png"));
    private final TextureRegion textureRegion = new TextureRegion(texture);

    public LightShieldSkillEntityDef() {
        bd = new BodyDef();
        
        BodyDef bdDef = new BodyDef();
        bdDef.type = BodyDef.BodyType.DynamicBody;
        
        CircleShape circleShape = new CircleShape();
        circleShape.setRadius(width / 2);

        FilterFactory ff = new FilterFactory();
        Filter filter = ff.getPlayerFilter();
        FixtureDef fixtureDef = new FixtureDef();
        ff.copyFilter(fixtureDef.filter, filter);
        
        fixtureDef.shape = circleShape;
        fixtureDef.isSensor = true;
        fixtureDef.density = 1f;
        
        fixtureDefs.put("basic", fixtureDef);
        
        circleShape.dispose();

    }

    /*
    @Override
    public void kill() {
        LightShieldSkillEntityDef.setN_instances(getN_instances() - 1);
        //ROBBA CHE NON CAPISCO

        for (Fixture f : body.getFixtureList()) {
            body.destroyFixture(f);
        }

        GdxGame.game.bodyToRemove.add(body);
        this.getStage().getRoot().removeActor(this);

        //this.addAction(Actions.removeActor());
    }
     */
 /*
    @Override
    protected void initPhysics() {

        BodyDef bdDef = new BodyDef();
        bdDef.type = BodyDef.BodyType.DynamicBody;
        bdDef.position.set(caster.getPosition());
        //this.world.createBody(bdDef)   caster.body
        body = this.world.createBody(bdDef);
        body.setUserData(this);
        CircleShape circleShape = new CircleShape();
        circleShape.setRadius(getWidth() / 2);

        FilterFactory ff = new FilterFactory();
        filter = ff.getPlayerFilter();
        FixtureDef fixtureDef = new FixtureDef();

        ff.copyFilter(fixtureDef.filter, filter);
        fixtureDef.shape = circleShape;
        fixtureDef.isSensor = true;
        fixtureDef.density = 1f;

        Fixture fxt = body.createFixture(fixtureDef);
        fxt.setUserData(this.body);
        circleShape.dispose();

        RevoluteJointDef jointDef = new RevoluteJointDef();
        jointDef.initialize(caster.body, body, caster.getPosition());

        this.world.createJoint(jointDef);

    }
     */
 /*
    @Override
    protected void initGraphics() {

        texture = new Texture(Gdx.files.internal("texture/player/skill/shield/s420.png"));
        textureRegion = new TextureRegion(texture);

        System.out.println(textureRegion);

    }
     */

 /*
    @Override
    public void act(float delta) {
        if (super.life <= 0) {
            kill();
            return;
        }
        stateTime += delta;

    }

    public static int getN_instances() {
        return n_instances;
    }

    public static void setN_instances(int n_instances) {
        LightShieldSkillEntityDef.n_instances = n_instances;
    }
     */
    public TextureRegion getTextureRegion() {
        return textureRegion;
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
        throw new Error("Inanimate object");
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
