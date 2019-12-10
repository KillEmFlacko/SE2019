
package com.gdx.game.entities.classes;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.gdx.game.entities.Player;
import com.gdx.game.factories.Weapon;

public interface CharacterClass{
    
    /*
    protected float strenght;
    protected float speed;
    protected float attackRate;
    protected int lifePoints;
    protected float bulletSpeed;
    protected TextureAtlas atlas;
    protected Weapon weapon;
    protected Animation<TextureAtlas.AtlasRegion> runAnimation;
    protected Animation<TextureAtlas.AtlasRegion> idleAnimation;
    */
    

    public float getAttackRate();

    public int getLifePoints();

    public float getStrenght();

    public float getSpeed();

    public float getBulletSpeed();
    
    public TextureAtlas getAtlas();
    
    public Animation<TextureAtlas.AtlasRegion> getRunAnimation();
    
    public Animation<TextureAtlas.AtlasRegion> getIdleAnimation();
    
    
    public void executePhysics(World world, Vector2 position);
    public void executeGraphics();
}
