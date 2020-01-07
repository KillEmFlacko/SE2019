package com.gdx.game.movements;

import com.badlogic.gdx.math.Vector2;

/**
 *
 * @author ammanas
 */
public class XMovement extends Movement{

    
    public XMovement(float xSpeed) {
        super(xSpeed, 0);
        
    }

    public XMovement(float xSpeed, String label) {
        super(xSpeed, 0, label);
    }
    
    

    public Vector2 getV() {
        
        return v;
        
    }
    
    public float Angle(){
        return v.angle();
    }
    
    
    
    
    
    
    
    
    
    
    
    
}
