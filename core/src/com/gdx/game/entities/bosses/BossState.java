/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdx.game.entities.bosses;

/**
 *
 * @author ammanas
 */
public abstract class BossState {

    private State bossState;

    public BossState(State state) {
        this.bossState = state;
    }

    public abstract boolean onIdle();

    public abstract boolean onMoving();

    public abstract boolean onAttacking();

    public abstract boolean onBlocking();

    public abstract boolean onDying();
    
    public abstract boolean onDead();
    
    public abstract boolean onAttackMoving();

}
