package com.gdx.game.settings;

/**
 *
 * @author raffaele
 */
public class Settings {

    private float volume;
    private static final float DEFAULT_VOLUME = 0.5f;

    public Settings() {
        this.volume = DEFAULT_VOLUME;
    }

    public void setVolume(float volume) {
        this.volume = volume;
    }

    public float getVolume() {
        return this.volume;
    }

    public void setDefault() {
        this.volume = DEFAULT_VOLUME;
    }
}
