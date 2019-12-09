package com.gdx.game.contact_listeners;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.gdx.game.entities.Bullet;
import com.gdx.game.entities.Player;
import com.gdx.game.entities.bosses.DemoBoss;

/**
 *
 * @author Armando
 */
public class BulletDamageContactListener implements ContactListener {

    @Override
    public void beginContact(Contact contact) {
        if (contact.getFixtureA().getUserData() instanceof Bullet) {
            Bullet b = (Bullet) contact.getFixtureA().getUserData();

            if (contact.getFixtureB().getUserData() instanceof Body) {
                Body body = (Body) contact.getFixtureB().getUserData();
                if (body.getUserData() instanceof DemoBoss) {
                    DemoBoss boss = (DemoBoss) body.getUserData();
                    boss.isHitBy(b);
                    Gdx.app.log("Hit", "Ueue, hai colpito il bosso!");
                    Gdx.app.log("HP", boss.getLife().toString());
                } else if (body.getUserData() instanceof Player) {
                    Player player = (Player) body.getUserData();
                    player.isHitBy(b);
                    Gdx.app.log("Hit", "Ops, il bosso ti ha colpito!");
                    Gdx.app.log("HP", player.getLife().toString());
                }
            }

            b.dispose();
        }
        if (contact.getFixtureB().getUserData() instanceof Bullet) {
            Bullet b = (Bullet) contact.getFixtureB().getUserData();

            if (contact.getFixtureA().getUserData() instanceof Body) {
                Body body = (Body) contact.getFixtureA().getUserData();
                if (body.getUserData() instanceof DemoBoss) {
                    DemoBoss boss = (DemoBoss) body.getUserData();
                    boss.isHitBy(b);
                    Gdx.app.log("HP", boss.getLife().toString());
                } else if (body.getUserData() instanceof Player) {
                    Player player = (Player) body.getUserData();
                    player.isHitBy(b);
                    Gdx.app.log("Hit", "Ops, il bosso ti ha colpito!");
                    Gdx.app.log("HP", player.getLife().toString());
                }
            }

            b.dispose();
        }
    }

    @Override
    public void endContact(Contact contact) {
    }

    @Override
    public void preSolve(Contact arg0, Manifold arg1) {
    }

    @Override
    public void postSolve(Contact arg0, ContactImpulse arg1) {
    }

}
