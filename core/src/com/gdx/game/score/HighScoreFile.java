/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdx.game.score;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * <code>HighScoreFile</code> handles the loading and the saving of a {@link HighScoreTable}.
 * It has a default path on the filesystem that locates the saved file.
 * 
 * @author Salvatore Gravina
 */
public class HighScoreFile{
    public final File file; //should be private
    private static final String PATH = new File("").getAbsolutePath().concat("/highscore.txt");
    
    public HighScoreFile(){
        this.file= new File(PATH);
    }
    
    public void saveOnFile(ArrayList<HighScoreEntry> table) throws FileNotFoundException, IOException{
        if (!this.file.exists()){
            this.file.createNewFile();
        }
        try (PrintWriter writer = new PrintWriter(file)) {
            for (HighScoreEntry x : table){
                writer.print(x.getNickname() + " " + x.getScore()+"\n");
            }
        }
    }
    
    public ArrayList<HighScoreEntry> loadFromFile() throws FileNotFoundException {
        
        ArrayList<HighScoreEntry> list = new ArrayList();
        Scanner scan = new Scanner(this.file);
        
        while(scan.hasNext()){
            HighScoreEntry player = new HighScoreEntry(scan.next(), scan.nextLong());
            list.add(player);
        }
        
        return list;
    }
    
    
}
