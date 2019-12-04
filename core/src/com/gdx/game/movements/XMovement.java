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
public class XMovement extends Movement{

    private float xSpeed;
    private Vector2 v;

    public XMovement(float xSpeed) {
        super(xSpeed, 0);        
        this.xSpeed = xSpeed;
        v = new Vector2(xSpeed,0);
        
    }

    public Vector2 getV() {
        
        return v;
        
    }
    
    public float Angle(){
        return v.angle();
    }
    
    
    
    
    
    
    
    
    
    
    
    
}
