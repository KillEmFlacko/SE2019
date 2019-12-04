/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdx.game.score;

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
public class HighScoreTable implements Iterable<HighScoreEntry> {
    private static final int TOP = 10;
    private final ArrayList<HighScoreEntry> table;
    //private final File file;
    private final HighScoreFile savemanager;
    
    
    public HighScoreTable(String path) throws FileNotFoundException{
      //  this.file = new File(path);
        this.table = new ArrayList();
        this.savemanager = new HighScoreFile(path);
        
        if (savemanager.file.exists()){
            ArrayList<HighScoreEntry> tmp = savemanager.loadFromFile();
            Collections.sort(tmp);
            for(int i=0; i<tmp.size() && i<TOP; i++){
                this.table.add(tmp.get(i));
            }
        }
    }
    
    
    public boolean isInTop(long score){ //broken method
        return ((table.isEmpty()) || table.get(table.size()-1).getScore() < score);
    }
    
    
    public void insertHighScore(String nickname, long score) throws IOException{
        if (!isInTop(score))
            return;
        
        HighScoreEntry entry = new HighScoreEntry(nickname, score);
        table.add(entry);
        Collections.sort(table);
        if(table.size()>TOP){
            table.remove(TOP);
        }
        savemanager.saveOnFile(this.table);
    }
    
    
    @Override
    public Iterator iterator() {
        return table.iterator();
        
    }
    
    
//    private ArrayList<HighScoreEntry> loadFromFile() throws FileNotFoundException {
//        
//        ArrayList<HighScoreEntry> list = new ArrayList();
//        Scanner scan = new Scanner(savemanager.file);
//        
//        while(scan.hasNext()){
//            HighScoreEntry player = new HighScoreEntry(scan.next(), scan.nextLong());
//            list.add(player);
//        }
//        
//        return list;
//    }
    
    
//    private void saveOnFile() throws FileNotFoundException, IOException{
//        if (!this.savemanager.file.exists()){
//            this.savemanager.file.createNewFile();
//        }
//        try (PrintWriter writer = new PrintWriter(savemanager.file)) {
//            for (HighScoreEntry x : this.table){
//                writer.print(x.getNickname() + " " + x.getScore()+"\n");
//            }
//        }
//    }
    
}
