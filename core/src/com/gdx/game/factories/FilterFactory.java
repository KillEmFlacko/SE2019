package com.gdx.game.factories;

import com.badlogic.gdx.physics.box2d.Filter;

/**
 *
 *
 * @author Armando
 */
public class FilterFactory {

    private final short PLAYER_CATEGORY = 0x0004;
    private final short PLAYER_BULLET_CATEGORY = 0x0008;
    private final short ENEMY_CATEGORY = 0x0001;
    private final short ENEMY_BULLET_CATEGORY = 0x0002;
    private final short ENTITY_COLLISION_FIXTURE = 0x0010;

    public Filter getPlayerFilter() {
        Filter f = new Filter();
        f.categoryBits = PLAYER_CATEGORY;
        f.maskBits = (short) (ENEMY_BULLET_CATEGORY | ENEMY_CATEGORY);
        return f;
    }

    public Filter getPlayerBulletFilter() {
        Filter f = new Filter();
        f.categoryBits = PLAYER_BULLET_CATEGORY;
        f.maskBits = ENEMY_CATEGORY;
        return f;
    }

    public Filter getEnemyFilter() {
        Filter f = new Filter();
        f.categoryBits = ENEMY_CATEGORY;
        f.maskBits = (short) (PLAYER_CATEGORY | PLAYER_BULLET_CATEGORY);
        return f;
    }

    public Filter getEnemyBulletFilter() {
        Filter f = new Filter();
        f.categoryBits = ENEMY_BULLET_CATEGORY;
        f.maskBits = PLAYER_CATEGORY;
        return f;
    }

    public Filter getEntityCollisionFixtureFilter() {
        Filter f = new Filter();
        f.categoryBits = ENTITY_COLLISION_FIXTURE;
        f.maskBits = (short) 0xFFFF;
        return f;
    }

    public void copyFilter(Filter to, Filter from) {
        to.categoryBits = from.categoryBits;
        to.maskBits = from.maskBits;
        to.groupIndex = from.groupIndex;
    }
}
