package com.gdx.game.entities.enemies;

import com.gdx.game.entities.bullets.DemoBossBasicBullet;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.gdx.game.GdxGame;
import com.gdx.game.listeners.event.events.HitEvent;
import com.gdx.game.entities.bullets.Bullet;
import com.gdx.game.entities.Player;
import com.gdx.game.factories.movement_products.MovementSet;
import java.util.Random;
import com.gdx.game.factories.Weapon;
import com.gdx.game.factories.movement_products.Movement;

/**
 *
 * @author ammanas
 */
public final class DemoBoss extends Boss {

    private Float timeAcc = 1f;
    //per farlo muovere subito senza dover istanziare un movimento
    private float stateTime = 0f;
    private Texture regions;
    private Animation<TextureRegion> movementAnimation;
    private TextureAtlas atlas;
    private MovementSet movementQ;
    private BossState bossState;
    private Player player;
    private Weapon weapon;
    private Vector2 actVelocity = new Vector2(0, 0);

    public DemoBoss(String name, Integer life, float width, float height, Vector2 position, MovementSet movementQ, Player player) {
        super(name, life, width, height, position);
        this.movementQ = movementQ;
        this.player = player;
        DemoBossBasicBullet b = new DemoBossBasicBullet(4f / GdxGame.game.SCALE, this.player.getPosition(), 1, 10f);
        this.weapon = new Weapon(this, b, 1);

        defaultAction = new DemoBossAction();
    }

    /**
     * Initializes the Actor physics. Do not call directly.
     */
    @Override
    public final void initPhysics() {

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(getPosition());

        this.body = this.world.createBody(bodyDef);
        this.body.setUserData(this);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(getWidth() * 0.6f / 2, getWidth() / 2);
//        CircleShape shape = new CircleShape();
//        shape.setRadius(worldWidth/2);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.isSensor = false;
        fixtureDef.restitution = 0f;
        fixtureDef.density = 0f;

        Fixture fixture = body.createFixture(fixtureDef);
        fixture.setUserData(this);
        shape.dispose();

    }
    //private int i = 0;

    private class DemoBossAction extends Action {

        @Override
        public boolean act(float delta) {
            if (body == null) {
                initGraphics();
                initPhysics();
            }
            setPosition(body.getPosition());
            if (DemoBoss.super.life <= 0) {
                kill();
                return true;
            }
            timeAcc += delta;
            stateTime += delta;

            textureRegion = movementAnimation.getKeyFrame(stateTime, true);

            Vector2 playerPosition = player.getPosition();
            Vector2 newMovePlayer = new Vector2(playerPosition.sub(DemoBoss.this.getPosition()));

            if (timeAcc >= 2.0f) {
                Random r = new Random();

                if ((r.nextFloat() * 10) >= 6) {
                    //si lancia contro il player
                    //System.out.println("Player position" + playerPosition);
                    actVelocity.set(newMovePlayer.scl(1.3f));
                    //checkDirection(newMovePlayer);

                    timeAcc = 1.5f;
                    checkDirection(newMovePlayer);
                } else {
                    //spara LELLO SPARA
                    Movement movement = movementQ.frontToBack();
                    actVelocity.set(movement);
                    weapon.fire(newMovePlayer.scl(1f));
                    checkDirection(newMovePlayer);

                    timeAcc = 0f;
                }

            }
            //robba di Armando nel mio codice
            if (!body.getLinearVelocity().equals(actVelocity)) {
                body.setLinearVelocity(actVelocity);
            }
            return false;
        }
    }

    public BossState getBossState() {
        return bossState;
    }

    public void setBossState(BossState bossState) {
        this.bossState = bossState;
    }

    /**
     * Method instantiates the graphics of the Actor. Do not call directly.
     */
    @Override
    public void initGraphics() {

        //atlas = new TextureAtlas(Gdx.files.internal("texture/enemy/bosses/knight/knight_run/knight.atlas"));
        atlas = new TextureAtlas(Gdx.files.internal("texture/enemy/bosses/big_demon/big_demon.atlas"));

        //System.out.println(atlas.findRegions("run").size);
        movementAnimation = new Animation<TextureRegion>(0.1f, atlas.findRegions("run"), PlayMode.LOOP);
        //movementAnimation = new Animation<TextureRegion>(0.1f, atlas.findRegions("f_run"), PlayMode.LOOP);
    }

    @Override
    public void isHitBy(Bullet bullet) {
        life -= bullet.getDamage();
        fire(new HitEvent());
    }

    /**
     * @deprecated Please DO NOT use this method. It will be removed for sure in
     * the future.
     * @return
     */
    public boolean isHit() {

        world.setContactListener(new ContactListener() {
            @Override
            public void beginContact(Contact contact) {
                Fixture a = contact.getFixtureA();
                Fixture b = contact.getFixtureB();
                Gdx.app.log("Begin Contact", "");
                Gdx.app.log("User Data ", b.getBody().getUserData().toString());

                if (b.getBody().getUserData() == DemoBoss.this.getUserData()) {
                    life = life - (int) a.getDensity();
                    //or any other way to calculate damage

                } else {
                    life = life - (int) b.getDensity();
                    //or any other way to calculate damage
                }

                Gdx.app.log("Life", life.toString());

            }

            @Override
            public void endContact(Contact contact) {
                Gdx.app.log("End contact", "");
            }

            @Override
            public void preSolve(Contact arg0, Manifold arg1) {
            }

            @Override
            public void postSolve(Contact arg0, ContactImpulse arg1) {

            }
        });

        return true;

    }

    public Object getUserData() {
        return this.body.getUserData();
    }

    public void flipFrames(boolean x, boolean y) {
        for (TextureRegion frame : movementAnimation.getKeyFrames()) {
            frame.flip(x, y);
        }
    }

    /**
     * @deprecated @param animation
     */
    public void changeTextureRegion(Vector2 animation) {

        if (animation.x > 0) {

            textureRegion = new TextureRegion(regions, 0.5f, 0.5f, 1f, 1f);
            //flipFrames(true, false);

        } else if (animation.x < 0) {
            textureRegion = new TextureRegion(regions, 0f, 0f, 0.5f, 0.5f);
            //flipFrames(true, false);

        } else {
            if (animation.y < 0) {
                textureRegion = new TextureRegion(regions, 0.5f, 0f, 1f, 0.5f);

            } else if (animation.y > 0) {
                textureRegion = new TextureRegion(regions, 0f, 0.5f, 0.5f, 1f);
                //flipFrames(false, true);

            } else {
                textureRegion = new TextureRegion(regions, 0.5f, 0f, 1f, 0.5f);
            }
        }

    }

    public boolean isRight() {
        return !textureRegion.isFlipX();
    }

    public boolean isLeft() {
        return textureRegion.isFlipX();
    }

    public void checkDirection(Vector2 movement) {
        if ((movement.x > 0 && isLeft()) || (movement.x < 0 && isRight())) {
            flipFrames(true, false);

        } else if (movement.x == 0) {

        }
    }

    @Override
    public String getName() {
        return name;
    }
}
