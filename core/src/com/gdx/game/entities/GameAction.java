/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdx.game.entities;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.gdx.game.exceptions.NotAnEntityException;

/**
 *
 * @author ammanas
 */
public class GameAction extends Action {

    @Override
    public boolean act(float delta) {
        return true;
    }
    
    /**
     * NOTE: if target is not an Entity then nothing is set.
     * Downcast might be an hazard.
     * 
     * Remember that, if not stated otherwise, target is the same as Actor.
     * @param target 
     */

    @Override
    public void setTarget(Actor target) {
        if (target instanceof Entity) {
            //super.target = target;
            super.setTarget(target);
        } else {

            //l'eccezione non può essere lanciata perchè modifica la firma
            //quello che posso fare è castare a Entity. Posso ? Chiedere ad Armando
        }
    }
    /**
     * NOTE: if actor is not an Entity then nothing is set.
     * Downcast might be an hazard.
     * @param actor 
     */
    @Override
    public void setActor(Actor actor) {
        if (actor instanceof Entity) {
            //super.actor = actor;
            super.setActor(actor);
        } else {

            //l'eccezione non può essere lanciata perchè modifica la firma
            //quello che posso fare è castare a Entity. Posso ? Chiedere ad Armando
        }
    }

    public Entity getEntity() {
        return (Entity) super.actor;
    }

    public Entity getTargetEntity() {

        return (Entity) super.target;
    }

}
