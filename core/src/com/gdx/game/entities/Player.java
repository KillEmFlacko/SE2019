package com.gdx.game.entities;

import com.gdx.game.factories.FilterFactory;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.gdx.game.GdxGame;
import com.gdx.game.contact_listeners.events.HitEvent;
import com.gdx.game.factories.Weapon;

/**
 *
 * @author Giovanni
 */
public final class Player extends MortalEntity {

    private TextureAtlas atlas;
    private final Weapon weapon;
    private Animation<TextureAtlas.AtlasRegion> runAnimation;
    private Animation<TextureAtlas.AtlasRegion> idleAnimation;
    private float stateTime = 0f;
    private final float speed = 9f;

    private boolean skillSelected = false;
    private DamageSkillAdapter dmgSkill;
    private DefenseSkill dSkill;
    private Weapon skillWeapon;

    public Player(String name, int lifepoints,  float width, float height, Vector2 position) {
        super(name, lifepoints,  width, height, position);

        //player must take spells that he has at his disposition
        //BigFireballSkillBullet bigFireballSkillBullet = new BigFireballSkillBullet(world, 3f, initalPosition, 50, 10f);
        dmgSkill = new BigFireballSkill(5f,this,30,10f,world,1f,this.getPosition());
        dSkill = new LightShieldSkill(2f, this);
        //skillWeapon = new Weapon(this, dmgSkill.getB(), 1/dmgSkill.getCoolDown());

        weapon = new Weapon(this, new BasicBullet( 4f / GdxGame.game.SCALE, position, 10, speed * 1.5f), 3);
    }

    @Override
    protected void initPhysics() {
        BodyDef bdDef = new BodyDef();
        bdDef.type = BodyDef.BodyType.DynamicBody;
        bdDef.position.set(getPosition());
        body = world.createBody(bdDef);
        body.setUserData(this);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(getWidth() / 2, getWidth() / 2);

        FilterFactory ff = new FilterFactory();
        FixtureDef fixDef = new FixtureDef();
        fixDef.shape = shape;
        ff.copyFilter(fixDef.filter, ff.getPlayerFilter());
        fixDef.isSensor = false;
        fixDef.restitution = 0f;
        fixDef.density = 0f;

        Fixture fixt = body.createFixture(fixDef);
        fixt.setUserData(body);
        shape.dispose();
    }

    @Override
    protected void initGraphics() {
        atlas = new TextureAtlas(Gdx.files.internal("texture/player/wizzard/wizzard.atlas"));
        idleAnimation = new Animation(0.2f, atlas.findRegions("m_idle"), Animation.PlayMode.LOOP);
        runAnimation = new Animation(0.09f, atlas.findRegions("m_run"), Animation.PlayMode.LOOP);
        textureRegion = idleAnimation.getKeyFrame(0f);
    }

    @Override
    public void act(float delta) {
        if (body == null) {
            initPhysics();
            initGraphics();
        }
        setPosition(body.getPosition());
        if (super.life <= 0) {
            kill();
            return;
        }

        stateTime += delta;
        super.act(delta);

        Vector2 velocity = new Vector2(0, 0);
        if (Gdx.input.isKeyPressed(Keys.W)) {
            velocity.add(0, speed);
        }
        if (Gdx.input.isKeyPressed(Keys.S)) {
            velocity.add(0, -speed);
        }
        if (Gdx.input.isKeyPressed(Keys.A)) {
            velocity.add(-speed, 0);
        }
        if (Gdx.input.isKeyPressed(Keys.D)) {
            velocity.add(speed, 0);
        }
        if (!body.getLinearVelocity().equals(velocity)) {
            setLinearVelocity(velocity);
        }

        if (!body.getLinearVelocity().equals(new Vector2(0, 0))) {
            textureRegion = runAnimation.getKeyFrame(stateTime);
        } else {
            textureRegion = idleAnimation.getKeyFrame(stateTime);
        }

        if (textureRegion.isFlipX()) {
            textureRegion.flip(true, false);
        }

        if ((Gdx.input.isKeyPressed(Keys.A) || Gdx.input.isKeyPressed(Keys.S)) && !(Gdx.input.isKeyPressed(Keys.D))) {
            textureRegion.flip(true, false);
        }

        shouldShoot(delta);
    }

    public void shouldShoot(float delta) {
        
        if(Gdx.input.isKeyPressed(Keys.E)){
            dSkill.castOn(this);
            
        }

        if (Gdx.input.isKeyJustPressed(Keys.UP) && Gdx.input.isKeyPressed(Keys.Q)) {
            //skill is a Fireball for instance
            //skillWeapon.fire(new Vector2(0, 1));
            dmgSkill.cast(new Vector2(0,1));
            

        } else if (Gdx.input.isKeyPressed(Keys.UP) && !Gdx.input.isKeyPressed(Keys.Q)) {
            weapon.fire(new Vector2(0, 1));
        }

        if (Gdx.input.isKeyPressed(Keys.DOWN)) {
            weapon.fire(new Vector2(0, -1));
        }
        
        if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
            if (Gdx.input.isKeyJustPressed(Keys.RIGHT) && Gdx.input.isKeyPressed(Keys.Q)) {
                //skill is a Fireball for instance
                //skillWeapon.fire(new Vector2(1, 0));
                dmgSkill.cast(new Vector2(1,0));

            } else if (Gdx.input.isKeyPressed(Keys.RIGHT) && !Gdx.input.isKeyPressed(Keys.Q)) {
                weapon.fire(new Vector2(1, 0));
            }

        }
        if (Gdx.input.isKeyPressed(Keys.LEFT)) {
            weapon.fire(new Vector2(-1, 0));
        }

    }

    @Override
    public void isHitBy(Bullet bullet) {
        life -= bullet.getDamage();
        fire(new HitEvent());
    }

    float getBoostSpellMultiplier() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    float getDamageSpellMultiplier() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    float getDefenseSpelMultiplier() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
