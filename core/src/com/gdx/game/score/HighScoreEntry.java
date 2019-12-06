/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdx.game.score;

/**
 *
 * @author ellbock
 */
public class HighScoreEntry implements Comparable<HighScoreEntry> {

    private final String nickname;
    private final long score;

    public HighScoreEntry(String nickname, long score) {    // ?
        this.nickname = nickname;
        this.score = score;
    }

    @Override
    public String toString() {
        return "HighScoreEntry{" + "nickname=" + nickname + ", score=" + score + '}';
    }

    public String getNickname() {
        return nickname;
    }

    public long getScore() {
        return score;
    }

    @Override
    public int compareTo(HighScoreEntry h) {
        return Long.compare(h.getScore(), this.getScore());
    }
}
