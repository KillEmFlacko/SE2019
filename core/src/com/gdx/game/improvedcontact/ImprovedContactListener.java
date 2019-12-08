package com.gdx.game.improvedcontact;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.gdx.game.entities.Bullet;

/**
 *
 * @author Armando
 */
public class ImprovedContactListener implements ContactListener {

    @Override
    public void beginContact(Contact contact) {
        if(contact.getFixtureA().getUserData() instanceof Bullet){
            Gdx.app.log("Hit", "Ueue hai colpito il bosso!");
            Bullet b = (Bullet) contact.getFixtureA().getUserData();
            b.dispose();
        }
        if(contact.getFixtureB().getUserData() instanceof Bullet){
            Gdx.app.log("Hit", "Ueue hai colpito il bosso!");
            Bullet b = (Bullet) contact.getFixtureB().getUserData();
            b.dispose();
        }
//        Body bdA = (Body) contact.getFixtureA().getUserData();
//        Body bdB = (Body) contact.getFixtureA().getUserData();
//
//        if ((bdA.getUserData() instanceof DemoBoss) || (bdB.getUserData() instanceof DemoBoss)) {
//        }
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
