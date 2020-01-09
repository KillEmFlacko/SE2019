package com.gdx.game.listeners.event;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.gdx.game.listeners.event.events.HitEvent;
import com.gdx.game.entities.Player;
import com.gdx.game.entities.enemies.DemoBoss;
import com.gdx.game.listeners.event.events.ScoreChanged;
import com.gdx.game.screens.Heart;
import java.util.ArrayList;

/**
 *
 * @author salvatore
 */
public class UpdateHUDListener extends ChangeListener {

    private final ArrayList<Heart> life;
    private final Image bossBar;
    private final Label scoreLabel;

    public UpdateHUDListener(ArrayList<Heart> life, Image bossBar, Label scoreLabel) {
        this.life = life;
        this.bossBar = bossBar;
        this.scoreLabel = scoreLabel;
    }

    @Override
    public void changed(ChangeEvent event, Actor actor) {
        if (event instanceof ScoreChanged) {
            scoreLabel.setText(Long.toString(((ScoreChanged) event).getScore()));
        } else {
            if (!(event instanceof HitEvent)) {
                return;
            }

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

}
