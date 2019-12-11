package com.gdx.game.factories;

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

    private  Bullet bullet;
    private final Entity shooter;
    private float shootingRate;
    private Date lastFireDate = null;

    public Weapon(Entity shooterEntity, Bullet bullet, float shootingRate) {
        this.shooter = shooterEntity;
        this.bullet = bullet;
        this.shootingRate = shootingRate;
    }

    public Bullet getBullet() {
        return bullet;
    }

    public void setBullet(Bullet bullet) {
        this.bullet = bullet;
    }

    public float getShootingRate() {
        return shootingRate;
    }

    public void setShootingRate(float shootingRate) {
        this.shootingRate = shootingRate;
    }

    public void fire(Vector2 direction) {
        Date ts = new Date();
        if (lastFireDate == null || ts.getTime() - lastFireDate.getTime() > (1f / shootingRate * 1000)) {
            Vector2 normDir = direction.nor();
            Vector2 bulletVelocity = new Vector2(normDir.x * bullet.getInitalSpeed(), normDir.y * bullet.getInitalSpeed());
            bullet.setPosition(shooter.getPosition().add(normDir.x * shooter.getWidth()/2,normDir.y*shooter.getWidth()/2));
            FilterFactory ff = new FilterFactory();
            if (shooter instanceof Player) {
                bullet.setFilter(ff.getPlayerBulletFilter());
            } else {
                bullet.setFilter(ff.getEnemyBulletFilter());
            }

            Bullet clone = bullet.clone();
            shooter.getStage().addActor(clone);
            clone.initPhysics();
            clone.initGraphics();
            clone.setLinearVelocity(bulletVelocity);

            lastFireDate = new Date();
        }
    }
}
