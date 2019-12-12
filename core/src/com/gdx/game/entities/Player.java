package com.gdx.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.gdx.game.GdxGame;
import com.gdx.game.contact_listeners.events.DeathEvent;
import com.gdx.game.contact_listeners.events.HitEvent;
import com.gdx.game.entities.classes.CharacterClass;
import com.gdx.game.factories.Weapon;

/**
 *
 * @author Giovanni
 */
public final class Player extends MortalEntity {
    private final Weapon weapon;
    private float stateTime = 0f;
    private float speed;
    
    private boolean skillSelected = false;
    private DamageSkillAdapter dmgSkill;
    private DefenseSkill dSkill;
    private Weapon skillWeapon;
    public Player(String name, World world, float width, float height, Vector2 position, CharacterClass characterClass) {
        super(name, characterClass.getLifePoints(), world, width, height, position);

        //player must take spells that he has at his disposition
        //BigFireballSkillBullet bigFireballSkillBullet = new BigFireballSkillBullet(world, 3f, initalPosition, 50, 10f);
        dmgSkill = new BigFireballSkill(5f, this, 30, 10f, world, 1f, this.getPosition());
        dSkill = new LightShieldSkill(3f, this);
        //skillWeapon = new Weapon(this, dmgSkill.getB(), 1/dmgSkill.getCoolDown());

        weapon = new Weapon(this, new BasicBullet(world, 4f / GdxGame.game.SCALE, position, 10, characterClass.getBulletSpeed()), 3);
	this.characterClass = characterClass;
    }
    
    @Override
    protected void initPhysics() {
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
            characterClass.executePhysics(world, getPosition(), getWidth(), getWidth());
            body = characterClass.getBody();
        }
        setPosition(characterClass.getBody().getPosition());
        if(super.life <= 0){
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
    
    public void shouldShoot(float delta) {
        
        if (Gdx.input.isKeyPressed(Keys.E)) {
            dSkill.castOn(this);
            
        }
        
        if (Gdx.input.isKeyJustPressed(Keys.UP) && Gdx.input.isKeyPressed(Keys.Q)) {
            //skill is a Fireball for instance
            //skillWeapon.fire(new Vector2(0, 1));
            dmgSkill.cast(new Vector2(0, 1));
            
        } else if (Gdx.input.isKeyPressed(Keys.UP) && !Gdx.input.isKeyPressed(Keys.Q)) {
            weapon.fire(new Vector2(0, 1));
        }
        
        if (Gdx.input.isKeyPressed(Keys.DOWN) && Gdx.input.isKeyPressed(Keys.Q)) {
            
            dmgSkill.cast(new Vector2(0, -1));
        } else if (Gdx.input.isKeyPressed(Keys.DOWN) && !Gdx.input.isKeyPressed(Keys.Q)) {
            weapon.fire(new Vector2(0, -1));
        }
        
        if (Gdx.input.isKeyPressed(Keys.RIGHT) && Gdx.input.isKeyPressed(Keys.Q)) {
            
            dmgSkill.cast(new Vector2(1, 0));
            
        } else if (Gdx.input.isKeyPressed(Keys.RIGHT) && !Gdx.input.isKeyPressed(Keys.Q)) {
            weapon.fire(new Vector2(1, 0));
        }
        
        if (Gdx.input.isKeyPressed(Keys.LEFT) && Gdx.input.isKeyPressed(Keys.Q)) {
            dmgSkill.cast(new Vector2(-1, 0));
        } else if (Gdx.input.isKeyPressed(Keys.LEFT) && !Gdx.input.isKeyPressed(Keys.Q)) {
            weapon.fire(new Vector2(-1, 0));
        }
        
    }
    
    @Override
    public void isHitBy(Bullet bullet) {
        life -= bullet.getDamage();
        fire(new HitEvent());
    }
    
    @Override
    public void kill() {
        GdxGame.game.bodyToRemove.add(this.body);
        this.getStage().getRoot().removeActor(this);
        fire(new DeathEvent());
    }
    @Override
    public void draw(Batch batch, float parentAlpha) {
        Color color = getColor();
        batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);
        batch.draw(textureRegion, getX() - getWidth()/2, getY() - getWidth()/2, getOriginX(), getOriginY(),
        getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
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
