/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdx.game.score;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import java.util.concurrent.atomic.AtomicLong;

/**
 * This class represents a score associated to an instance of a game. It is
 * possible to increment (or decrement) atomically the score, deeming this
 * class thread-safe. 
 *
 * @author Salvatore Gravina
 */
public class ScoreCounter extends ChangeListener {
    private final AtomicLong score;
    
    public ScoreCounter(){
        this.score = new AtomicLong(0);
    }
    
    /**
     * Returns the score value stored in the object. 
     * 
     * @return the score value.
     */
    public long getScore() {
        return this.score.get();
    }
    
    /**
     * Atomically increments the score by <code>dscore</code>.
     * 
     * @param dscore the value to be added to the score.
     */
    public void increaseScore(long dscore){
        this.score.addAndGet(dscore);
    }

    @Override
    public void changed(ChangeEvent event, Actor actor) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
