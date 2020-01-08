package com.gdx.game.skills.entities;

import com.gdx.game.entities.bullets.Bullet;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJointDef;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.gdx.game.entities.MortalEntity;
import com.gdx.game.entities.Player;
import com.gdx.game.factories.FilterFactory;

/**
 *
 * @author ammanas
 */
public class LightShieldSkillEntity extends MortalEntity {

    protected Filter filter;
    private Player caster;
    private Texture texture;
    private static int n_instances = 0;

    //height and width are the dimensions of the square in which the circle is confined
    public LightShieldSkillEntity(String name, Integer life, float width, float height, Vector2 position, Player caster) {
        super(name, life, width, height, position);
        this.caster = caster;
        defaultAction = new Aziona();
    }

    @Override
    public void isHitBy(Bullet bullet) {
        life -= bullet.getDamage();
    }

    @Override
    public void initPhysics() {

        BodyDef bdDef = new BodyDef();
        bdDef.type = BodyDef.BodyType.DynamicBody;
        bdDef.position.set(caster.getPosition());
        //this.world.createBody(bdDef)   caster.body
        body = this.world.createBody(bdDef);
        body.setUserData(this);
        CircleShape circleShape = new CircleShape();
        circleShape.setRadius(getWidth() / 2);

        FilterFactory ff = new FilterFactory();
        filter = ff.getPlayerFilter();
        FixtureDef fixtureDef = new FixtureDef();

        ff.copyFilter(fixtureDef.filter, filter);
        fixtureDef.shape = circleShape;
        fixtureDef.isSensor = true;
        fixtureDef.density = 0f;

        Fixture fxt = body.createFixture(fixtureDef);
        fxt.setUserData(this);
        circleShape.dispose();

        RevoluteJointDef jointDef = new RevoluteJointDef();
        jointDef.initialize(caster.body, body, caster.getPosition());

        this.world.createJoint(jointDef);

    }

    @Override
    public void initGraphics() {

        texture = new Texture(Gdx.files.internal("texture/player/skill/shield/s420.png"));

        textureRegion = new TextureRegion(texture);

        System.out.println(textureRegion);

    }

    private class Aziona extends Action {

        @Override
        public boolean act(float delta) {
            if (LightShieldSkillEntity.super.life <= 0 || caster.getParent() == null) {
                dispose();
                return true;
            }
            //Questo  fa si che il body vada appresso al player MA dato che lo setti ogni volta 
            //lo scudo si trova in mezzo al boss anche se questo non potrebbe superarlo.
            //body.setTransform(caster.getPosition(), 0);
            //textureRegion = movingAnimation.getKeyFrame(stateTime, true);
            return false;
        }
    }

    public static int getN_instances() {
        return n_instances;
    }

    public static void setN_instances(int n_instances) {
        LightShieldSkillEntity.n_instances = n_instances;
    }

    @Override
    protected void setParent(Group parent) {
        super.setParent(parent);
        n_instances--;
    }
}
