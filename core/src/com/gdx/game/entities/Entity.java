package com.gdx.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.gdx.game.entities.classes.CharacterClass;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.RunnableAction;
import com.badlogic.gdx.utils.Disposable;
import com.gdx.game.GameStage;
import com.gdx.game.GdxGame;

/**
 * DIVIDI L'ENTITY BODY IN 2 BODY, UN COLLIDING BODY CHE SI OCCUPA DEL LATO
 * FISICO, ED UN SENSOR BODY CHE SI OCCUPA DEL LATO DI GESTIONE DELLE
 * COLLISIONI, VEDI DI FARLI LAVORARE INSIEME. UTILIZZA TRASFORM SUL SENSORE,
 * NON SUL CORPO DINAMICO!
 *
 * @author Armando
 */
public abstract class Entity extends Actor implements Disposable {

    protected World world;
    protected CharacterClass characterClass;
    protected Body body;
    protected TextureRegion textureRegion;
    protected Action defaultAction;

    public Entity(float worldWidth, float worldHeight, Vector2 initialPosition) {
        setWidth(worldWidth);
        setHeight(worldHeight);
        setPosition(initialPosition.x, initialPosition.y);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) { //Draw dice al batch cosa deve disegnare. Lo stage ogni volta che fai stage.draw chiama tutti i draw degli actors passandogli il batch in modo che possono contribire al batch e disegna tutto insieme
        batch.draw(textureRegion, getPosition().x - getWidth() / 2, getPosition().y - getWidth() / 2, getWidth(), getHeight());
    }

    protected abstract void initPhysics();

    protected abstract void initGraphics();

    // -------------- Physics:Velocity
    protected void setLinearVelocity(Vector2 velocity) {
        body.setLinearVelocity(velocity);
    }

    protected void setLinearVelocity(float x, float y) {
        body.setLinearVelocity(x, y);
    }

    protected Vector2 getLinearVelocity() {
        return body.getLinearVelocity();
    }

    /**
     * Returns the actual entity position.
     *
     * @return the actual entity position
     */
    public Vector2 getPosition() {
        return new Vector2(getX(), getY());
    }

    public void setPosition(Vector2 pos) {
        if (body != null) {
            body.setTransform(pos, 0);
        }
        setPosition(pos.x, pos.y);
    }

    public World getWorld() {
        return world;
    }

    @Override
    protected void setStage(Stage stage) {
        super.setStage(stage);
        if (stage instanceof GameStage) {
            GameStage s = (GameStage) stage;
            world = s.getWorld();
        } else {
            world = null;
        }
    }

    @Override
    protected void setParent(Group parent) {
        if (parent == null) {
            GdxGame.game.bodyToRemove.add(body);
            body = null;
        } else {
            clear();
            addAction(defaultAction);
            super.setParent(parent);
        }
    }

    @Override
    public void dispose() {
        defaultAction.reset();
        this.clear();
        Action removeActor = Actions.removeActor(this);
        if (getStage() != null) {
            
        getStage().addAction(removeActor);
        }
    }

    public void setBody(Body b){
        body = b;
    }

    @Override
    public void act(float delta) {
        if (body != null) {
            super.setPosition(body.getPosition().x, body.getPosition().y);
        }
        super.act(delta);
    }

}
