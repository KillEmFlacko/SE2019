
package com.gdx.game.entities.classes;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.gdx.game.entities.Player;
import com.gdx.game.factories.Weapon;

public abstract class CharacterClass{
    
    protected float strenght;
    protected float speed;
    protected float attackRate;
    protected int lifePoints;
    protected float bulletSpeed;
    protected TextureAtlas atlas;
    protected TextureRegion textureRegion;
    protected Weapon weapon;
    protected Animation<TextureAtlas.AtlasRegion> runAnimation;
    protected Animation<TextureAtlas.AtlasRegion> idleAnimation;

    public CharacterClass(float strenght, float speed, float attackRate, int lifePoints, float bulletSpeed) {
        this.strenght = strenght;
        this.speed = speed;
        this.attackRate = attackRate;
        this.lifePoints = lifePoints;
        this.bulletSpeed = bulletSpeed;
    }
    


    public float getAttackRate() {
        return attackRate;
    }

    public int getLifePoints() {
        return lifePoints;
    }

    public float getStrenght() {
        return strenght;
    }

    public float getSpeed() {
        return speed;
    }

    public float getBulletSpeed() {
        return bulletSpeed;
    }
    
    
    
    public abstract void executePhysics();
    public abstract void executeGraphics();
    
}
