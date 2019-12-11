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
import com.badlogic.gdx.utils.Array;
import com.gdx.game.GdxGame;
import com.gdx.game.contact_listeners.events.DeathEvent;
import com.gdx.game.factories.FilterFactory;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ammanas
 */
public class LightShieldSkillEntity extends MortalEntity {

    protected Filter filter;
    protected Animation<TextureRegion> movingAnimation;
    protected Animation<TextureRegion> explosionAnimation;
    private float stateTime = 0f;
    private Player caster;
    private Texture texture;
    private TextureAtlas atlas;
    //private Fixture f;
    private RevoluteJoint revoluteJoint;
    private static int n_instances = 0;

    //height and width are the dimensions of the square in which the circle is confined
    public LightShieldSkillEntity(String name, Integer life, World world, float width, float height, Vector2 position, Player caster) {
        super(name, life, world, width, height, position);
        this.caster = caster;

    }

    @Override
    public void isHitBy(Bullet bullet) {
        life -= bullet.getDamage();
    }

    @Override
    public void kill() {
        LightShieldSkillEntity.setN_instances(getN_instances() - 1);
        //ROBBA CHE NON CAPISCO
        for (Fixture f : body.getFixtureList()) {
            body.destroyFixture(f);
        }

        GdxGame.game.bodyToRemove.add(body);
        this.getStage().getRoot().removeActor(this);

    }

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
        //fixtureDef.isSensor = true;
        fixtureDef.density = 1f;

        Fixture fxt = body.createFixture(fixtureDef);
        fxt.setUserData(this.body);
        circleShape.dispose();

        RevoluteJointDef jointDef = new RevoluteJointDef();
        jointDef.initialize(caster.body, body, caster.getPosition());

        this.world.createJoint(jointDef);

    }

    @Override
    protected void initGraphics() {

        texture = new Texture(Gdx.files.internal("texture/player/skill/shield/s420.png"));
        //atlas = new TextureAtlas(Gdx.files.internal("texture/player/skill/shield/shield3-packed/pack.atlas"));
        //movingAnimation = new Animation<TextureRegion>(0.02f, atlas.findRegions("shield3_eff"),Animation.PlayMode.LOOP);
        //textureRegion = movingAnimation.getKeyFrame(0f,true);
        //PERCHÈ È COMMENTATO : HO DELLE SPRITE DI MERDA CHE NON FUNZIONANO. Ho dovuto fare un atlas dividento un'immagine in 20 parti per ottenere un 
        //risultato mediocre. Meglio la texture fissa.
        /*
        TextureRegion[][] animation = TextureRegion.split(texture, 5, 4);
        
        Array<TextureRegion> array = new Array<>(animation.length * animation[0].length);

        for (TextureRegion[] textureRegions : animation) {
            array.addAll(textureRegions);
        }
        movingAnimation = new Animation<>(0.1f, array, Animation.PlayMode.LOOP);
        textureRegion = movingAnimation.getKeyFrame(0,true);
         */

        textureRegion = new TextureRegion(texture);

        System.out.println(textureRegion);

    }

    @Override
    public void act(float delta) {
        if (super.life <= 0) {
            kill();
            return;
        }
        stateTime += delta;
        //Questo  fa si che il body vada appresso al player MA dato che lo setti ogni volta 
        //lo scudo si trova in mezzo al boss anche se questo non potrebbe superarlo.
        //body.setTransform(caster.getPosition(), 0);
        //textureRegion = movingAnimation.getKeyFrame(stateTime, true);

    }

    public static int getN_instances() {
        return n_instances;
    }

    public static void setN_instances(int n_instances) {
        LightShieldSkillEntity.n_instances = n_instances;
    }

}
