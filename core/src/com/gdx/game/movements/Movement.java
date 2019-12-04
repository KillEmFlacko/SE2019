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
public class Movement extends Vector2{
    
    private float xSpeed;
    private float ySpeed;
    private Vector2 v;
    

    public Movement(float xSpeed, float ySpeed) {
        super(xSpeed, ySpeed);
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
        this.v = new Vector2(xSpeed, ySpeed);
        
    }

    public Vector2 getV() {
        return v;
    }
    
    


    
    
    
}