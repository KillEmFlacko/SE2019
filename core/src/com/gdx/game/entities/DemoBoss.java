/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdx.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.gdx.game.movements.MovementSetFactory;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.gdx.game.movements.*;

/**
 *
 * @author ammanas
 */
public final class DemoBoss extends Boss {

    private Float timeAcc = 0f;

    //private Queue<Vector2> animationQ;
    private MovementSet animationQ;

    public DemoBoss(String name, Integer life, World world, float width, float height, Vector2 position) {
        super(name, life, world, width, height, position);

        initPhysics();

    }

    /**
     * Method initializes the Actor physics.
     * Do not call directly.
     */
    @Override
    protected final void initPhysics() {
        //super.initPhysics(); //To change body of generated methods, choose Tools | Templates.

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.KinematicBody;
        bodyDef.position.set(Gdx.graphics.getWidth() * 2/3, Gdx.graphics.getHeight() * 2/3);

        this.body = this.world.createBody(bodyDef);
        this.body.setUserData("DemoBoss-v1");
        CircleShape shape = new CircleShape();
        shape.setRadius(40f);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 5f;

        Fixture fixture = body.createFixture(fixtureDef);
        fixture.setUserData(this);
        shape.dispose();

        world.step(1 / 60f, 6, 2);

        MovementSetFactory mvsf = MovementSetFactory.instanceOf();
        System.out.println(this.getX());
        animationQ = mvsf.build("Fast", "Triangle", true, false, this.body.getPosition());
        DemoBoss.this.body.setLinearVelocity(animationQ.frontToBack());

    }
    //private int i = 0;

    @Override
    public void act(float delta) {
        if (super.life == 0) {
            kill();
            return;
        }
        //isHit();
        timeAcc += delta;
        if (timeAcc >= 2.0f) {
            //life = 0;
            Vector2 animation = animationQ.frontToBack();
            Gdx.app.log("V", animation.toString());
            DemoBoss.this.body.setLinearVelocity(animation);
            timeAcc = 0f;

        }
    }
    
    /**
     * Method instantiates the graphics of the Actor.
     * Do not call directly.
     */

    @Override
    protected void initGraphics() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void kill() {

        this.world.destroyBody(body);

        body.setUserData(null);
        body = null;

        
        this.getStage().getRoot().removeActor(this);

        //stop animation and remove body
    }
    
    
    public void isHitBy(Bullet bullet){
        life -= bullet.getDamage();
    }
    
    /**
     * @deprecated 
     * Please DO NOT use this method. It will be removed for sure in the future.
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

    public String getName() {
        return name;
    }

    public Integer getLife() {
        return life;
    }

}
