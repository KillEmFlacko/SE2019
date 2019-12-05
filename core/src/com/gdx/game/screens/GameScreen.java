package com.gdx.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputEventQueue;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.gdx.game.entities.DemoBoss;

/**
 *
 * @author Armando
 */
public class GameScreen implements Screen {

    private Stage stage;
    private Game game;
    private World world;
    private Body bdPlayer;
    private Box2DDebugRenderer debugRenderer;
    private InputEventQueue inputEQ;

    public GameScreen(Game aGame) {
        this.game = aGame;
        stage = new Stage();
        world = new World(Vector2.Zero, true);
        debugRenderer = new Box2DDebugRenderer();
        inputEQ = new InputEventQueue(stage);

        initPhy();
        DemoBoss db = new DemoBoss("Nameless King", 30, this.world, 128, 128, new Vector2(Gdx.graphics.getWidth() * 2 / 3, Gdx.graphics.getHeight() * 2/3));
        stage.addActor(db);
        
        
        
        
    }

    private void initPhy() {
        BodyDef bdDef = new BodyDef();
        bdDef.type = BodyDef.BodyType.DynamicBody;
        bdDef.position.set(300, 400);
        bdPlayer = world.createBody(bdDef);

        CircleShape shape = new CircleShape();
        shape.setRadius(60f);
        
        FixtureDef fixDef = new FixtureDef();
        fixDef.shape = shape;
        fixDef.density = 4f;

        bdPlayer.createFixture(fixDef);
        shape.dispose();
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(inputEQ);
    }

    @Override
    public void render(float f) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
        debugRenderer.render(world, stage.getCamera().combined);
        world.step(1 / 60f, 6, 2);
    }

    @Override
    public void dispose() {
        stage.dispose();
    }

    @Override
    public void resize(int i, int i1) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }
}
