/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdx.game.entities;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.RunnableAction;
import com.badlogic.gdx.utils.Disposable;
import com.gdx.game.GdxGame;
import com.gdx.game.contact_listeners.events.DeathEvent;

/**
 *
 * @author ammanas
 */
public abstract class MortalEntity extends Entity {

    protected String name;
    protected Integer life;

    public MortalEntity(String name, Integer life, float width, float height, Vector2 position) {
        super( width, height, position);
        this.name = name;
        setName(name);
        this.life = life;
    }
    
    /**
     * Correctly manage the damage due to a bullet
     * @param bullet 
     */
    public abstract void isHitBy(Bullet bullet);
    
    /**
     * Dispose all the involved bodies for deletion, that is adding their
     * references to the GdxGame.game.bodiesToRemove structure
     */
    public void kill() {
        dispose();
        fire(new DeathEvent());
    }
    
    public Integer getLife() {
        return life;
    }

    float getBoostSpellMultiplier() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    float getDamageSpellMultiplier() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    float getDefenseSpelMultiplier() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
