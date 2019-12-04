/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdx.game.score;

import java.util.concurrent.atomic.AtomicLong;

/**
 *
 * @author salvatore
 */
public class CurrentAtomicScore {
    private final AtomicLong score;
    
    public CurrentAtomicScore(){
        this.score = new AtomicLong(0);
    }

    public long getScore() {
        return this.score.get();
    }
    
    public void IncreaseScore(long dscore){
        this.score.addAndGet(dscore);
    }
    
    
    
}
