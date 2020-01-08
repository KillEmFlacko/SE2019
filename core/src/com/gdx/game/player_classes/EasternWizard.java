package com.gdx.game.player_classes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class EasternWizard extends MovableCharacterClass {

    private final float STRENGHT = 1.5f;
    private final float ATTACK_RATE = 3;
    private final float SPEED = 9f;
    private final int LIFE_POINTS = 4;
    private final float BULLET_SPEED = 7f * 1.5f;
    private TextureAtlas atlas;
    private Animation<TextureAtlas.AtlasRegion> runAnimation;
    private Animation<TextureAtlas.AtlasRegion> idleAnimation;

    @Override
    public void executeGraphics() {
        atlas = new TextureAtlas(Gdx.files.internal("texture/player/wizzard/Ewizzard.atlas"));
        idleAnimation = new Animation(0.2f, atlas.findRegions("m_idle"), Animation.PlayMode.LOOP);
        runAnimation = new Animation(0.09f, atlas.findRegions("m_run"), Animation.PlayMode.LOOP);
    }

    @Override
    public float getStrenght() {
        return STRENGHT;
    }

    @Override
    public float getAttackRate() {
        return ATTACK_RATE;
    }

    @Override
    public float getSpeed() {
        return SPEED;
    }

    @Override
    public int getLifePoints() {
        return LIFE_POINTS;
    }

    @Override
    public float getBulletSpeed() {
        return BULLET_SPEED;
    }

    @Override
    public TextureAtlas getAtlas() {
        return atlas;
    }

    @Override
    public Animation<TextureAtlas.AtlasRegion> getRunAnimation() {
        return runAnimation;
    }

    @Override
    public Animation<TextureAtlas.AtlasRegion> getIdleAnimation() {
        return idleAnimation;
    }

}
