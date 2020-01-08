package com.gdx.game.entities.bullets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

/**
 *
 * @author Armando
 */
public class DemoBossBasicBullet extends BasicBullet {

    // ASTRAI
    public DemoBossBasicBullet(float radius, Vector2 position, int damage, float initSpeed) {
        super(radius, position, damage, initSpeed);
    }

    @Override
    public void initGraphics() {
        Texture texture = new Texture(Gdx.files.internal("texture/fireball/Small_Fireball_26x10.png"));
        TextureRegion[][] animation = TextureRegion.split(texture, 26, 10);
        Array<TextureRegion> array = new Array<>(animation.length * animation[0].length);
        for (TextureRegion[] textureRegions : animation) {
            array.addAll(textureRegions);
        }
        movingAnimation = new Animation<>(0.05f, array, Animation.PlayMode.LOOP);
        textureRegion = movingAnimation.getKeyFrame(0f);
    }

    @Override
    public Bullet clone() {
        DemoBossBasicBullet clone = new DemoBossBasicBullet(getWidth() / 2, getPosition(), damage, initialSpeed);
        clone.setFilter(filter);
        return clone;
    }
}
