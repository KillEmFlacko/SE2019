package com.gdx.game.screens.animated_actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

/**
 *
 * @author ferdy
 */
public class WesternCharacterAnimation extends PlaybleCharacterAnimation {

    public WesternCharacterAnimation() {
        atlas = new TextureAtlas(Gdx.files.internal("texture/player/wizzard/Wwizzard.atlas"));
        idleAnimation = new Animation(0.2f, atlas.findRegions("m_idle"), Animation.PlayMode.LOOP);
    }

}
