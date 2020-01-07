/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

/**
 *
 * @author ferdy
 */
public class Heart extends Image {

    private final Image image;
    private final Texture texture;

    public Heart() {
        texture = new Texture(Gdx.files.internal("texture/player/hud/heart.png"));
        image = new Image(texture);
        // image.setSize(Gdx.graphics.getWidth()/15, Gdx.graphics.getHeight()/12);
    }

    public Image getImage() {
        return image;
    }

}
