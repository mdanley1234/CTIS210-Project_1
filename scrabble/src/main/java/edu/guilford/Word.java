package edu.guilford;

import java.util.Random;

import edu.guilford.TileSet.Language;

/**
 * The Word class represents a word string, its corresponding Scrabble set,
 * and provides methods for calculating its score, comparing it with other words,
 * and generating random words.
 */
public class Word implements Comparable<Word> {
    private String string; // The word string
    private static ScrabbleSet defaultScrabbleSet = new ScrabbleSet(Language.ENGLISH); // The scrabble set (default ENGLISH)
    private ScrabbleSet scrabbleSet = defaultScrabbleSet;

    /**
     * Constructs a Word with the specified string and Scrabble set.
     * 
     * @param string The word string.
     * @param scrabbleSet The Scrabble set to be used for scoring.
     */
    public Word(String string, ScrabbleSet scrabbleSet) {
        this.string = string;
        this.scrabbleSet = scrabbleSet;
    }

    /**
     * Constructs a Word with the specified string using the default English Scrabble set.
     * 
     * @param string The word string.
     */
    public Word(String string) {
        this.string = string;
    }

    /**
     * Constructs a random Word with a random length (between 2 and 14 characters),
     * consisting of lowercase English letters, and uses the default English Scrabble set.
     */
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

    /**
     * Returns the word string.
     * 
     * @return The word string.
     */
    public String getString() {
        return string;
    }

    /**
     * Sets the word string.
     * 
     * @param string The word string to set.
     */
    public void setString(String string) {
        this.string = string;
    }

    /**
     * Returns the Scrabble set used to calculate the word's score.
     * 
     * @return The Scrabble set.
     */
    public ScrabbleSet getScrabbleSet() {
        return scrabbleSet;
    }

    /**
     * Sets the Scrabble set used to calculate the word's score.
     * 
     * @param scrabbleSet The Scrabble set to set.
     */
    public void setScrabbleSet(ScrabbleSet scrabbleSet) {
        this.scrabbleSet = scrabbleSet;
    }

    /**
     * Calculates the Scrabble score for the word using the provided Scrabble set.
     * 
     * @return The Scrabble score of the word.
     */
    public int calculateScore() {
        return scrabbleSet.calculatePoints(string);
    }

    /**
     * Compares this word with another word based on their Scrabble scores.
     * If the scores are equal, it compares the words lexicographically.
     * 
     * @param otherWord The other word to compare with.
     * @return A positive number if this word has a higher score, a negative number if it has a lower score,
     *         or 0 if both words have the same score (in which case, they are compared lexicographically).
     */
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

    /**
     * Returns a string representation of the word along with its Scrabble score.
     * 
     * @return A string representation of the word and its score in the format:
     *         "word | Points: score".
     */
    @Override
    public String toString() {
        return string + " | Points: " + calculateScore();
    }
}
