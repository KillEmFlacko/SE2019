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
public class Attacking extends BossState {

    public Attacking(State state) {
        super(state);
    }

    @Override
    public boolean onIdle() {

        return false;
    }

    @Override
    public boolean onMoving() {
        return false;
    }

    @Override
    public boolean onAttacking() {
        return true;
    }

    @Override
    public boolean onBlocking() {
        return false;
    }

    @Override
    public boolean onDying() {
        return false;
    }

    @Override
    public boolean onDead() {
        return false;
    }

    @Override
    public boolean onAttackMoving() {

        return false;
    }

}
