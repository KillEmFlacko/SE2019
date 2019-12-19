/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdx.game.contact_listeners;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.gdx.game.contact_listeners.events.HitEvent;
import com.gdx.game.entities.Player;
import com.gdx.game.entities.bosses.DemoBoss;
import com.gdx.game.screens.assets.Heart;
import java.util.ArrayList;

/**
 *
 * @author salvatore
 */
public class UpdateHUDListener extends ChangeListener {

    private ArrayList<Heart> life;
    private DemoBoss boss;

    public UpdateHUDListener(ArrayList<Heart> life) {
        this.life = life;
    }

    @Override
    public void changed(ChangeEvent event, Actor actor) {
        if (!(event instanceof HitEvent)) {
            return;
        }
        if (actor instanceof Player) {
            int currlife = ((Player) actor).getLife();
            int i = 0;

            for (i = life.size() - currlife - 1; i < life.size(); i++) {
                life.get(i).getImage().setColor(Color.BLACK);
            }
        }
    }

}
