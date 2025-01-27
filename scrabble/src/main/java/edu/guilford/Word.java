package edu.guilford;

import java.util.Random;

import edu.guilford.TileSet.Language;

public class Word implements Comparable<Word> {
    private String string; // The word string
    private static ScrabbleSet defaultScrabbleSet = new ScrabbleSet(Language.ENGLISH); // The scrabble set (default ENGLISH)
    private ScrabbleSet scrabbleSet = defaultScrabbleSet;

    public Word(String string, ScrabbleSet scrabbleSet) {
        this.string = string;
        this.scrabbleSet = scrabbleSet;
    }

    // Assume English
    public Word(String string) {
        this.string = string;
    }

    // Word
    public Word() {
        Random random = new Random();
        int length = random.nextInt(13) + 2; // Random length between 2 and 14
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            char c = (char) (random.nextInt(26) + 'a'); // Random lowercase letter
            sb.append(c);
        }
        this.string = sb.toString();
        this.scrabbleSet = new ScrabbleSet(Language.ENGLISH); // Assuming ScrabbleSet has a default constructor for normal English set
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    public ScrabbleSet getScrabbleSet() {
        return scrabbleSet;
    }

    public void setScrabbleSet(ScrabbleSet scrabbleSet) {
        this.scrabbleSet = scrabbleSet;
    }

    public int calculateScore() {
        return scrabbleSet.calculatePoints(string);
    }

    @Override
    public int compareTo(Word otherWord) {
        if (calculateScore() > otherWord.calculateScore()) {
            return 1;
        } else if (calculateScore() < otherWord.calculateScore()) {
            return -1;
        } else {
            return string.compareTo(otherWord.getString());
        }

    }
}
