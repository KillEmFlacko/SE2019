package com.gdx.game.entities;

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

    protected abstract void initPhysics();

    protected abstract void initGraphics();

    // --------------------- Physics:Velocity
    protected abstract void setLinearVelocity(Vector2 velocity);

    protected abstract void setLinearVelocity(float x, float y);

    protected abstract Vector2 getLinearVelocity();
    
    /**
     * Returns the actual entity position.
     * @return the actual entity position
     */
    public abstract Vector2 getPosition();
}
