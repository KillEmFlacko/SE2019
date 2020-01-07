package com.gdx.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.actions.RepeatAction;
import com.gdx.game.actions.GameAction;
import com.gdx.game.contact_listeners.events.HitEvent;
import com.gdx.game.factories.FilterFactory;
import com.gdx.game.factories.Weapon;

/**
 *
 * @author Giovanni
 */
public final class Player extends MortalEntity {

    private float speed;

    private boolean skillSelected = false;
    private DefenseSkill dSkill;

    private Weapon baseWeapon;
    private Weapon fireWeapon;
    
    private float strength;
    private float attackRate;
    private float baseSpeed;
    private float bulletSpeed;

    public Player(PlayerDef entityDef) {
        super(entityDef);

        this.strength = entityDef.getStrength();
        this.baseSpeed = entityDef.getSpeed();
        this.attackRate = entityDef.getAttackRate();
        this.bulletSpeed = entityDef.getBulletSpeed();
        
        FilterFactory ff = new FilterFactory();
        
        BulletDef bulletDef = new BasicBulletDef();
        Bullet baseBullet = new Bullet(bulletDef, ff.getPlayerBulletFilter(), bulletSpeed);
        baseBullet.setDamageMultiplicator(strength);
        this.baseWeapon = new Weapon(this, baseBullet, attackRate);
        
        BulletDef fireballDef = new FireballDef();
        Bullet fireball = new Bullet(fireballDef, ff.getPlayerBulletFilter(), bulletSpeed);
        this.fireWeapon = new Weapon(this, fireball, 1f/((SkillDef)fireballDef).getCooldown());
        
        RepeatAction playerAction = new RepeatAction();
        playerAction.setAction(new PlayerAction());
        playerAction.setCount(RepeatAction.FOREVER);

        addAction(playerAction);
    }

    public float getStrength() {
        return strength;
    }

    public float getAttackRate() {
        return attackRate;
    }

    public float getBulletSpeed() {
        return bulletSpeed;
    }

    /*
    public Player(String name, World world, float width, float height, Vector2 position, CharacterClass characterClass) {
        super(name, characterClass.getLifePoints(), world, width, height, position);
        System.out.println(this.getLife());
        //player must take spells that he has at his disposition
        //BigFireballSkillBullet bigFireballSkillBullet = new BigFireballSkillBullet(world, 3f, initalPosition, 50, 10f);
        dmgSkill = new BigFireballSkill(5f, this, 30, 10f, world, 1f, this.getPosition());
        dSkill = new LightShieldSkill(3f, this);
        //skillWeapon = new Weapon(this, dmgSkill.getB(), 1/dmgSkill.getCoolDown());
        

        weapon = new Weapon(this, new BasicBulletDef(world, 4f / GdxGame.game.SCALE, position, 10, characterClass.getBulletSpeed()), 3);
	this.characterClass = characterClass;
    }
    

    
    @Override
    protected void initGraphics() {
        characterClass.executeGraphics();
        textureRegion = characterClass.getIdleAnimation().getKeyFrame(0f);
    }
    
     
    @Override
    public void act(float delta) {
        if (characterClass.getBody() == null) {
            characterClass.executeGraphics();
            characterClass.executePhysics(world, getPosition(), getWidth(), getWidth(), this);
            body = characterClass.getBody();
        }
        setPosition(characterClass.getBody().getPosition());
        if (super.life <= 0) {
            kill();
            return;
        }

        stateTime += delta;
        super.act(delta);
        float speed = characterClass.getSpeed();
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
            textureRegion = characterClass.getRunAnimation().getKeyFrame(stateTime);
        } else {
            textureRegion = characterClass.getIdleAnimation().getKeyFrame(stateTime);
        }

        if (textureRegion.isFlipX()) {
            textureRegion.flip(true, false);
        }

        if ((Gdx.input.isKeyPressed(Keys.A) || Gdx.input.isKeyPressed(Keys.S)) && !(Gdx.input.isKeyPressed(Keys.D))) {
            textureRegion.flip(true, false);
        }

        shouldShoot(delta);
    }
     */
    public void shouldShoot(float delta) {

        if (Gdx.input.isKeyPressed(Keys.E)) {
            dSkill.castOn(this);

        }

        if (Gdx.input.isKeyJustPressed(Keys.UP) && Gdx.input.isKeyPressed(Keys.Q)) {
            //skill is a Fireball for instance
            //skillWeapon.fire(new Vector2(0, 1));
            fireWeapon.fire(new Vector2(0, 1));

        } else if (Gdx.input.isKeyPressed(Keys.UP) && !Gdx.input.isKeyPressed(Keys.Q)) {
            baseWeapon.fire(new Vector2(0, 1));
        }

        if (Gdx.input.isKeyPressed(Keys.DOWN) && Gdx.input.isKeyPressed(Keys.Q)) {

            fireWeapon.fire(new Vector2(0, -1));
        } else if (Gdx.input.isKeyPressed(Keys.DOWN) && !Gdx.input.isKeyPressed(Keys.Q)) {
            baseWeapon.fire(new Vector2(0, -1));
        }

        if (Gdx.input.isKeyPressed(Keys.RIGHT) && Gdx.input.isKeyPressed(Keys.Q)) {

            fireWeapon.fire(new Vector2(1, 0));

        } else if (Gdx.input.isKeyPressed(Keys.RIGHT) && !Gdx.input.isKeyPressed(Keys.Q)) {
            baseWeapon.fire(new Vector2(1, 0));
        }

        if (Gdx.input.isKeyPressed(Keys.LEFT) && Gdx.input.isKeyPressed(Keys.Q)) {
            fireWeapon.fire(new Vector2(-1, 0));
        } else if (Gdx.input.isKeyPressed(Keys.LEFT) && !Gdx.input.isKeyPressed(Keys.Q)) {
            baseWeapon.fire(new Vector2(-1, 0));
        }

    }

    @Override
    public void isHitBy(Bullet bullet) {
        super.isHitBy(bullet);
        fire(new HitEvent());
    }

    /*
    @Override
    public void draw(Batch batch, float parentAlpha) {
        Color color = getColor();
        batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);
        batch.draw(textureRegion, getX() - getWidth() / 2, getY() - getWidth() / 2, getOriginX(), getOriginY(),
                getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
    }

    @Override
    protected void initPhysics() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
     */
    private class PlayerAction extends GameAction {

        @Override
        public boolean act(float delta) {
            setPosition(getBody().getPosition());
            if (getHP() <= 0) {
                kill();
                return true;
            }

            stateTime += delta;
            super.act(delta);
            float speed = baseSpeed;
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
            if (!getBody().getLinearVelocity().equals(velocity)) {
                setLinearVelocity(velocity);
            }

            if (!getBody().getLinearVelocity().equals(new Vector2(0, 0))) {
                setRegionToDraw(getEntityDef().getAnimations().get("run").getKeyFrame(stateTime));
            } else {
                setRegionToDraw(getEntityDef().getAnimations().get("idle").getKeyFrame(stateTime));
            }

            if (getRegionToDraw().isFlipX()) {
                getRegionToDraw().flip(true, false);
            }

            if ((Gdx.input.isKeyPressed(Keys.A) || Gdx.input.isKeyPressed(Keys.S)) && !(Gdx.input.isKeyPressed(Keys.D))) {
                getRegionToDraw().flip(true, false);
            }

            shouldShoot(delta);
            return true;
        }
    }

}
