package edu.guilford;

import java.util.ArrayList;

public class ScrabbleSet {

    public ScrabbleSet() {

    }

    // Generates a set of tiles for the English version of Scrabble of size totalCount
    public void generateEnglish(int totalCount) {
        // Create the tile set
        ArrayList<Character> tiles = new ArrayList<>();
        for (char c = 'A'; c <= 'Z'; c++) {
            tiles.add(c);
        }
        tiles.add(' '); // Adding the blank space

        // Populate tiles
    }

}
