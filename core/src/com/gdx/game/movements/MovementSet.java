package com.gdx.game.movements;

import com.badlogic.gdx.Gdx;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author ammanas
 */
public class MovementSet {

    private Queue<Movement> moveSet;

    public Queue<Movement> getMoveSet() {
        return moveSet;
    }

    public MovementSet() {
        this.moveSet = new LinkedList<>();

    }

    public void add(Movement m) {
        //moveSet.add(m);
        moveSet.offer(m);
    }

    public Movement remove() {
        return moveSet.remove();
    }

    public Movement frontToBack() {
        Movement m = moveSet.remove();
        moveSet.add(m);
        Gdx.app.log("Movement", m.toString());
        return m;
    }

    public Movement peek() {
        return moveSet.peek();
    }

}
