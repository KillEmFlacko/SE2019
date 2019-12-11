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
import com.badlogic.gdx.utils.Array;

/**
 *
 * @author Armando
 */
public class BasicBullet extends Bullet {

    protected final int damage;
    protected final float initialSpeed;
    private Texture texture;
    protected Animation<TextureRegion> movingAnimation;
    protected Animation<TextureRegion> explosionAnimation;
    protected float stateTime = 0f;

    // ASTRAI
    public BasicBullet( float radius, Vector2 position, int damage, float initSpeed) {
        super( radius, position);
        this.damage = damage;
        this.initialSpeed = initSpeed;
    }

    @Override
    public void initPhysics() {
        BodyDef bdDef = new BodyDef();
        bdDef.type = BodyDef.BodyType.DynamicBody;
        bdDef.bullet = true;
        bdDef.position.set(getPosition());

        body = world.createBody(bdDef);
        body.setUserData(this);

        CircleShape circleShape = new CircleShape();
        circleShape.setRadius(getWidth()/2);

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
    public void initGraphics() {
        texture = new Texture(Gdx.files.internal("texture/fireballV2/Small_Iceball_24x9.png"));
        TextureRegion[][] animation = TextureRegion.split(texture, 24,9);
        Array<TextureRegion> array = new Array<>(animation.length * animation[0].length);
        for (TextureRegion[] textureRegions : animation) {
            array.addAll(textureRegions);
        }
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
        BasicBullet clone = new BasicBullet( getWidth()/2, getPosition(), damage, initialSpeed);
        clone.setFilter(filter);
        return clone;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) { 
        float textureH = getHeight();
        float textureW = getHeight() * ((float)textureRegion.getRegionWidth()/textureRegion.getRegionHeight()); 
        batch.draw(textureRegion, 
                body.getPosition().x - getWidth() / 2, body.getPosition().y - getHeight() / 2, 
                getWidth()/2, getHeight()/2, 
                textureW, textureH, 
                1, 1, 
                body.getLinearVelocity().angle()+180f);
    }  
}
