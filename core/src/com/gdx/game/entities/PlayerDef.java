/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdx.game.entities;

/**
 *
 * @author david
 */
public interface PlayerDef extends MortalEntityDef {

    public float getStrength();

    public float getSpeed();

    public float getAttackRate();

    public float getBulletSpeed();

}
