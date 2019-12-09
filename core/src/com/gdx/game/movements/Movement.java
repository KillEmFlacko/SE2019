package com.gdx.game.movements;

import com.badlogic.gdx.math.Vector2;

/**
 *
 * @author ammanas
 *
 */
//Decorator pattern
public class Movement extends Vector2 {

    protected float xSpeed;
    protected float ySpeed;
    protected Vector2 v;
    protected String label;

    public Movement(float xSpeed, float ySpeed) {
        super(xSpeed, ySpeed);
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
        this.v = new Vector2(xSpeed, ySpeed);

    }

    public Movement(float xSpeed, float ySpeed, String label) {
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
        this.label = label;
        this.v = new Vector2(xSpeed, ySpeed);
    }

    public Movement(Vector2 v, String label) {
        this.v = v;
        this.xSpeed = v.x;
        this.ySpeed = v.y;
        this.label = label;
    }

    public Movement(Vector2 v) {
        this.v = v;
        this.xSpeed = v.x;
        this.ySpeed = v.y;
    }

    public Vector2 getV() {
        return v;
    }

}
