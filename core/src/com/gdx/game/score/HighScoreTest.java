/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdx.game.score;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author salvatore
 */
public class HighScoreTest {
    private final HighScoreTable table;

    public HighScoreTest() throws FileNotFoundException {
        this.table = new HighScoreTable("/home/salvatore/Documenti/highscore.txt");
    }
    
    public static void main(String args[]) throws FileNotFoundException, IOException{
        HighScoreTest test = new HighScoreTest();
        test.table.insertHighScore("sei", 1000);
        for(HighScoreEntry x : test.table){
            System.out.println(x);
        }
        
    }
    
}
