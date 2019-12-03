/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdx.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import sun.net.www.http.KeepAliveCache;

/**
 *
 * @author Giovanni
 */
public class Player extends Entity {

    public Player(World world, float width, float height, Vector2 position) {
        super(world, width, height, position);
        initPhysics();

    }

    @Override
    protected void initPhysics() {
        BodyDef bdDef = new BodyDef();
        bdDef.type = BodyDef.BodyType.DynamicBody;
        bdDef.position.set(300, 400);
        body = world.createBody(bdDef);

        CircleShape shape = new CircleShape();
        shape.setRadius(60f);

        FixtureDef fixDef = new FixtureDef();
        fixDef.shape = shape;
        fixDef.density = 0.5f;

        body.createFixture(fixDef);
        shape.dispose();
    }

    @Override
    protected void initGraphics() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    //ci servono i metodi ACT e DRAW
    //richiama nel costruttore i metodi initPhysics initGraphics
    @Override
    public void act(float delta) {
        super.act(delta); //To change body of generated methods, choose Tools | Templates.

        Vector2 velocity = new Vector2(0, 0);
        if (Gdx.input.isKeyPressed(Keys.W)) {
            velocity.add(0, 100);
        }
        if (Gdx.input.isKeyPressed(Keys.S)) {
            velocity.add(0, -100);
        }
        if (Gdx.input.isKeyPressed(Keys.A)) {
            velocity.add(-100, 0);
        }
        if (Gdx.input.isKeyPressed(Keys.D)) {
            velocity.add(100, 0);
        }

    }

}
