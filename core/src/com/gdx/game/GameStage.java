package com.gdx.game;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 *
 * @author Armando
 */
public class GameStage extends Stage {

    private World world;

    public GameStage() {
        super();
        World w = new World(Vector2.Zero, true);
        this.world = w;
    }

    public GameStage(World world) {
        super();
        this.world = world;
    }

    public GameStage(World world, Viewport viewport) {
        super(viewport);
        this.world = world;
    }

    public GameStage(World world, Viewport viewport, Batch batch) {
        super(viewport, batch);
        this.world = world;
    }

    public GameStage(Viewport viewport) {
        super(viewport);
        World w = new World(Vector2.Zero, true);
        this.world = w;
    }

    public GameStage(Viewport viewport, Batch batch) {
        super(viewport, batch);
        World w = new World(Vector2.Zero, true);
        this.world = w;
    }

    public World getWorld() {
        return world;
    }

    @Override
    public void dispose() {
        super.dispose();
        world.dispose();
    }
}
