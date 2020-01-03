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
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJoint;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;
import com.gdx.game.GdxGame;

/**
 *
 * @author Armando
 */
public class BasicBulletDef implements BulletDef {

    private final int damage = 1;
    private final float initialSpeed = 10;
    protected Animation<TextureRegion> movingAnimation;
    private Texture texture = new Texture(Gdx.files.internal("texture/fireballV2/Small_Iceball_24x9.png"));
    private TextureRegion textureRegion;

    private BodyDef bd;
    private ObjectMap<String, FixtureDef> fixtureDefs = new ObjectMap<>();
    private ObjectMap<String, Animation> animations = new ObjectMap<>();
    private float width = 4f / GdxGame.game.SCALE;
    private float height = width;

    private float customScale;
    private Filter filter;

    public BasicBulletDef(MortalEntity caster,Filter filter) {
        bd = new BodyDef();

        bd.type = BodyDef.BodyType.KinematicBody;

        CircleShape circleShape = new CircleShape();
        circleShape.setRadius(width / 2);

        FilterFactory ff = new FilterFactory();

        FixtureDef fixtureDef = new FixtureDef();
        this.filter = filter;

        ff.copyFilter(fixtureDef.filter, filter);
        fixtureDef.shape = circleShape;
        fixtureDef.isSensor = true;
        fixtureDef.density = 0.1f;

        circleShape.dispose();
        
        fixtureDefs.put("basic", fixtureDef);
        
        TextureRegion[][] animation = TextureRegion.split(texture, 24, 9);
        Array<TextureRegion> array = new Array<>(animation.length * animation[0].length);
        for (TextureRegion[] textureRegions : animation) {
            array.addAll(textureRegions);
        }
        movingAnimation = new Animation<>(0.05f, array, Animation.PlayMode.LOOP);
        
        animations.put("basic", movingAnimation);
    }

    public int getDamage() {
        return damage;
    }

    public float getInitialSpeed() {
        return initialSpeed;
    }
    

    /*
    @Override
    public void initPhysics() {
        BodyDef bdDef = new BodyDef();
        bdDef.type = BodyDef.BodyType.KinematicBody;
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
        BasicBullet clone = new BasicBullet(world, getWidth()/2, getPosition(), damage, initialSpeed);
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
     */
    @Override
    public BodyDef getBodyDef() {
        return bd;
    }

    @Override
    public ObjectMap<String, FixtureDef> getFixtureDefs() {
        return fixtureDefs;
    }

    @Override
    public ObjectMap<String, Animation> getAnimations() {
        return animations;
    }

    @Override
    public float getWidth() {
        return width;
    }

    @Override
    public void setWidth(float width) {
        this.width = width;
    }

    @Override
    public float getHeight() {
        return height;
    }

    @Override
    public void setWidtHeight(float width) {
        this.width = width;
        this.height = width;
    }

    @Override
    public float getCustomScale() {
        return customScale;
    }

    @Override
    public void setCustomScale(float customScale) {
        this.customScale = customScale;
    }

    @Override
    public void setHeight(float height) {
        this.height = height;
    }

}
