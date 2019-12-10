/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdx.game.entities.classes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.gdx.game.entities.BasicBullet;
import com.gdx.game.factories.Weapon;

public class NorthernWizard extends CharacterClass{
    
    /*
    lifePoints = 100;
    attackRate = 3;
    strenght = 1;
    speed = 9f;
    bulletSpeed = 9f
    */

    public NorthernWizard() {
        super(1, 12f, 3, 80, 9f);
    }

    @Override
    public void executeGraphics() {
        atlas = new TextureAtlas(Gdx.files.internal("texture/player/wizzard/wizzard.atlas"));
        idleAnimation = new Animation(0.2f, atlas.findRegions("m_idle"), Animation.PlayMode.LOOP);
        runAnimation = new Animation(0.09f, atlas.findRegions("m_run"), Animation.PlayMode.LOOP);
        textureRegion = idleAnimation.getKeyFrame(0f);
    }

    @Override
    public void executePhysics() {
        
    }
    
}
