package com.gdx.game.entities.classes;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.gdx.game.entities.MortalEntity;

public interface CharacterClass {

    public float getAttackRate();

    public int getLifePoints();

    public float getStrenght();

    public float getSpeed();

    public float getBulletSpeed();

    public TextureAtlas getAtlas();

    public Animation<TextureAtlas.AtlasRegion> getRunAnimation();

    public Animation<TextureAtlas.AtlasRegion> getIdleAnimation();

    public Body executePhysics(World world, Vector2 position, float fixtureWidth, float fixtureHeight, MortalEntity character);

    public void executeGraphics();

}
