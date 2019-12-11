/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdx.game.entities;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

/**
 *
 * @author ammanas
 */
public abstract class PlayerSkill extends Skill {

    public PlayerSkill(float coolDown, MortalEntity caster) {
        super(coolDown, caster);
    }   
}
