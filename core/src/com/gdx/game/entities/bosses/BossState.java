/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdx.game.entities.bosses;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.gdx.game.entities.Boss;
/**
 *
 * @author ammanas
 */
public abstract class BossState {

    private State bossState;
    private TextureAtlas atlas;

    public BossState(State state, TextureAtlas atlas) {
        this.bossState = state;
        this.atlas = atlas;
    }
    
    /**
     * State handle method
     * @param keyword What to find in the atlas
     * @return Animation if the called method is the correct handle for the current state,
     * otherwise null
     */
    public abstract Animation onIdle(Boss boss,String keyword);
    //this methods also set the state to the correspondign one
    public abstract Animation onMoving(Boss boss,String keyword);

    public abstract Animation onAttacking(Boss boss,String keyword);

    public abstract Animation onBlocking(Boss boss,String keyword);

    public abstract Animation onDying(Boss boss,String keyword);
    
    public abstract Animation onDead(Boss boss,String keyword);
    
    public abstract Animation onAttackMoving(Boss boss,String keyword);

}
