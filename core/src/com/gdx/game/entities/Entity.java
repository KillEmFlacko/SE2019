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
    protected Vector2 position;

    public Entity(World world, float width, float height, Vector2 position) {
        this.world=world;
        this.width=width;
        this.height=height;
        this.position=position;
    }

    protected abstract void initPhysics();
    protected abstract void initGraphics();
}
