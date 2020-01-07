
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
import com.gdx.game.entities.MortalEntity;
import com.gdx.game.factories.FilterFactory;


public abstract class MovableCharacterClass implements CharacterClass {
    
    @Override
    public Body executePhysics(World world, Vector2 position, float fixtureWidth,float fixtureHeight, MortalEntity character) {
        Body body;
        BodyDef bdDef = new BodyDef();
        bdDef.type = BodyDef.BodyType.DynamicBody;
        bdDef.position.set(position);
        body = world.createBody(bdDef);
        body.setUserData(character);
        
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(fixtureWidth / 2, fixtureHeight / 2);
        
        FilterFactory ff = new FilterFactory();
        FixtureDef fixDef = new FixtureDef();
        fixDef.shape = shape;
        ff.copyFilter(fixDef.filter, ff.getPlayerFilter());
        fixDef.isSensor = false;
        fixDef.restitution = 0f;
        fixDef.density = 0f;
        
        Fixture fixt = body.createFixture(fixDef);
        fixt.setUserData(body);
        shape.dispose();
        return body;
    }
}
