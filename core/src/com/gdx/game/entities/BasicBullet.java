package com.gdx.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.gdx.game.factories.FilterFactory;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

/**
 *
 * @author Armando
 */
public final class BasicBullet extends Bullet {

    private final int damage;
    private final float initialSpeed;
    private TextureAtlas atlas;
    private Animation<TextureAtlas.AtlasRegion> movingAnimation;
    private float stateTime = 0f;

    // ASTRAI
    public BasicBullet(World world, float radius, Vector2 position, int damage, float initSpeed) {
        super(world, radius, position);
        this.damage = damage;
        this.initialSpeed = initSpeed;
    }

    @Override
    public void init() {
        initPhysics();
        initGraphics();
    }

    @Override
    protected void initPhysics() {
        BodyDef bdDef = new BodyDef();
        bdDef.type = BodyDef.BodyType.KinematicBody;
        bdDef.position.set(initalPosition);

        body = world.createBody(bdDef);

        CircleShape circleShape = new CircleShape();
        circleShape.setRadius(worldWidth/2);

        FilterFactory ff = new FilterFactory();
        FixtureDef fixtureDef = new FixtureDef();
        ff.copyFilter(fixtureDef.filter, filter);
        fixtureDef.shape = circleShape;
        fixtureDef.isSensor = true;
        fixtureDef.density = 0.1f;
        //Armando ma non manca la user data della Fixture del proiettile? Altrimenti non vede le collisioni
        body.createFixture(fixtureDef);        
        circleShape.dispose();
    }

    @Override
    protected void initGraphics() {
        atlas = new TextureAtlas(Gdx.files.internal("texture/fireball/fireball.atlas"));
        movingAnimation = new Animation(0.10f, atlas.findRegions("moving"), Animation.PlayMode.LOOP);
        textureRegion = movingAnimation.getKeyFrame(0f);
    }

    @Override
    public void act(float delta) {
        stateTime += delta;
        textureRegion = movingAnimation.getKeyFrame(stateTime,true);
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
        BasicBullet clone = new BasicBullet(world, worldWidth, initalPosition, damage, initialSpeed);
        clone.setFilter(filter);
        return clone;
    }
}
