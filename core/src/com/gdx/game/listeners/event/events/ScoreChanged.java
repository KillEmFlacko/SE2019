package com.gdx.game.listeners.event.events;

import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

/**
 *
 * @author Armando
 */
public class ScoreChanged extends ChangeListener.ChangeEvent{
    
    private final long score;
    
    public ScoreChanged(long newScore){
        score = newScore;
    }
    
    public long getScore(){
        return score;
    }  
}
