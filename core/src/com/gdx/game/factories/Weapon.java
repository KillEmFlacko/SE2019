/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdx.game.factories;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.gdx.game.entities.Entity;
import com.gdx.game.entities.Bullet;
import com.gdx.game.entities.Player;
import java.util.Date;

/**
 *
 * @author Armando
 */
public class Weapon {
    private Bullet bullet;
    private Entity shooter;
    private int shootingRate;
    private Date lastFireDate = null;
    
    public Weapon(Entity shooterEntity, Bullet bullet, int shootingRate){
        this.shooter = shooterEntity;
        this.bullet = bullet;
        this.shootingRate = shootingRate;
    }
    
    public void fire(Vector2 direction){
        Date ts = new Date();
        if(lastFireDate == null || ts.getTime() - lastFireDate.getTime() > (1f/shootingRate * 1000) ){
            Vector2 normDir = direction.nor();
            Vector2 bulletVelocity = new Vector2(normDir.x * bullet.getInitalSpeed(), normDir.y * bullet.getInitalSpeed());
            bullet.setInitialPosition(shooter.getPosition());
            if(shooter instanceof Player){
                bullet.setFilter(FilterFactory.getPlayerBulletFilter());
            }else {
                bullet.setFilter(FilterFactory.getEnemyBulletFilter());
            }

            Bullet clone = bullet.clone();
            shooter.getStage().addActor(clone);
            clone.init();
            clone.setLinearVelocity(bulletVelocity);
            
            lastFireDate = new Date();
        }
    }
}
