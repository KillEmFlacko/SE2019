/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdx.game.settings;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import java.io.Serializable;

/**
 *
 * @author raffaele
 */
public class Settings implements Serializable {

    private static Slider volume;
    private static Music music;
    private static final float DEFAULT_VOLUME = 0.5f;
    
   


    public static void setVolume(Slider volume) {
        Settings.volume = volume;
    }

    public static void setMusic(Music music) {
        Settings.music = music;
    }

    public static Slider getVolume() {
        return volume;
    }

    public static Music getMusic() {
        return music;
    }

    public static float getDEFAULT_VOLUME() {
        return DEFAULT_VOLUME;
    }

    public static void initAudio() {
        music = Gdx.audio.newMusic(Gdx.files.internal("audio/menu/AbandonedWindmill.mp3"));
        music.play();
        music.setLooping(true);
        music.setVolume(DEFAULT_VOLUME);
    }

}
