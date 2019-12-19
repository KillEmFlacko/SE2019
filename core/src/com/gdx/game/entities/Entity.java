package com.gdx.game.entities;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

/**
 * DIVIDI L'ENTITY BODY IN 2 BODY, UN COLLIDING BODY CHE SI OCCUPA DEL LATO
 * FISICO, ED UN SENSOR BODY CHE SI OCCUPA DEL LATO DI GESTIONE DELLE
 * COLLISIONI, VEDI DI FARLI LAVORARE INSIEME. UTILIZZA TRASFORM SUL SENSORE,
 * NON SUL CORPO DINAMICO!
 *
 * @author Armando
 */
public class Entity extends Actor {

    // ------------- OLD PROPERTIES ---------------
//    protected World world;
//    protected CharacterClass characterClass;
//    protected Body body;
//    protected TextureRegion textureRegion;
    // --------------------------------------------
    
    // ++++++++++++++++ REFACTOR ++++++++++++++++++
    private Body body;
    private TextureRegion textureRegion;
    private EntityDef entityDef;
    private Fixture mainFixture;
    // ++++++++++++++++++++++++++++++++++++++++++++

    // ------------ OLD CONSTRUCTOR --------------- 
/*    public Entity(World world, float worldWidth, float worldHeight, Vector2 initialPosition) {
        this.world = world;
        setWidth(worldWidth);
        setHeight(worldHeight);
        setPosition(initialPosition.x, initialPosition.y);
    }
     */
    // --------------------------------------------
    
    // ++++++++++++++++ REFACTOR ++++++++++++++++++
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
    public WorldStage getWorldStage() {
        return null;
    }

    /*
    Sets the stage, raise an error if it's not a WorldStage
     */
    @Override
    public void setStage(Stage stage) {

    }

    @Override
    public void setWidth(float width) {

    }

    @Override
    public void setHeight(float height) {

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

    protected Body updateFistures() {
        return null;
    }

    /* Defines a default implementation of draw
    where the textureRegion is drawn using
    the actor's parameters 
     */
    public void draw() {

    }

    /* Utilize Action in order to remove the Actor
    from the Stage and to take it in a state that 
    allows the object to be reused
     */
    public void dispose() {

    }

    // ++++++++++++++++++++++++++++++++++++++++++++
    
    // ------------ OLD METHOD --------------------
//    @Override
//    public void draw(Batch batch, float parentAlpha) { //Draw dice al batch cosa deve disegnare. Lo stage ogni volta che fai stage.draw chiama tutti i draw degli actors passandogli il batch in modo che possono contribire al batch e disegna tutto insieme
//        batch.draw(textureRegion, body.getPosition().x - getWidth() / 2, body.getPosition().y - getWidth() / 2, getWidth(), getHeight());
//    }
    // --------------------------------------------
    
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

}
