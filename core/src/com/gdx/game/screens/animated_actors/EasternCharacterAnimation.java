package com.gdx.game.screens.animated_actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

/**
 *
 * @author ferdy
 */
public class EasternCharacterAnimation extends PlaybleCharacterAnimation {

    public EasternCharacterAnimation() {
        atlas = new TextureAtlas(Gdx.files.internal("texture/player/wizzard/Ewizzard.atlas"));
        idleAnimation = new Animation(0.2f, atlas.findRegions("m_idle"), Animation.PlayMode.LOOP);
    }
}
