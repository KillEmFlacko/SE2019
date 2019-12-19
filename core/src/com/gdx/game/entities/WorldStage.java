/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdx.game.entities;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
/**
 *
 * @author natal
 */
public class WorldStage extends Stage {
    
    public World world;
    public int velocityIterations;
    public int positionIterations;
    
    public World getWorld(){
        return world;
    }
    
    public void setIterations(int vel, int pos){
        this.positionIterations = pos;
        this.velocityIterations = vel;
    }
    
    public void dispose(){
        
    }
    
    public void act() {
        
    }
}
