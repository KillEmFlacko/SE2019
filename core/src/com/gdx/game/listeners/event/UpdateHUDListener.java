package com.gdx.game.listeners.event;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.gdx.game.listeners.event.events.HitEvent;
import com.gdx.game.entities.Player;
import com.gdx.game.entities.bosses.DemoBoss;
import com.gdx.game.screens.Heart;
import java.util.ArrayList;

/**
 *
 * @author salvatore
 */
public class UpdateHUDListener extends ChangeListener {

    private ArrayList<Heart> life;
    private Image bossBar;

    public UpdateHUDListener(ArrayList<Heart> life, Image bossBar) {
        this.life = life;
        this.bossBar = bossBar;
    }

    @Override
    public void changed(ChangeEvent event, Actor actor) {
        if (!(event instanceof HitEvent)) {
            return;
        }
//        if (actor instanceof Player) {
//            int currlife = ((Player) actor).getLife();
//            for (int i = 0; i < life.size() ; i++) {
//                if(i > currlife){
//                    life.get(i).getImage().setColor(Color.BLACK);
//                }
//            }
//        }

        if (actor instanceof Player) {
            int currlife = ((Player) actor).getLife();

            for (int i = life.size() - 1; i > currlife - 1; i--) {
                life.get(i).getImage().setColor(Color.BLACK);
            }
        } else if (actor instanceof DemoBoss) {
            bossBar.setSize(((DemoBoss) actor).getLife() * Gdx.graphics.getWidth() / 150, 20);
        }
    }

}
