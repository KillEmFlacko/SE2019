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

    private final Bullet bullet;
    private final Entity shooter;
    private final float shootingRate;
    private Date lastFireDate = null;

    public Weapon(Entity shooterEntity, Bullet bullet, float shootingRate) {
        this.shooter = shooterEntity;
        this.bullet = bullet;
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
            clone.initPhysics();
            clone.initGraphics();
            shooter.getStage().addActor(clone);
            clone.setLinearVelocity(bulletVelocity);

            lastFireDate = new Date();
        }
    }
}
