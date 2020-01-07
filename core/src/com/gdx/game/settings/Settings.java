/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdx.game.settings;

import com.badlogic.gdx.Gdx;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author raffaele
 */
public class Settings{

    private float volume;
    private static final float DEFAULT_VOLUME = 0.5f;

    public Settings(){
        this.volume = DEFAULT_VOLUME;
    }
    
    public void setVolume(float volume) {
        this.volume = volume;
    }

    public float getVolume() {
        return this.volume;
    }
    
    public void setDefault(){
        this.volume = DEFAULT_VOLUME;
    }
}
