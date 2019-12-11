
package com.gdx.game.entities.classes;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.gdx.game.factories.FilterFactory;


public abstract class MovableCharacterClass implements CharacterClass {
    protected Body body;
    
    @Override
    public void executePhysics(World world, Vector2 position, float fixtureWidth,float fixtureHeight) {
        BodyDef bdDef = new BodyDef();
        bdDef.type = BodyDef.BodyType.DynamicBody;
        bdDef.position.set(position);
        body = world.createBody(bdDef);
        body.setUserData(this);

        // MAIN FIXTURE, THE SENSOR ONE
        PolygonShape mainShape = new PolygonShape();
        mainShape.setAsBox(fixtureWidth / 2, fixtureWidth / 2);

        FilterFactory ff = new FilterFactory();
        FixtureDef mainFixDef = new FixtureDef();
        mainFixDef.shape = mainShape;
        ff.copyFilter(mainFixDef.filter, ff.getPlayerFilter());
        mainFixDef.isSensor = true;
        mainFixDef.restitution = 0f;
        mainFixDef.density = 0f;

        // COLLISION FIXTURE
        PolygonShape collisionShape = new PolygonShape();
        collisionShape.setAsBox(fixtureWidth / 2, fixtureHeight * 0.2f / 2, new Vector2(0, -fixtureWidth * 0.8f / 2), 0f);

        FixtureDef collisionFixDef = new FixtureDef();
        collisionFixDef.shape = collisionShape;
        ff.copyFilter(collisionFixDef.filter, ff.getEntityCollisionFixtureFilter());
        collisionFixDef.isSensor = false;
        collisionFixDef.restitution = 0f;
        collisionFixDef.density = 0f;

        Fixture mainFixture = body.createFixture(mainFixDef);
        Fixture collisionFixture = body.createFixture(collisionFixDef);

        mainFixture.setUserData(body);
        collisionFixture.setUserData(body);
        mainShape.dispose();
        collisionShape.dispose();
    }

    @Override
    public Body getBody() {
        return body;
    }
    
}
