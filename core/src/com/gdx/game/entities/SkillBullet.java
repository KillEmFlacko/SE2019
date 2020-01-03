/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdx.game.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.World;

/**
 *
 * @author ammanas
 */
public abstract class SkillBullet extends Bullet {
   
    public SkillBullet(BulletDef entityDef) {
        super(entityDef);
    }

    public SkillBullet(Filter filter, BulletDef entityDef) {
        super(filter, entityDef);
    }
    
    
   
    
}
