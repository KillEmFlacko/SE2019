/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdx.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.gdx.game.movements.MovementSetFactory;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.gdx.game.movements.*;

/**
 *
 * @author ammanas
 */
public final class DemoBoss extends Boss {

    private final static int FRAME_ROWS = 1;
    private final static int FRAME_COLUMS = 4;
    private Float timeAcc = 2f;
    //per farlo muovere subito senza dover istanziare un movimento
    private float stateTime = 0f;
    private Texture regions;
    private Animation<TextureRegion> movementAnimation;
    private TextureAtlas atlas;

    //private Queue<Vector2> animationQ;
    private MovementSet movementQ;

    //THIS CLASS ASSUMES SPRITES ARE FACING RIGHT WHEN GIVEN TO IT
    public DemoBoss(String name, Integer life, World world, float width, float height, Vector2 position) {
        super(name, life, world, width, height, position);

        initPhysics();
        initGraphics();
        //Movement first = movementQ.frontToBack();
        //DemoBoss.this.body.setLinearVelocity(first);

    }

    /**
     * Method initializes the Actor physics. Do not call directly.
     */
    @Override
    protected final void initPhysics() {
        //super.initPhysics(); //To change body of generated methods, choose Tools | Templates.

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.KinematicBody;
        //bodyDef.position.set(Gdx.graphics.getWidth() * 2 / 3, Gdx.graphics.getHeight() * 2 / 3);
        bodyDef.position.set(this.position);

        this.body = this.world.createBody(bodyDef);
        this.body.setUserData("DemoBoss-v1");
        CircleShape shape = new CircleShape();
        shape.setRadius(40f);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 5f;

        Fixture fixture = body.createFixture(fixtureDef);
        fixture.setUserData(this);
        shape.dispose();

        MovementSetFactory mvsf = MovementSetFactory.instanceOf();
        System.out.println(this.getX());
        movementQ = mvsf.build("Fast", "StraightLine", false, false, this.body.getPosition());

    }
    //private int i = 0;

    @Override
    public void act(float delta) {
        if (super.life == 0) {
            kill();
            return;
        }
        timeAcc += delta;

        textureRegion = movementAnimation.getKeyFrame(stateTime, true);
        
        stateTime += delta;

        if (timeAcc >= 2.0f) {
            
            //life = 0;

            Vector2 movement = movementQ.frontToBack();
            //changeTextureRegion(movement);

            Gdx.app.log("V", movement.toString());
            DemoBoss.this.body.setLinearVelocity(movement);
            checkDirection(movement);

            timeAcc = 0f;

        }

    }

    /**
     * Method instantiates the graphics of the Actor. Do not call directly.
     */
    @Override
    protected void initGraphics() {
        /*
        Note that first fraem must be the one facing the first direction
         */
        //regions = new Texture(Gdx.files.internal("texture/enemy/bosses/test_pack_regions.png"));
        //textureRegion = new TextureRegion(regions, 0.5f, 0f, 1f, 0.5f);

        //atlas = new TextureAtlas(Gdx.files.internal("texture/enemy/bosses/knight/knight_run/knight.atlas"));
        atlas = new TextureAtlas(Gdx.files.internal("texture/enemy/bosses/monster_run/monster_run.atlas"));
        /*
        Next step is to have everything in an Atlas and have an Atlas Region to split
         */

        //regions = new Texture(Gdx.files.internal("texture/enemy/bosses/knight/knight walk animation.png"));
        /*
        regions = new Texture(Gdx.files.internal("texture/enemy/bosses/monster_run/monster_run.png"));
        TextureRegion tex[][] = TextureRegion.split(regions, regions.getWidth() / FRAME_COLUMS, regions.getHeight() / FRAME_ROWS);
        TextureRegion texArray[] = new TextureRegion[FRAME_COLUMS * FRAME_ROWS];
        System.out.println(tex.length);

        int index = 0;
        //transpose the vector : we have 1 colums and 8 rows right now
        for (int i = 0; i < FRAME_ROWS; i++) {
            for (int j = 0; j < FRAME_COLUMS; j++) {
                texArray[index++] = tex[i][j];
            }
        }
        
        
        movementAnimation = new Animation<>(0.1f, texArray);
         */
        System.out.println(atlas.findRegions("run").size);
        movementAnimation = new Animation<TextureRegion>(0.3f, atlas.findRegions("big_demon"), PlayMode.LOOP);

        //movementAnimation = new Animation<TextureRegion>(0.1f, atlas.findRegions("f_run"), PlayMode.LOOP);
    }

    public void kill() {

        this.world.destroyBody(body);

        body.setUserData(null);
        body = null;
        this.getStage().getRoot().removeActor(this);

        //stop animation and remove body
    }

    public void isHitBy(Bullet bullet) {
        life -= bullet.getDamage();
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
     * @deprecated 
     * @param animation 
     */

    public void changeTextureRegion(Vector2 animation) {

        if (animation.x > 0) {

            //ANIMATION STARTS ALREADY ORIENTED TOWARD THIS SO WHAT DO I DO
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
        if (movement.x > 0 && movement.y == 0) {

            //textureRegion.flip(true, false);
            flipFrames(true, false);

        } else if (movement.x < 0 && movement.y == 0) {

            //System.out.println("jee");
            //textureRegion.flip(true, false);
            flipFrames(true, false);


        } else if (movement.x == 0 && movement.y > 0) {
            //change texture region to y one by moving on the atlas
        } else if (movement.x == 0 && movement.y < 0) {
            //change texture region by moving on the atlas
        } else {
            System.out.println("WHat?!");
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {

        batch.draw(textureRegion, body.getPosition().x - width / 2, body.getPosition().y - height / 2, width, height);

        //batch.draw(textureRegion, body.getPosition().x - width / 2, body.getPosition().y - height / 2, width / 2, height / 2, width, height, 1f, 1f,getDirection(body.getLinearVelocity()));
    }

    public String getName() {
        return name;
    }

    public Integer getLife() {
        return life;
    }

}
