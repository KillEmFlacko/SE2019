/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdx.game.entities;

/**
 *
 * @author ammanas
 */
public abstract class MortalEntity extends Entity {

    private Stats stats;
    private int hp;

    public MortalEntity(Stats stats, EntityDef entityDef) {
        super(entityDef);
        this.stats = stats;
    }
    
    public abstract void kill();

    public void isHitBy(Bullet b){
        hp -= b.getDamage();
    }

    public int getHP(){
        return hp;
    }

    private void setHp(int hp) {
        this.hp = hp;
    }
    
}
