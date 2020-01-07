package com.gdx.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.gdx.game.factories.FilterFactory;

/**
 *
 * @author ammanas
 */
public class BigFireballSkillBullet extends SkillBullet {

    private TextureAtlas atlas;

    public BigFireballSkillBullet(int damage, float speed, float radius, Vector2 position) {
        super(damage, speed, radius, position);
        defaultAction = new BasicBulletAction();
    }

    @Override
    public void initPhysics() {

        BodyDef bdDef = new BodyDef();
        bdDef.type = BodyDef.BodyType.KinematicBody;
        bdDef.position.set(getPosition());

        body = world.createBody(bdDef);
        body.setUserData(this);

        CircleShape circleShape = new CircleShape();
        circleShape.setRadius(getWidth() / 2);

        FilterFactory ff = new FilterFactory();
        FixtureDef fixtureDef = new FixtureDef();
        ff.copyFilter(fixtureDef.filter, filter);
        fixtureDef.shape = circleShape;
        fixtureDef.isSensor = true;
        fixtureDef.density = 0.1f;

        Fixture fxt = body.createFixture(fixtureDef);
        fxt.setUserData(this);
        circleShape.dispose();
    }

    @Override
    public void initGraphics() {

        atlas = new TextureAtlas(Gdx.files.internal("texture/player/skill/fireballSkill/pack.atlas"));
        movingAnimation = new Animation<TextureRegion>(0.1f, atlas.findRegions("moving"), Animation.PlayMode.LOOP);
        textureRegion = movingAnimation.getKeyFrame(0f);
    }

    private class BasicBulletAction extends Action {

        @Override
        public boolean act(float delta) {
            stateTime += delta;
            textureRegion = movingAnimation.getKeyFrame(stateTime, true);
            return false;
        }
    }

    @Override
    public SkillBullet clone() {
        BigFireballSkillBullet clone = new BigFireballSkillBullet(this.getDamage(), getInitalSpeed(), getWidth() / 2, getPosition());
        clone.setFilter(filter);
        return clone;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        float textureH = getHeight();
        float textureW = getHeight() * ((float) textureRegion.getRegionWidth() / textureRegion.getRegionHeight());
        batch.draw(textureRegion,
                body.getPosition().x - getWidth() / 2, body.getPosition().y - getHeight() / 2,
                getWidth() / 2, getHeight() / 2,
                textureW, textureH,
                1, 1,
                body.getLinearVelocity().angle() + 180f);
    }
}
