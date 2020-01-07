package com.gdx.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.gdx.game.GdxGame;

/**
 *
 * @author Armando
 */
public class DemoBossBullet extends BasicBullet {

    // ASTRAI
    public DemoBossBullet( float radius, Vector2 position, int damage, float initSpeed) {
        super( radius, position, damage, initSpeed);
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
        DemoBossBullet clone = new DemoBossBullet( getWidth() / 2, getPosition(), damage, initialSpeed);
        clone.setFilter(filter);
        return clone;
    }
}
