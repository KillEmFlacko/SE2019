package com.gdx.game.movements;

import com.badlogic.gdx.math.Vector2;

/**
 *
 * @author ammanas
 */
public class YMovement extends Movement {

    public YMovement(float ySpeed) {
        super(0, ySpeed);
    }

    public YMovement(float ySpeed, String label) {
        super(0, ySpeed, label);
    }

    public Vector2 getV() {
        return v;
    }

}
