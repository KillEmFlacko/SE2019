/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdx.game.entities.bosses;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;
import com.gdx.game.entities.Bullet;
import com.gdx.game.movements.MovementSetFactory;

import com.gdx.game.movements.*;

/**
 *
 * @author ammanas
 */
public final class DemoBoss extends Boss {
    private Float timeAcc = 2f;
    //per farlo muovere subito senza dover istanziare un movimento
    private float stateTime = 0f;
    private Texture regions;
    private Animation<TextureRegion> movementAnimation;
    private TextureAtlas atlas;
    private MovementSet movementQ;
    private BossState bossState;

    public DemoBoss(String name, Integer life, World world, float width, float height, Vector2 position,MovementSet movementQ) {
        super(name, life, world, width, height, position);
        this.movementQ = movementQ;
        
        //bossState = new IdleState(); TO ADD
        //this.movementQ = bossState.onIdle() TO ADD
        initPhysics();
        initGraphics();


    }

    /**
     * Initializes the Actor physics. Do not call directly.
     */
    @Override
    protected final void initPhysics() {


        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(this.initalPosition);

        this.body = this.world.createBody(bodyDef);
        this.body.setUserData(this);
        CircleShape shape = new CircleShape();
        //PolygonShape p = new PolygonShape();        
        //p.setAsBox(width/2, height/2);
        shape.setRadius(width/2);
        
        
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.isSensor = false;
        fixtureDef.restitution = 0f;
        fixtureDef.density = 0f;

        Fixture fixture = body.createFixture(fixtureDef);
        fixture.setUserData(body);
        shape.dispose();

        MovementSetFactory mvsf = MovementSetFactory.instanceOf();


    }
    //private int i = 0;

    @Override
    public void act(float delta) {
        if (super.life == 0) {
            kill();
            return;
        }
        timeAcc += delta;

        textureRegion = movementAnimation.getKeyFrame(stateTime, true);
        
        stateTime += delta;

        if (timeAcc >= 2.0f) {

            Vector2 movement = movementQ.frontToBack();
            Gdx.app.log("V", movement.toString());
            DemoBoss.this.body.setLinearVelocity(movement);
            checkDirection(movement);

            timeAcc = 0f;

        }

    }

    public BossState getBossState() {
        return bossState;
    }

    public void setBossState(BossState bossState) {
        this.bossState = bossState;
    }
    
    
    /**
     * Method instantiates the graphics of the Actor. Do not call directly.
     */
    @Override
    protected void initGraphics() {

        //atlas = new TextureAtlas(Gdx.files.internal("texture/enemy/bosses/knight/knight_run/knight.atlas"));
        atlas = new TextureAtlas(Gdx.files.internal("texture/enemy/bosses/monster_run/monster_run.atlas"));

        //System.out.println(atlas.findRegions("run").size);
        movementAnimation = new Animation<TextureRegion>(0.1f, atlas.findRegions("big_demon"), PlayMode.LOOP);
        //movementAnimation = new Animation<TextureRegion>(0.1f, atlas.findRegions("f_run"), PlayMode.LOOP);
    }

    public void kill() {

        this.world.destroyBody(body);

        body.setUserData(null);
        body = null;
        this.getStage().getRoot().removeActor(this);

        //stop animation and remove body
    }

    public void isHitBy(Bullet bullet) {
        life -= bullet.getDamage();
    }

    /**
     * @deprecated Please DO NOT use this method. It will be removed for sure in
     * the future.
     * @return
     */
    public boolean isHit() {

        world.setContactListener(new ContactListener() {
            @Override
            public void beginContact(Contact contact) {
                Fixture a = contact.getFixtureA();
                Fixture b = contact.getFixtureB();
                Gdx.app.log("Begin Contact", "");
                Gdx.app.log("User Data ", b.getBody().getUserData().toString());

                if (b.getBody().getUserData() == DemoBoss.this.getUserData()) {
                    life = life - (int) a.getDensity();
                    //or any other way to calculate damage

                } else {
                    life = life - (int) b.getDensity();
                    //or any other way to calculate damage
                }

                Gdx.app.log("Life", life.toString());

            }

            @Override
            public void endContact(Contact contact) {
                Gdx.app.log("End contact", "");
            }

            @Override
            public void preSolve(Contact arg0, Manifold arg1) {
            }

            @Override
            public void postSolve(Contact arg0, ContactImpulse arg1) {

            }
        });

        return true;

    }

    public Object getUserData() {
        return this.body.getUserData();
    }

    public void flipFrames(boolean x, boolean y) {
        for (TextureRegion frame : movementAnimation.getKeyFrames()) {
            frame.flip(x, y);
        }
    }
    /**
     * @deprecated 
     * @param animation 
     */

    public void changeTextureRegion(Vector2 animation) {

        if (animation.x > 0) {

            
            textureRegion = new TextureRegion(regions, 0.5f, 0.5f, 1f, 1f);
            //flipFrames(true, false);

        } else if (animation.x < 0) {
            textureRegion = new TextureRegion(regions, 0f, 0f, 0.5f, 0.5f);
            //flipFrames(true, false);

        } else {
            if (animation.y < 0) {
                textureRegion = new TextureRegion(regions, 0.5f, 0f, 1f, 0.5f);

            } else if (animation.y > 0) {
                textureRegion = new TextureRegion(regions, 0f, 0.5f, 0.5f, 1f);
                //flipFrames(false, true);

            } else {
                textureRegion = new TextureRegion(regions, 0.5f, 0f, 1f, 0.5f);
            }
        }

    }

    public void checkDirection(Vector2 movement) {
        if (movement.x > 0 && movement.y == 0) {

            //textureRegion.flip(true, false);
            flipFrames(true, false);

        } else if (movement.x < 0 && movement.y == 0) {

            //System.out.println("jee");
            //textureRegion.flip(true, false);
            flipFrames(true, false);


        } else if (movement.x == 0 && movement.y > 0) {
            //change texture region to y one by moving on the atlas
        } else if (movement.x == 0 && movement.y < 0) {
            //change texture region by moving on the atlas
        } else {
            System.out.println("WHat?!");
        }
    }

    public String getName() {
        return name;
    }
}
