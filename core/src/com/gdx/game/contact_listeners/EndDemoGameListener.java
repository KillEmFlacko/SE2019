/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdx.game.contact_listeners;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.gdx.game.contact_listeners.events.DeathEvent;
import com.gdx.game.entities.Player;
import com.gdx.game.entities.bosses.DemoBoss;
import com.gdx.game.screens.GameScreen;

/**
 *
 * @author salvatore
 */
public class EndDemoGameListener extends ChangeListener{
    private final GameScreen gamesScreen;
    private final Player player;
    private final DemoBoss demoBoss;
    
    public EndDemoGameListener(GameScreen gameScreen,Player p, DemoBoss db){
        this.gamesScreen = gameScreen;
        this.player = p;
        this.demoBoss = db;
    }
    
    @Override
    public void changed(ChangeEvent event, Actor actor) {
        if(event instanceof DeathEvent){
            System.out.println("livello finito");
            this.gamesScreen.end();
        }
    }
}
