package com.gdx.game.entities;

import com.gdx.game.factories.FilterFactory;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.gdx.game.factories.Weapon;

/**
 *
 * @author Giovanni
 */
public final class Player extends MortalEntity {

    private TextureAtlas atlas;
    private final Weapon weapon;
    private Animation<TextureAtlas.AtlasRegion> runAnimation;
    private Animation<TextureAtlas.AtlasRegion> idleAnimation;
    private float stateTime = 0f;
    private final float speed = 9f;

    public Player(String name, int lifepoints, World world, float width, float height, Vector2 position) {
        super(name, lifepoints, world, width, height, position);
        weapon = new Weapon(this, new BasicBullet(world, 1f, position, 10, speed * 1.5f), 3);
        initPhysics();
        initGraphics();
    }

    @Override
    protected void initPhysics() {
        BodyDef bdDef = new BodyDef();
        bdDef.type = BodyDef.BodyType.DynamicBody;
        bdDef.position.set(initalPosition);
        body = world.createBody(bdDef);
        body.setUserData(this);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(worldWidth / 2, worldWidth / 2);

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
    }

    @Override
    protected void initGraphics() {
        atlas = new TextureAtlas(Gdx.files.internal("texture/player/wizzard/wizzard.atlas"));
        idleAnimation = new Animation(0.2f, atlas.findRegions("m_idle"), Animation.PlayMode.LOOP);
        runAnimation = new Animation(0.09f, atlas.findRegions("m_run"), Animation.PlayMode.LOOP);
        textureRegion = idleAnimation.getKeyFrame(0f);
    }

    @Override
    public void act(float delta) {
        stateTime += delta;
        super.act(delta);

        Vector2 velocity = new Vector2(0, 0);
        if (Gdx.input.isKeyPressed(Keys.W)) {
            velocity.add(0, speed);
        }
        if (Gdx.input.isKeyPressed(Keys.S)) {
            velocity.add(0, -speed);
        }
        if (Gdx.input.isKeyPressed(Keys.A)) {
            velocity.add(-speed, 0);
        }
        if (Gdx.input.isKeyPressed(Keys.D)) {
            velocity.add(speed, 0);
        }
        if (!body.getLinearVelocity().equals(velocity)) {
            setLinearVelocity(velocity);
        }

        if (!body.getLinearVelocity().equals(new Vector2(0, 0))) {
            textureRegion = runAnimation.getKeyFrame(stateTime);
        } else {
            textureRegion = idleAnimation.getKeyFrame(stateTime);
        }

        if (textureRegion.isFlipX()) {
            textureRegion.flip(true, false);
        }

        if ((Gdx.input.isKeyPressed(Keys.A) || Gdx.input.isKeyPressed(Keys.S)) && !(Gdx.input.isKeyPressed(Keys.D))) {
            textureRegion.flip(true, false);
        }
        shouldShoot(delta);
    }

    public void shouldShoot(float delta) {
        if (Gdx.input.isKeyPressed(Keys.UP)) {
            weapon.fire(new Vector2(0, 1));
        }
        if (Gdx.input.isKeyPressed(Keys.DOWN)) {
            weapon.fire(new Vector2(0, -1));
        }
        if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
            weapon.fire(new Vector2(1, 0));
        }
        if (Gdx.input.isKeyPressed(Keys.LEFT)) {
            weapon.fire(new Vector2(-1, 0));
        }
    }

}
