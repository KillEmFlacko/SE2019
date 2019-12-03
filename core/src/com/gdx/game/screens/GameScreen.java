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
import com.gdx.game.entities.Player;

/**
 *
 * @author Armando
 */
public class GameScreen implements Screen {

    private Stage stage;
    private Game game;
    private World world;
    private Player player;
    private Box2DDebugRenderer debugRenderer;
    private InputEventQueue inputEQ;

    public GameScreen(Game aGame) {
        this.game = aGame;
        stage = new Stage();
        world = new World(Vector2.Zero, true);
        debugRenderer = new Box2DDebugRenderer();
        inputEQ = new InputEventQueue(stage);
        Player player = new Player(world, 50, 50, new Vector2(300, 400));
        stage.addActor(player);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(inputEQ);
    }

    @Override
    public void render(float f) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
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
