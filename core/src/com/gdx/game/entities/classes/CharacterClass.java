
package com.gdx.game.entities.classes;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.gdx.game.entities.MortalEntity;
import com.gdx.game.entities.Player;
import com.gdx.game.factories.Weapon;

public interface CharacterClass{

    public float getAttackRate();
    
    public Body getBody();

    public int getLifePoints();

    public float getStrenght();

    public float getSpeed();

    public float getBulletSpeed();
    
    public TextureAtlas getAtlas();
    
    public Animation<TextureAtlas.AtlasRegion> getRunAnimation();
    
    public Animation<TextureAtlas.AtlasRegion> getIdleAnimation();
    
    
    public void executePhysics(World world, Vector2 position, float fixtureWidth,float fixtureHeight, MortalEntity character);
    public void executeGraphics();
}
