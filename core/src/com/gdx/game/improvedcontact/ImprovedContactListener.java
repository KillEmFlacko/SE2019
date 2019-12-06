/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdx.game.improvedcontact;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.gdx.game.entities.bosses.DemoBoss;

/**
 *
 * @author Armando
 */
public class ImprovedContactListener implements ContactListener {

    @Override
    public void beginContact(Contact contact) {
        Body bdA = (Body) contact.getFixtureA().getUserData();
        Body bdB = (Body) contact.getFixtureA().getUserData();

        if ((bdA.getUserData() instanceof DemoBoss) || (bdB.getUserData() instanceof DemoBoss)) {
            Gdx.app.log("Hit", "Ueue hai colpito il bosso!");
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
