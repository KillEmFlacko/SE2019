package com.gdx.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.Filter;

/**
 *
 * @author ammanas
 */
public class DemoBossBulletDef extends BasicBulletDef {
    /*
    public DemoBossBullet(World world, float radius, Vector2 position, int damage, float initSpeed) {
    super(world, radius, position, damage, initSpeed);
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
    DemoBossBullet clone = new DemoBossBullet(world, getWidth() / 2, getPosition(), damage, initialSpeed);
    clone.setFilter(filter);
    return clone;
    }
    @Override
    public void dispose() {
    Stage stage = getStage();
    //actor already removed i don't really know what it is
    if (stage == null)
    return;
    Group g = stage.getRoot();
    g.removeActor(this);
    GdxGame.game.bodyToRemove.add(body);
    }
     */
    private final Texture texture;


    public DemoBossBulletDef(MortalEntity caster, Filter filter) {
        super(caster, filter);
        this.texture = new Texture(Gdx.files.internal("texture/fireball/Small_Fireball_26x10.png"));
    }

    /*
    public DemoBossBullet(World world, float radius, Vector2 position, int damage, float initSpeed) {
        super(world, radius, position, damage, initSpeed);
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
        DemoBossBullet clone = new DemoBossBullet(world, getWidth() / 2, getPosition(), damage, initialSpeed);
        clone.setFilter(filter);
        return clone;
    }

    @Override
    public void dispose() {
        Stage stage = getStage();
        //actor already removed i don't really know what it is
        if (stage == null)
            return;

        Group g = stage.getRoot();
        
        g.removeActor(this);
        
        GdxGame.game.bodyToRemove.add(body);
    }
    */

}
