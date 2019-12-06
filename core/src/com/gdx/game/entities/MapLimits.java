/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdx.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

/**
 *
 * @author ammanas
 */
public class MapLimits extends Entity {

    public MapLimits(World world, float width, float height, Vector2 initialPosition) {
        super(world, width, height, initialPosition);
        initPhysics();
        //initGraphics();
    }

    @Override
    protected void initPhysics() {

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.KinematicBody;
        bodyDef.position.set(this.initalPosition);

        this.body = this.world.createBody(bodyDef);
        this.body.setUserData(this);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width / 2, height / 2);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.isSensor = false;
        fixtureDef.restitution = 0f;
        fixtureDef.density = 10f;

        Fixture fixture = body.createFixture(fixtureDef);
        fixture.setUserData(body);
        shape.dispose();

    }

    @Override
    protected void initGraphics() {
        //textureRegion = new TextureRegion(new Texture(Gdx.files.internal("badlogic.jpg")));
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
         //To change body of generated methods, choose Tools | Templates.
    }
    
    


    
    

}
