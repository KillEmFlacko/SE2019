package com.gdx.game.entities.classes.animation;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

public abstract class PlaybleCharacterAnimation extends Actor {

    protected TextureAtlas atlas;
    protected Animation<TextureAtlas.AtlasRegion> runAnimation;
    protected Animation<TextureAtlas.AtlasRegion> idleAnimation;
    protected TextureRegion textureRegion;
    private float stateTime = 0f;

    @Override
    public void act(float delta) {
        stateTime += delta;
        textureRegion = idleAnimation.getKeyFrame(stateTime);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        Color color = getColor();
        batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);
        batch.draw(textureRegion, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
    }

}
