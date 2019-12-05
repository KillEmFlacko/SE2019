
package com.gdx.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

/**
 *
 * @author Giovanni
 */
public class Player extends Entity {

    private TextureAtlas atlas;
    private Animation<TextureAtlas.AtlasRegion> runAnimation;
    private Animation<TextureAtlas.AtlasRegion> idleAnimation;
    private float stateTime = 0f;
    
    

    public Player(World world, float width, float height, Vector2 position) {
        super(world, width, height, position);
        initPhysics();
        initGraphics();

    }

    @Override
    protected void initPhysics() {
        BodyDef bdDef = new BodyDef();
        bdDef.type = BodyDef.BodyType.DynamicBody;
        bdDef.position.set(300, 400);
        body = world.createBody(bdDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width / 2, height / 2);

        FixtureDef fixDef = new FixtureDef();
        fixDef.shape = shape;
        fixDef.density = 0.5f;

        body.createFixture(fixDef);
        shape.dispose();
    }

    @Override
    protected void initGraphics() {
        atlas = new TextureAtlas(Gdx.files.internal("texture/player/knight.atlas"));
        idleAnimation = new Animation(0.15f, atlas.findRegions("m_idle"), Animation.PlayMode.LOOP);
        runAnimation = new Animation(0.10f, atlas.findRegions("m_run"), Animation.PlayMode.LOOP);

    }

    //ci servono i metodi ACT e DRAW
    //richiama nel costruttore i metodi initPhysics initGraphics
    @Override
    public void act(float delta) {
        stateTime+= delta;
        super.act(delta); //To change body of generated methods, choose Tools | Templates.

        Vector2 velocity = new Vector2(0, 0);
        if (Gdx.input.isKeyPressed(Keys.W)) {
            velocity.add(0, 100);
        }
        if (Gdx.input.isKeyPressed(Keys.S)) {
            velocity.add(0, -100);
        }
        if (Gdx.input.isKeyPressed(Keys.A)) {
            velocity.add(-100, 0);
        }
        if (Gdx.input.isKeyPressed(Keys.D)) {
            velocity.add(100, 0);
        }
        if (!body.getLinearVelocity().equals(velocity)) {
            body.setLinearVelocity(velocity);
        }

       if(!body.getLinearVelocity().equals(new Vector2(0,0))){
           textureRegion = runAnimation.getKeyFrame(stateTime);
       }
       else{
           textureRegion = idleAnimation.getKeyFrame(stateTime);
       }
       
       if (textureRegion.isFlipX()){
            textureRegion.flip(true, false);
       }
       
       if((Gdx.input.isKeyPressed(Keys.A) || Gdx.input.isKeyPressed(Keys.S)) && !(Gdx.input.isKeyPressed(Keys.D))){
           textureRegion.flip(true, false);
       }
       
    }

    @Override
    public void draw(Batch batch, float parentAlpha) { //Draw dice al batch cosa deve disegnare. Lo stage ogni volta che fai stage.draw chiama tutti i draw degli actors passandogli il batch in modo che possono contribire al batch e disegna tutto insieme

        batch.draw(textureRegion, body.getPosition().x - width / 2, body.getPosition().y - height / 2, width, height);

    }

}
