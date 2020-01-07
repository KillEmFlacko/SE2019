package com.gdx.game.levels;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.PolygonMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.gdx.game.GameStage;
import com.gdx.game.GdxGame;
import com.gdx.game.entities.Enemy;
import com.gdx.game.entities.Player;
import com.gdx.game.entities.classes.NorthernWizard;
import com.gdx.game.factories.FilterFactory;

/**
 * A single game level with his entities and map. A level is a Group, which
 * extends Actor, so it knows how to draw itself. That is, it knows how to draw
 * the map and how to draw the other entities. Also, it is a container for those
 * entities. It knows how to start and when it has ended.
 *
 * @author Davide & Armando
 */
public abstract class Level extends Group implements Disposable {

    protected Player player;
    protected World world;
    protected OrthogonalTiledMapRenderer mapRenderer;
    protected Body mapWalls;

    public Level(Player p) {
        this.player = p;
    }

    /**
     * If no player is passed we create a player of our own.
     * ??????????????????????????????????????????????????
     */
    public Level() {
        /////////// PLAYER ////////////
        float playerWorldWidth = 16 / GdxGame.SCALE;
        float playerWorldHeight = 28 / GdxGame.SCALE;
        player = new Player("uajono",  playerWorldWidth, playerWorldHeight, Vector2.Zero,new NorthernWizard());
        ///////////////////////////////
    }

    /**
     * Firstly, we set the player and the enemies initial positions. Here we
     * actually start the level, that is we add the actors to this Group in
     * order to start drawing them, also we start drawing the map.
     */
    public abstract void start();

    /**
     * Should do everything is needed to end the level and must fire the
     * EndLevel event.
     */
    public abstract void dispose();

    /**
     * Supposing that every tile is a square, returns the number of pixels of
     * its side.
     *
     * @return The number of pixels for each tile.
     */
    protected abstract float getTilePerMeter();

    /**
     * Returns the number of tiles for each meter.
     *
     * @return The number of tiles for each meter.
     */
    protected abstract float getPixelPerTile();

    public Player getPlayer() {
        return player;
    }

    ///////////// DA TOGLIERE ?????? /////////////
    public abstract TiledMap getMap();

    public abstract Array<Enemy> getEnemies();
    //////////////////////////////////////////////

    /**
     * Should listen for events from the actors of the concrete level. Every
     * level know when ends so must listen for the events which cause it to be
     * considered finished.
     */
    protected abstract class EndLevelListener extends ChangeListener {

    }

    /**
     * Every level is an actor, so he can fire events, particularly when the
     * level ends it fires and EndLevel event (ChangeEvent because we need the
     * target?)
     */
    public static class EndLevelEvent extends ChangeListener.ChangeEvent {
        private boolean win;
        
        public EndLevelEvent(boolean hasWon){
            win = hasWon;
        }
        
        public boolean hasWon(){
            return win;
        }
    }

    @Override
    protected void setStage(Stage stage) {
        super.setStage(stage);
        if (stage instanceof GameStage) {
            GameStage s = (GameStage) stage;
            world = s.getWorld();
        }
    }

    /**
     * Loads the objects in the layer staticObjectsLayerId, then creates a
     * single Static Body containing them as fixtures.
     *
     * @param staticObjectsLayerId the id of the object layer containing the
     * static objects to instantiate
     */
    protected void instantiateStaticObjects(String staticObjectsLayerId) {
        MapObjects walls = getMap().getLayers().get(staticObjectsLayerId).getObjects();

        BodyDef bdDef = new BodyDef();
        bdDef.type = BodyDef.BodyType.StaticBody;
        bdDef.position.set(0, 0);
        mapWalls = world.createBody(bdDef);
        for (MapObject wall : walls) {
            if (wall instanceof PolygonMapObject) {
                PolygonShape shape = getPolygon((PolygonMapObject) wall, getPixelPerTile() * getTilePerMeter());
                FixtureDef fxtDef = new FixtureDef();
                fxtDef.shape = shape;
                
                FilterFactory ff = new FilterFactory();
                ff.copyFilter(fxtDef.filter, ff.getStaticColliderFilter());

                mapWalls.createFixture(fxtDef);
                shape.dispose();
            }
        }
    }

    /**
     * Convenience method that casts a PolygonMapObject as a PolygonShape in
     * order to be utilized in a fixture.
     *
     * @param polygonObject the PolygonMapObject to convert
     * @param scaleFactor the scale factor in pixelPerMeter
     * @return the converted PolygonMapObject as a PolygonShape
     */
    protected static PolygonShape getPolygon(PolygonMapObject polygonObject, float scaleFactor) {
        PolygonShape polygon = new PolygonShape();
        float[] vertices = polygonObject.getPolygon().getTransformedVertices();

        float[] worldVertices = new float[vertices.length];

        for (int i = 0; i < vertices.length; ++i) {
            System.out.println(vertices[i]);
            worldVertices[i] = vertices[i] / (scaleFactor);
        }

        polygon.set(worldVertices);
        return polygon;
    }

    /**
     * Draws the actors and the map.
     *
     * @param batch
     * @param parentAlpha
     */
    @Override
    public void draw(Batch batch, float parentAlpha) {
        if (mapRenderer != null) {
            batch.end();
            mapRenderer.render();
            mapRenderer.setView((OrthographicCamera) getStage().getCamera());
            batch.begin();
        }
        super.draw(batch, parentAlpha);
    }
}
