package com.gdx.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputEventQueue;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.gdx.game.GdxGame;
import com.gdx.game.entities.Player;
import com.gdx.game.entities.bosses.DemoBoss;
import com.gdx.game.movements.MovementSetFactory;

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
        stage = new Stage(GdxGame.vp);
        world = new World(Vector2.Zero, true);
        debugRenderer = new Box2DDebugRenderer();
        player = new Player(world, 50, 50, new Vector2(300, 400));
        stage.addActor(player);

        MovementSetFactory mvsf = MovementSetFactory.instanceOf();
        Vector2 v = new Vector2(Gdx.graphics.getWidth() * 2 / 3, Gdx.graphics.getHeight() * 2/3);
        //v is the player position to substitute when merge is complete
        DemoBoss db = new DemoBoss("Nameless King", 30, this.world, 100, 100, v, mvsf.build("Fast", "Square" ,false, v, 3));
        stage.addActor(db);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
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
