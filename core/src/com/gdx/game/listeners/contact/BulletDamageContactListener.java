package com.gdx.game.listeners.contact;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.gdx.game.entities.bullets.Bullet;
import com.gdx.game.entities.LightShieldSkillEntity;
import com.gdx.game.entities.MortalEntity;

/**
 *
 * @author Armando
 */
public class BulletDamageContactListener implements ContactListener {

    @Override
    public void beginContact(Contact contact) {
        if (contact.getFixtureA().getUserData() instanceof Bullet) {
            Bullet b = (Bullet) contact.getFixtureA().getUserData();
            b.dispose();
            if (contact.getFixtureB().getUserData() instanceof MortalEntity) {
                MortalEntity boss = (MortalEntity) contact.getFixtureB().getUserData();
                boss.isHitBy(b);
                Gdx.app.log("Hit", "Ueue, hai colpito il bosso!");
                Gdx.app.log("HP", boss.getLife().toString());
            } else if (contact.getFixtureB().getUserData() instanceof LightShieldSkillEntity) {
                LightShieldSkillEntity lsse = (LightShieldSkillEntity) contact.getFixtureB().getUserData();

                System.out.println("Colpito lo scudo");
                lsse.isHitBy(b);
            }
        }

        if (contact.getFixtureB().getUserData() instanceof Bullet) {
            Bullet b = (Bullet) contact.getFixtureB().getUserData();

            System.out.println("Starting Contact");
            System.out.println(contact.getFixtureA().getUserData());
            System.out.println(contact.getFixtureB().getUserData());
            System.out.println(b.getDamage());
            b.dispose();

            System.out.println("COLLISION");
            if (contact.getFixtureA().getUserData() instanceof MortalEntity) {
                MortalEntity boss = (MortalEntity) contact.getFixtureA().getUserData();
                boss.isHitBy(b);
                Gdx.app.log("HP", boss.getLife().toString());
            } else if (contact.getFixtureA().getUserData() instanceof LightShieldSkillEntity) {
                LightShieldSkillEntity lsse = (LightShieldSkillEntity) contact.getFixtureA().getUserData();
                System.out.println("Colpito lo scudo");
                lsse.isHitBy(b);
            }
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
