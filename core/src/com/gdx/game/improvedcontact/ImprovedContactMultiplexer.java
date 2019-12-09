package com.gdx.game.improvedcontact;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.Manifold;
import net.dermetfan.gdx.physics.box2d.ContactMultiplexer;

/**
 *
 * @author Armando
 */
public class ImprovedContactMultiplexer extends ContactMultiplexer {

    @Override
    public void endContact(Contact contact) {
        super.endContact(contact); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {
        super.postSolve(contact, impulse); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {
        super.preSolve(contact, oldManifold); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void beginContact(Contact contact) {
        super.beginContact(contact); //To change body of generated methods, choose Tools | Templates.
    }
    
}
