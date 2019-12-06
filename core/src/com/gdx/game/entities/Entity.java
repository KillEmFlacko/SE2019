package com.gdx.game.entities;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 *
 * @author Armando
 */
public abstract class Entity extends Actor {

    protected World world;
    protected Body body;
    protected TextureRegion textureRegion;
    protected float width, height;
    protected Vector2 initalPosition;

    public Entity(World world, float width, float height, Vector2 initialPosition) {
        this.world = world;
        this.width = width;
        this.height = height;
        this.initalPosition = initialPosition;
    }
    @Override
    public void draw(Batch batch, float parentAlpha) { //Draw dice al batch cosa deve disegnare. Lo stage ogni volta che fai stage.draw chiama tutti i draw degli actors passandogli il batch in modo che possono contribire al batch e disegna tutto insieme
        batch.draw(textureRegion, body.getPosition().x - width / 2, body.getPosition().y - width / 2, width, height);
    }

    protected abstract void initPhysics();

    protected abstract void initGraphics();

    // --------------------- Physics:Velocity
    protected void setLinearVelocity(Vector2 velocity){
        body.setLinearVelocity(velocity);
    }

    protected void setLinearVelocity(float x, float y){
        body.setLinearVelocity(x, y);
    }

    protected Vector2 getLinearVelocity(){
        return body.getLinearVelocity();
    }
    
    /**
     * Returns the actual entity position.
     * @return the actual entity position
     */
    public Vector2 getPosition(){
        return body.getPosition();
    }
}
