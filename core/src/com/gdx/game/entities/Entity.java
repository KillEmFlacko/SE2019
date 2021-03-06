package com.gdx.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.gdx.game.entities.classes.CharacterClass;

/**
 * DIVIDI L'ENTITY BODY IN 2 BODY, UN COLLIDING BODY CHE SI OCCUPA DEL LATO FISICO, ED UN SENSOR
 * BODY CHE SI OCCUPA DEL LATO DI GESTIONE DELLE COLLISIONI, VEDI DI FARLI LAVORARE INSIEME.
 * UTILIZZA TRASFORM SUL SENSORE, NON SUL CORPO DINAMICO!
 * @author Armando
 */
public abstract class Entity extends Actor {

    protected World world;
    protected CharacterClass characterClass;
    protected Body body;
    protected TextureRegion textureRegion;

    public Entity(World world, float worldWidth, float worldHeight, Vector2 initialPosition) {
        this.world = world;
        setWidth(worldWidth);
        setHeight(worldHeight);
        setPosition(initialPosition.x, initialPosition.y);
    }
    @Override
    public void draw(Batch batch, float parentAlpha) { //Draw dice al batch cosa deve disegnare. Lo stage ogni volta che fai stage.draw chiama tutti i draw degli actors passandogli il batch in modo che possono contribire al batch e disegna tutto insieme
        batch.draw(textureRegion, body.getPosition().x - getWidth() / 2, body.getPosition().y - getWidth() / 2, getWidth(), getHeight());
    }

    protected abstract void initPhysics();

    protected abstract void initGraphics();

    // -------------- Physics:Velocity
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
        return new Vector2(getX(), getY());
    }
    
    public void setPosition(Vector2 pos){
        setPosition(pos.x, pos.y);
    }

    public World getWorld() {
        return world;
    }
 
    
}
