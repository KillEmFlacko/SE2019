/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdx.game.entities;

import com.badlogic.gdx.physics.box2d.Filter;

/**
 *
 * @author Armando
 */
public class FilterFactory {
    private static final short PLAYER_CATEGORY = 0x0004;
    private static final short PLAYER_BULLET_CATEGORY = 0x0008;
    private static final short ENEMY_CATEGORY = 0x0001;
    private static final short ENEMY_BULLET_CATEGORY = 0x0002;
    
    public static Filter getPlayerFilter(){
        Filter f = new Filter();
        f.categoryBits = PLAYER_CATEGORY;
        f.maskBits = (short) (ENEMY_BULLET_CATEGORY | ENEMY_CATEGORY);
        return f;
    }
    
    public static Filter getPlayerBulletFilter(){
        Filter f = new Filter();
        f.categoryBits = PLAYER_BULLET_CATEGORY;
        f.maskBits = ENEMY_CATEGORY;
        return f;
    }
    
    public static Filter getEnemyFilter(){
        Filter f = new Filter();
        f.categoryBits = ENEMY_CATEGORY;
        f.maskBits = (short) (PLAYER_CATEGORY | PLAYER_BULLET_CATEGORY);
        return f;
    }
    
    public static Filter getEnemyBulletFilter(){
        Filter f = new Filter();
        f.categoryBits = ENEMY_BULLET_CATEGORY;
        f.maskBits = PLAYER_CATEGORY;
        return f;
    }
    
    public static void copyFilter(Filter to, Filter from){
        to.categoryBits = from.categoryBits;
        to.maskBits = from.maskBits;
        to.groupIndex = from.groupIndex;
    }
}
