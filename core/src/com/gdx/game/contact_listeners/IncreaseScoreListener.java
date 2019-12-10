/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdx.game.contact_listeners;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.gdx.game.score.ScoreCounter;

/**
 *
 * @author salvatore
 */
public class IncreaseScoreListener extends ChangeListener{
    private final ScoreCounter score;
    public IncreaseScoreListener(ScoreCounter score){
        this.score=score;
    }
    
    @Override
    public void changed(ChangeEvent event, Actor actor) {
        score.increaseScore(1);
        System.out.println("Score Incrementato"+score.getScore());
    }
    
}
