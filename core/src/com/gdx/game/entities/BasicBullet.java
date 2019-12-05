package com.gdx.game.entities;

import com.gdx.game.factories.FilterFactory;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

/**
 *
 * @author Armando
 */
public final class BasicBullet extends Bullet {

    private final int damage;
    private final float initialSpeed;

    // ASTRAI
    public BasicBullet(World world, float radius, Vector2 position, int damage, float initSpeed) {
        super(world, radius, position);
        this.damage = damage;
        this.initialSpeed = initSpeed;
    }

    @Override
    public void init() {
        initPhysics();
    }
    
    @Override
    protected void initPhysics() {
        BodyDef bdDef = new BodyDef();
        bdDef.type = BodyDef.BodyType.KinematicBody;
        bdDef.position.set(initalPosition);

        body = world.createBody(bdDef);

        CircleShape circleShape = new CircleShape();
        circleShape.setRadius(width);

        FilterFactory ff = new FilterFactory();
        FixtureDef fixtureDef = new FixtureDef();
        ff.copyFilter(fixtureDef.filter, filter);
        fixtureDef.shape = circleShape;
        fixtureDef.density = 0.1f;

        body.createFixture(fixtureDef);
        circleShape.dispose();
    }

    @Override
    protected void initGraphics() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getDamage() {
        return damage;
    }

    @Override
    public float getInitalSpeed() {
        return initialSpeed;
    }

    @Override
    public Bullet clone() {
        BasicBullet clone = new BasicBullet(world, width, initalPosition, damage, initialSpeed);
        clone.setFilter(filter);
        return clone;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        //batch.draw(textureRegion, body.getPosition().x - width/2, body.getPosition().y - height/2, width, height);
    }
}
