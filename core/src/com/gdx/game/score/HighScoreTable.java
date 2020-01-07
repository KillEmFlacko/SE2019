package com.gdx.game.score;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

/**
 * An <code>HighScoreTable</code> object represents an ordered list of a fixed
 * number of scores obtained by the playersof the game. The object handles
 * reading and saving on a default location on the filesystem.
 *
 * @see HighScoreEntry
 * @author Luca Boccia
 * @author Salvatore Gravina
 */
public class HighScoreTable implements Iterable<HighScoreEntry> {

    private static final int TOP = 10;
    private final ArrayList<HighScoreEntry> table;
    //private final File file;
    private final HighScoreFile savemanager;

    /**
     * Constructor of the class. It handles the loading from the default file
     * location on the filesystem.
     *
     * @throws FileNotFoundException
     */
    public HighScoreTable() throws FileNotFoundException {
        //  this.file = new File(path);
        this.table = new ArrayList();
        this.savemanager = new HighScoreFile();

        if (savemanager.file.exists()) {
            ArrayList<HighScoreEntry> tmp = savemanager.loadFromFile();
            Collections.sort(tmp);
            for (int i = 0; i < tmp.size() && i < TOP; i++) {
                this.table.add(tmp.get(i));
            }
        }
    }

    /**
     * Checks if the <code>score</code> would appear in the table after the
     * insetion.
     *
     * @param score
     * @return True if the score might appear in the table, False otherwise.
     */
    public boolean isInTop(long score) { //broken method
        return ((table.isEmpty()) || table.size() < 10 || table.get(table.size() - 1).getScore() < score);
    }

    /**
     * Inserts a new row in the table and saves it on the filesystem.
     *
     * @param nickname a {@link String} representing the Nickname of the player.
     * @param score the score value associated to the player.
     * @throws IOException
     */
    public void insertHighScore(String nickname, long score) throws IOException {
        if (!isInTop(score)) {
            return;
        }

        HighScoreEntry entry = new HighScoreEntry(nickname, score);
        table.add(entry);
        Collections.sort(table);
        if (table.size() > TOP) {
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
