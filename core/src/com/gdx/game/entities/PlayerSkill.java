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
public abstract class PlayerSkill extends Skill {

    public PlayerSkill(MortalEntity caster, float coolDown) {
        super(caster, coolDown);
    }
}
