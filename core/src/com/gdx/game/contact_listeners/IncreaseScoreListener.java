/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdx.game.contact_listeners;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.gdx.game.contact_listeners.events.HitEvent;
import com.gdx.game.entities.Player;
import com.gdx.game.score.ScoreCounter;

/**
 *
 * @author salvatore
 */
public class IncreaseScoreListener extends ChangeListener {

    private final ScoreCounter score;
    private long lastHitTime;
    private int comboMultiplier;
    private static final long RESET_TIME = 1500;
    private static final long BASE_INCR = 10;

    public IncreaseScoreListener(ScoreCounter score) {
        this.score = score;
        this.comboMultiplier = 1;
        this.lastHitTime = System.currentTimeMillis();
    }

    @Override
    public void changed(ChangeEvent event, Actor actor) {
        // Se l'evento non è un HitEvent, ignora l'evento
        if (!(event instanceof HitEvent)) {
            return;
        }

        HitEvent hit = (HitEvent) event;
        
        // Se il player le ha prese, resettiamo il comboMultiplier
        if (hit.entity instanceof Player) {
            resetCombo();
            return;
        }
        
        // Utilizziamo il tempo per resettare il comboMultiplier
        long currentHitTime = System.currentTimeMillis();

        // Se il tempo passato dall'ultimo colpo centrato è sotto soglia
        // aumentiamo il combo Multiplier, altrimenti va resettato.
        if (currentHitTime - lastHitTime < RESET_TIME)
            increaseCombo();
        else 
            resetCombo();
        
        // Incrementiamo lo score e lo stampiamo sulla console
        long delta = BASE_INCR * comboMultiplier;
        score.increaseScore(delta);
        lastHitTime = currentHitTime;
        System.out.println("Score incrementato di " + delta + " - Score attuale: " + score.getScore());

    }

    public int getComboMultiplier() {
        return comboMultiplier;
    }

    private void resetCombo() {
        System.out.println("Combo Reset! :-(");
        comboMultiplier = 1;
    }

    private void increaseCombo() {
        if (comboMultiplier < 5) {
            comboMultiplier++;
            System.out.println("Combo: " + comboMultiplier);
        }
    }

}
