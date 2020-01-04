package com.gdx.game.entities;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.gdx.game.actions.GameAction;
import com.gdx.game.errors.InvalidStageTypeError;

/**
 * DIVIDI L'ENTITY BODY IN 2 BODY, UN COLLIDING BODY CHE SI OCCUPA DEL LATO
 * FISICO, ED UN SENSOR BODY CHE SI OCCUPA DEL LATO DI GESTIONE DELLE
 * COLLISIONI, VEDI DI FARLI LAVORARE INSIEME. UTILIZZA TRASFORM SUL SENSORE,
 * NON SUL CORPO DINAMICO!
 *
 * @author Armando
 */
public class Entity extends Actor {

    private Body body;
    private TextureRegion textureRegion;
    private EntityDef entityDef;
    private Fixture mainFixture; 
    
    public Entity(EntityDef entityDef) {
        super();
        this.entityDef = entityDef;

        textureRegion = (TextureRegion) entityDef.getAnimations().get("idle").getKeyFrame(0f);
        addAction(new Init());
    }

    public Body getBody() {
        return body;
    }

    protected void setBody(Body body) {
        this.body = body;
    }

    public EntityDef getEntityDef() {
        return entityDef;
    }

    protected void setEntityDef(EntityDef entityDef) {
        this.entityDef = entityDef;
    }

    public TextureRegion getRegionToDraw() {
        return textureRegion;
    }

    protected void setRegionToDraw(TextureRegion textureRegion) {
        this.textureRegion = textureRegion;
    }

    /*
    Calls getStage() and cast the stage to WorldStage
     */
    @Override
    public WorldStage getStage() {
        return (WorldStage) super.getStage();
    }

    /*
    Sets the stage, raise an error if it's not a WorldStage
     */
    @Override
    public void setStage(Stage stage) {
        if (stage instanceof WorldStage) {
            super.setStage(stage);
        } else {
            throw new InvalidStageTypeError();
        }
    }

    @Override
    public void setWidth(float width) {
        super.setWidth(width);
    }

    @Override
    public void setHeight(float height) {
        super.setHeight(height);
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
        setPosition(pos.x, pos.y);
    }

    protected Body updateFixtures() {
        return null;
    }

    /* Defines a default implementation of draw
    where the textureRegion is drawn using
    the actor's parameters 
     */
    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(textureRegion, body.getPosition().x - getWidth() / 2, body.getPosition().y - getWidth() / 2, getWidth(), getHeight());
    }

    /* Utilize Action in order to remove the Actor
    from the Stage and to take it in a state that 
    allows the object to be reused
     */
    public void dispose() {
        for (Action a : this.getActions()){
            a.reset();
        }
        
        this.clear();

        Action removeActor = Actions.removeActor(this);
        if (getStage() != null) {
            getStage().addAction(removeActor);
        }

    }

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

    protected class Init extends GameAction {

        @Override
        public boolean act(float delta) {
            if (body == null) {
                body = getStage().getWorld().createBody(entityDef.getBodyDef());
                body.setUserData(this);
                Fixture f1 = body.createFixture(entityDef.getFixtureDefs().get("colliding"));
                Fixture f2 = body.createFixture(entityDef.getFixtureDefs().get("sensor"));

                f1.setUserData(body);
                f2.setUserData(body);
            }
            
            return true;
        }
    }

}
