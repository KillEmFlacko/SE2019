/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdx.game.entities.bosses;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.gdx.game.entities.MortalEntity;
import com.gdx.game.entities.*;


/**
 *
 * @author ammanas
 */
public class Boss extends MortalEntity {

    public Boss(Stats stats, EntityDef entityDef) {
        super(entityDef,stats);
    }



}
