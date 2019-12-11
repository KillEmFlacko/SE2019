package com.gdx.game.entities.bosses;

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
import com.badlogic.gdx.physics.box2d.World;
import com.gdx.game.GdxGame;
import com.gdx.game.contact_listeners.events.DeathEvent;
import com.gdx.game.contact_listeners.events.HitEvent;
import com.gdx.game.entities.Bullet;
import com.gdx.game.entities.Player;
import com.gdx.game.movements.MovementSet;
import java.util.Random;
import com.gdx.game.entities.*;
import com.gdx.game.factories.FilterFactory;
import com.gdx.game.factories.Weapon;
import com.gdx.game.movements.Movement;

/**
 *
 * @author ammanas
 */
public final class DemoBoss extends Boss {

    private Float timeAcc = 2f;
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

    private Movement prevMovement;

    public DemoBoss(String name, Integer life,  float width, float height, Vector2 position, MovementSet movementQ, Player player) {
        super(name, life, width, height, position);
        this.movementQ = movementQ;
        this.player = player;
        DemoBossBullet b = new DemoBossBullet(world, 4f/GdxGame.game.SCALE, this.player.getPosition(), 25, 10f);
        this.weapon = new Weapon(this, b, 1);

        //bossState = new IdleState(); TO ADD
        //this.movementQ = bossState.onIdle() TO ADD
//        initPhysics();
//        initGraphics();
        this.prevMovement = movementQ.peek();
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
        fixture.setUserData(body);
        shape.dispose();
        
    }
    //private int i = 0;

    @Override
    public void act(float delta) {
        if (body == null) {
            initGraphics();
            initPhysics();
        }
        setPosition(body.getPosition());
        if (super.life <= 0) {
            kill();
            return;
        }
        timeAcc += delta;
        stateTime += delta;

        textureRegion = movementAnimation.getKeyFrame(stateTime, true);

        Vector2 playerPosition = player.getPosition();
        Vector2 newMovePlayer = new Vector2(playerPosition.sub(this.getPosition()));

        if (timeAcc >= 2.0f) {
            Random r = new Random();

            if ((r.nextFloat() * 10) >= 6) {

                //System.out.println("Player position" + playerPosition);

                actVelocity.set(newMovePlayer.scl(1.3f));
                //checkDirection(newMovePlayer);
                
                timeAcc = 1.5f;
                //prevMovement = new Movement(newMovePlayer);
            } else {
                Movement movement = movementQ.frontToBack();
                //Gdx.app.log("V", movement.toString());
                actVelocity.set(movement);
                weapon.fire(newMovePlayer.scl(1f));
                //checkDirection(movement);
                
                timeAcc = 0f;
            }
            checkDirection(this.getLinearVelocity());

            prevMovement = new Movement(this.getLinearVelocity());
        }
        //robba di Armando nel mio codice
        if(!body.getLinearVelocity().equals(actVelocity)){
            body.setLinearVelocity(actVelocity);
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
    public void kill() {

//        Pe', non mi chiamare rompipalle, purtroppo i corpi 
//        possono essere distutti soltanto dopo il world.step()
        GdxGame.game.bodyToRemove.add(body);
//        this.world.destroyBody(body);
//
//        body.setUserData(null);
//        body = null;
        
        fire(new DeathEvent());
        this.getStage().getRoot().removeActor(this);
        //stop animation and remove body
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

    public void checkDirection(Vector2 movement) {
        if ((movement.x >= 0 && prevMovement.x >= 0) || (movement.x <= 0 && prevMovement.x <= 0)){
            return;
            
        }else{
            
            
            flipFrames(true, false);
        }
        
        /*
        if (movement.x > 0 && prevMovement.x < 0) {

            //textureRegion.flip(true, false);
            flipFrames(true, false);

        } else if (movement.x < 0 && prevMovement.x > 0) {

            //System.out.println("jee");
            //textureRegion.flip(true, false);
            flipFrames(true, false);

        } else if (movement.x == 0 && movement.y > 0) {
            //change texture region to y one by moving on the atlas
        } else if (movement.x == 0 && movement.y < 0) {
            //change texture region by moving on the atlas
        } else {
            //System.out.println("WHat?!");
            //since sprites are missing
            /*
            if (movement.x > movement.y) {
                flipFrames(true, false);
            } else {
                //nothing
            }
            }
        */

        
    }

    public String getName() {
        return name;
    }
}
