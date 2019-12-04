/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdx.game.movements;

import com.badlogic.gdx.math.Vector2;

/**
 *
 * @author ammanas
 */
public class YMovement extends Movement{

    private float ySpeed;
    private Vector2 v;

    public YMovement(float ySpeed) {
        super(0, ySpeed);
        this.ySpeed = ySpeed;
        v = new Vector2(0, ySpeed);
    }



    public Vector2 getV() {
        return v;
    }
    
    
    
}
