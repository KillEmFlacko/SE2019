/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdx.game.contact_listeners.events;

import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.gdx.game.entities.MortalEntity;

/**
 *
 * @author salvatore
 */
public class HitEvent extends ChangeListener.ChangeEvent{
    
    public MortalEntity entity;
    
    public HitEvent(MortalEntity entity){
        this.entity = entity;
    }
}
