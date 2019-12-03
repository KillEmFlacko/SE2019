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
import java.util.Collections;
import java.util.Iterator;
import java.util.Scanner;

/**
 *
 * @author ellbock
 */
public class HighScoreTable implements Iterable {
    private static final int TOP = 10;
    private final ArrayList<HighScoreEntry> table;
    private final File file;
    
    
    public HighScoreTable(String path) throws FileNotFoundException{
        this.file = new File(path);
        this.table = new ArrayList();
        
        if (file.exists()){
            ArrayList<HighScoreEntry> tmp = loadFromFile();
            Collections.sort(tmp);
            for(int i=0; i<TOP; i++){
                this.table.add(tmp.get(i));
            }
        }
    }
    
    
    public boolean isInTop(long score){
        return (table.get(TOP-1).getScore() < score);
    }
    
    
    public void insertHighScore(String nickname, long score) throws IOException{
        if (!isInTop(score))
            return;
        
        HighScoreEntry entry = new HighScoreEntry(nickname, score);
        table.add(entry);
        Collections.sort(table);
        table.remove(TOP);
        saveOnFile();
    }
    
    
    @Override
    public Iterator iterator() {
        return table.iterator();
    }
    
    
    private ArrayList<HighScoreEntry> loadFromFile() throws FileNotFoundException {
        
        ArrayList<HighScoreEntry> list = new ArrayList();
        Scanner scan = new Scanner(file);
        
        while(scan.hasNext()){
            HighScoreEntry player = new HighScoreEntry(scan.next(), scan.nextLong());
            list.add(player);
        }
        
        return list;
    }
    
    
    private void saveOnFile() throws FileNotFoundException, IOException{
        if (!this.file.exists()){
            this.file.createNewFile();
        }
        
        PrintWriter writer = new PrintWriter(file);
        
        for (HighScoreEntry x : this.table){
            writer.println(x.getNickname() + " " + x.getScore());
        }
    }
    
}
