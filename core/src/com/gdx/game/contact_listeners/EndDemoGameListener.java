/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdx.game.contact_listeners;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.gdx.game.contact_listeners.events.DeathEvent;
import com.gdx.game.screens.GameScreen;

/**
 *
 * @author salvatore
 */
public class EndDemoGameListener extends ChangeListener{
    private final GameScreen gamesScreen;
    
    public EndDemoGameListener(GameScreen gameScreen){
        this.gamesScreen = gameScreen;
    }
    
    @Override
    public void changed(ChangeEvent event, Actor actor) {
        if(event instanceof DeathEvent){
            System.out.println("livello finito");
            this.gamesScreen.end();

        }
    }
    
}
