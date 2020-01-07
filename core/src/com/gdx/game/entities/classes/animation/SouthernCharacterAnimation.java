/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdx.game.entities.classes.animation;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

/**
 *
 * @author ferdy
 */
public class SouthernCharacterAnimation extends PlaybleCharacterAnimation {

    public SouthernCharacterAnimation() {
        atlas = new TextureAtlas(Gdx.files.internal("texture/player/wizzard/Swizzard.atlas"));
        idleAnimation = new Animation(0.2f, atlas.findRegions("m_idle"), Animation.PlayMode.LOOP);
    }

}
