/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdx.game.entities;

/**
 *
 * @author natal
 */
public interface Skill {
    final float BASE_COOLDOWN = 1f;
    
    public void getBaseCooldown();
    
    public void setCooldown ();
    
    public void getCooldown();
    
}
