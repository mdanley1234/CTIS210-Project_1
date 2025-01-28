package edu.guilford;

/**
 * The Tile class represents a letter tile used in a word game (like Scrabble),
 * containing a letter and its associated point value.
 */
public class Tile {
    
    private char letter;
    private int value;

    /**
     * Constructs a Tile object with the specified letter and value.
     * 
     * @param letter The letter on the tile.
     * @param value The point value of the tile.
     */
    public Tile(char letter, int value) {
        this.letter = letter;
        this.value = value;
    }
    
    /**
     * Sets the letter of the tile.
     * 
     * @param letter The letter to set on the tile.
     */
    public void setLetter(char letter) {
        this.letter = letter;
    }

    /**
     * Sets the value of the tile.
     * 
     * @param value The point value to set for the tile.
     */
    public void setValue(int value) {
        this.value = value;
    }

    /**
     * Gets the letter of the tile.
     * 
     * @return The letter of the tile.
     */
    public char getLetter() {
        return letter;
    }

    /**
     * Gets the value of the tile.
     * 
     * @return The point value of the tile.
     */
    public int getValue() {
        return value;
    }

    /**
     * Returns a string representation of the Tile object, 
     * combining the letter and its value in a readable format.
     * 
     * @return A string representation of the tile in the format: 
     *         "letter (value)".
     */
    public String toString() {
        return letter + " (" + value + ")";
    }
}
