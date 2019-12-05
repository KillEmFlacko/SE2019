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
public final class Player extends Entity {

    private TextureAtlas atlas;
    private final Weapon weapon;
    private Animation<TextureAtlas.AtlasRegion> runAnimation;
    private Animation<TextureAtlas.AtlasRegion> idleAnimation;
    private float stateTime = 0f;

    public Player(World world, float width, float height, Vector2 position) {
        super(world, width, height, position);
        weapon = new Weapon(this, new BasicBullet(world, 2, position, 10, 100), 5);
        initPhysics();
        initGraphics();
    }

    @Override
    protected void initPhysics() {
        BodyDef bdDef = new BodyDef();
        bdDef.type = BodyDef.BodyType.DynamicBody;
        bdDef.position.set(300, 100);
        body = world.createBody(bdDef);
        body.setUserData(this);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width / 2, height / 2);

        FilterFactory ff = new FilterFactory();
        FixtureDef fixDef = new FixtureDef();
        fixDef.shape = shape;
        ff.copyFilter(fixDef.filter, ff.getPlayerFilter());
        fixDef.density = 0.5f;

        Fixture fixt = body.createFixture(fixDef);
        fixt.setUserData(body);
        shape.dispose();
    }

    @Override
    protected void initGraphics() {
        atlas = new TextureAtlas(Gdx.files.internal("texture/player/knight.atlas"));
        idleAnimation = new Animation(0.15f, atlas.findRegions("m_idle"), Animation.PlayMode.LOOP);
        runAnimation = new Animation(0.10f, atlas.findRegions("m_run"), Animation.PlayMode.LOOP);

    }

    @Override
    public void act(float delta) {
        stateTime += delta;
        super.act(delta);

        Vector2 velocity = new Vector2(0, 0);
        if (Gdx.input.isKeyPressed(Keys.W)) {
            velocity.add(0, 50);
        }
        if (Gdx.input.isKeyPressed(Keys.S)) {
            velocity.add(0, -50);
        }
        if (Gdx.input.isKeyPressed(Keys.A)) {
            velocity.add(-50, 0);
        }
        if (Gdx.input.isKeyPressed(Keys.D)) {
            velocity.add(50, 0);
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
