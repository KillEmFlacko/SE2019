/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdx.game.movements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 *
 * @author ammanas
 */
public class MovementSet extends LinkedList{
    
    
    private Queue<Movement> moveSet;

    public Queue<Movement> getMoveSet() {
        return moveSet;
    }
    
    
    public MovementSet() {
        this.moveSet = new LinkedList<>();
    }
    
    
    
    public void add(Movement m){
        //moveSet.add(m);
        moveSet.offer(m);
    }
    
    @Override
    public Movement remove(){
        return moveSet.remove();
    }
    
    public Movement frontToBack(){
        Movement m = moveSet.remove();
        moveSet.add(m);
        Gdx.app.log("Movement", m.toString());
        return m;
    }
    
   
    
}
