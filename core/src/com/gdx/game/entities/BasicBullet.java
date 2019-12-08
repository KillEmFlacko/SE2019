package com.gdx.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.gdx.game.factories.FilterFactory;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

/**
 *
 * @author Armando
 */
public final class BasicBullet extends Bullet {

    private final int damage;
    private final float initialSpeed;
    private Texture texture;
    private Animation<TextureRegion> movingAnimation;
    private Animation<TextureRegion> explosionAnimation;
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
        body.setUserData(this);

        CircleShape circleShape = new CircleShape();
        circleShape.setRadius(worldWidth/2);

        FilterFactory ff = new FilterFactory();
        FixtureDef fixtureDef = new FixtureDef();
        ff.copyFilter(fixtureDef.filter, filter);
        fixtureDef.shape = circleShape;
        fixtureDef.isSensor = true;
        fixtureDef.density = 0.1f;

        Fixture fxt = body.createFixture(fixtureDef);
        fxt.setUserData(this);
        circleShape.dispose();
    }

    @Override
    protected void initGraphics() {
        texture = new Texture(Gdx.files.internal("texture/fireballV2/Small_Iceball_24x9.png"));
        TextureRegion[][] animation = TextureRegion.split(texture, 24,9);
        Array<TextureRegion> array = new Array<>(animation[0]);
        movingAnimation = new Animation<>(0.05f, array, Animation.PlayMode.LOOP);
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

    @Override
    public void draw(Batch batch, float parentAlpha) { 
        float textureH = worldHeight;
        float textureW = worldHeight * (24/9f); 
        batch.draw(textureRegion, 
                body.getPosition().x - worldWidth / 2, body.getPosition().y - worldWidth / 2, 
                worldWidth/2, worldHeight/2, 
                textureW, textureH, 
                1, 1, 
                body.getLinearVelocity().angle()+180f);
    }
}
