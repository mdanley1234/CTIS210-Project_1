package edu.guilford;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * The ScrabbleSet class represents a collection of 100 tiles for use in Scrabble-like games.
 * It provides functionality to create a tile set based on a specific language or a randomized set.
 * Additionally, it can calculate the point value of a given word based on the tiles available in the set.
 */
public class ScrabbleSet {

    private TileSet tileSet; // The tile set containing the tiles for the Scrabble game.

    /**
     * Creates a Scrabble tile set based on a specified language.
     * The tile set is populated using a CSV file containing the letter distribution for the chosen language.
     * 
     * @param language The language to be used for the tile set (e.g., ENGLISH).
     * @throws IllegalArgumentException If the specified language is unsupported.
     */
    public ScrabbleSet(TileSet.Language language) {
        tileSet = new TileSet(language);
        String csvFileLocation = new String();

        // Select the appropriate CSV file for the given language.
        switch (language) {
            case ENGLISH:
                csvFileLocation = "scrabble\\src\\main\\java\\edu\\guilford\\letterDistributions\\English_Standard.csv";
                break;
            default:
                throw new IllegalArgumentException("Unsupported language: " + language);
        }

        // Read the CSV file and populate the tile set.
        try (BufferedReader br = new BufferedReader(new FileReader(csvFileLocation))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Process each line in the CSV file.
                String[] values = line.split(",");
                char letter = values[0].charAt(0);
                // Handle blank tiles.
                if (letter == '_') {
                    letter = ' ';
                }

                int count = Integer.parseInt(values[1]);
                for (int i = 0; i < count; i++) {
                    tileSet.addTile(letter);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates a randomized Scrabble tile set with 100 tiles.
     * Assumes the English language for letter distribution. Enforces a minimum count of each letter 
     * and a maximum count for vowels.
     */
    public ScrabbleSet() {
        tileSet = new TileSet(TileSet.Language.ENGLISH);
        int letterMin = 1; // Minimum of 1 of each letter (a-z and blank).
        int vowelMax = 5; // Maximum number of any individual vowel.

        // Add the minimum required count for each letter.
        for (char letter = 'a'; letter <= 'z'; letter++) {
            for (int i = 0; i < letterMin; i++) {
                tileSet.addTile(letter);
            }
        }
        for (int i = 0; i < letterMin; i++) {
            tileSet.addTile(' ');
        }

        // Randomly generate the remaining tiles while enforcing vowel constraints.
        while (tileSet.getTileSet().size() < 100) {
            char letter = (char) (Math.random() * 27 + 'a' - 1);
            if (letter == '`') {
                letter = ' ';
            }

            // Ensure the vowel maximum is not exceeded.
            if (!(letter == 'a' || letter == 'e' || letter == 'i' || letter == 'o' || letter == 'u') 
                    || tileSet.getTileCount(letter) < vowelMax) {
                tileSet.addTile(letter);
            }
        }
    }

    /**
     * Returns a string representation of the Scrabble tile set, 
     * displaying the count of each letter and blank tiles.
     * 
     * @return A string showing the counts of each letter and blank tiles in the tile set.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (char letter = 'a'; letter <= 'z'; letter++) {
            sb.append(letter).append(": ").append(tileSet.getTileCount(letter)).append("\n");
        }
        sb.append("blank: ").append(tileSet.getTileCount(' ')).append("\n");
        return sb.toString();
    }

    /**
     * Calculates the total point value of a given word based on the tile values in the set.
     * If the word cannot be formed due to insufficient tiles, the method returns -1.
     * 
     * @param word The word to calculate the point value for.
     * @return The total point value of the word, or -1 if the word cannot be formed.
     * @throws IllegalArgumentException If the word contains invalid characters.
     */
    public int calculatePoints(String word) {
        int[] letterCounts = new int[26];
        for (char letter : word.toCharArray()) {
            if (Character.isLetter(letter)) {
                letterCounts[Character.toLowerCase(letter) - 'a']++;
            } else {
                throw new IllegalArgumentException("Invalid character in word: " + letter);
            }
        }

        int points = 0; // Total points for the word.
        int blanks = 0; // Number of blank tiles needed.

        for (int i = 0; i < letterCounts.length; i++) {
            char letter = (char) (i + 'a');
            for (int j = 0; j < letterCounts[i]; j++) {
                if (j < tileSet.getTileCount(letter)) {
                    points += tileSet.getValue(letter);
                } else {
                    blanks++;
                }
            }
        }

        // If there are enough blanks to cover the excess letters, return the total points.
        if (blanks <= tileSet.getTileCount(' ')) {
            return points;
        } else {
            return -1; // Not enough tiles to form the word.
        }
    }
}