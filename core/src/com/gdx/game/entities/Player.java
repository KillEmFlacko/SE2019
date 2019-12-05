package com.gdx.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.Batch;
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
    private Weapon weapon;

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
        bdDef.position.set(300, 400);
        body = world.createBody(bdDef);
        body.setUserData(this);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width / 2, height / 2);

        FixtureDef fixDef = new FixtureDef();
        fixDef.shape = shape;
        FilterFactory.copyFilter(fixDef.filter, FilterFactory.getPlayerFilter());
        fixDef.density = 0.5f;

        Fixture fixt = body.createFixture(fixDef);
        fixt.setUserData(body);
        shape.dispose();
    }

    @Override
    protected void initGraphics() {
        atlas = new TextureAtlas(Gdx.files.internal("texture/player/test_pack.atlas"));
        textureRegion = atlas.findRegion("down");
    }

    //ci servono i metodi ACT e DRAW
    //richiama nel costruttore i metodi initPhysics initGraphics
    @Override
    public void act(float delta) {
        super.act(delta); //To change body of generated methods, choose Tools | Templates.

        Vector2 velocity = new Vector2(0, 0);
        if (Gdx.input.isKeyPressed(Keys.W)) {
            velocity.add(0, 20);
        }
        if (Gdx.input.isKeyPressed(Keys.S)) {
            velocity.add(0, -20);
        }
        if (Gdx.input.isKeyPressed(Keys.A)) {
            velocity.add(-20, 0);
        }
        if (Gdx.input.isKeyPressed(Keys.D)) {
            velocity.add(20, 0);
        }
        if (!body.getLinearVelocity().equals(velocity)) {
            body.setLinearVelocity(velocity);
        }

        if (Gdx.input.isKeyJustPressed(Keys.W)) {
            textureRegion = atlas.findRegion("up");
        } else if (Gdx.input.isKeyJustPressed(Keys.S)) {
            textureRegion = atlas.findRegion("down");
        } else if (Gdx.input.isKeyJustPressed(Keys.A)) {
            textureRegion = atlas.findRegion("left");
        } else if (Gdx.input.isKeyJustPressed(Keys.D)) {
            textureRegion = atlas.findRegion("right");
        }
        shouldShoot(delta);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) { //Draw dice al batch cosa deve disegnare. Lo stage ogni volta che fai stage.draw chiama tutti i draw degli actors passandogli il batch in modo che possono contribire al batch e disegna tutto insieme
        batch.draw(textureRegion, body.getPosition().x - width / 2, body.getPosition().y - height / 2, width, height);
    }

    @Override
    protected void setLinearVelocity(Vector2 velocity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void setLinearVelocity(float x, float y) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected Vector2 getLinearVelocity() {
        return body.getLinearVelocity();
    }

    @Override
    public Vector2 getPosition() {
        return body.getPosition();
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
