package com.gdx.game.entities.classes.animation;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class NorthernCharacterAnimation extends PlaybleCharacterAnimation {

    public NorthernCharacterAnimation() {
        atlas = new TextureAtlas(Gdx.files.internal("texture/player/wizzard/Nwizzard.atlas"));
        idleAnimation = new Animation(0.2f, atlas.findRegions("m_idle"), Animation.PlayMode.LOOP);
    }

}
