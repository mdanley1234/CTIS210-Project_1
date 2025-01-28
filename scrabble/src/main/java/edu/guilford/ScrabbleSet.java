package edu.guilford;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ScrabbleSet {


    /*
     * The ScrabbleSet class is used to create and hold a TileSet object that
     * contains 100 tiles. If a language is specified, the tile set will be created
     * according to a csv file that contains the letter distribution for that
     * language. If no language is specified, the tile set will be created randomly,
     * with a specified minimum letter count and maximum vowel count.
     */

    private TileSet tileSet; // The tile set

    // Standard 100 tile set using csv file for letter distribution
    public ScrabbleSet(TileSet.Language language) {
        tileSet = new TileSet(language);
        String csvFileLocation = new String();

        // Select language
        switch (language) {
            case ENGLISH:
                csvFileLocation = "scrabble\\src\\main\\java\\edu\\guilford\\letterDistributions\\English_Standard.csv";
            break;
            default:
                throw new IllegalArgumentException("Unsupported language: " + language);
        }

        // Read the CSV file and load the tile set
        try (BufferedReader br = new BufferedReader(new FileReader(csvFileLocation))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Process the line
                String[] values = line.split(",");
                char letter = values[0].charAt(0);
                // Catch blank tiles
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

    // Randomized tile set of 100 tiles | NOTE: ASSUMES ENGLISH
    // Minimum of 1 of each letter, individual vowel maximum of 6
    public ScrabbleSet() {
        tileSet = new TileSet(TileSet.Language.ENGLISH);
        int letterMin = 1; // Minimum of 1 of each letter (Can only be 0, 1, 2, or 3)
        int vowelMax = 5; // Vowel maximum of 5

        // Generate minimum requirements (letterMin)
        for (char letter = 'a'; letter <= 'z'; letter++) {
            for (int i = 0; i < letterMin; i++) {
                tileSet.addTile(letter);
            }
        }
        for (int i = 0; i < letterMin; i++) {
            tileSet.addTile(' ');
        }

        // Complete randomized generation
        while (tileSet.getTileSet().size() < 100) {
            // Select random letter (a-z + (blank))
            char letter = (char) (Math.random() * 27 + 'a' - 1);
            if (letter == '`') {
                letter = ' ';
            }

            // Prevent vowel maximum from being exceeded
            if (!(letter == 'a' || letter == 'e' || letter == 'i' || letter == 'o' || letter == 'u') || tileSet.getTileCount(letter) < vowelMax) {
                tileSet.addTile(letter);
            }
        }
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (char letter = 'a'; letter <= 'z'; letter++) {
            sb.append(letter).append(": ").append(tileSet.getTileCount(letter)).append("\n");
        }
        sb.append("blank: ").append(tileSet.getTileCount(' ')).append("\n");
        return sb.toString();
    }

    // Returns points that a word is worth | Currently only works in ENGLISH
    public int calculatePoints(String word) {
        // Create an array to store the count of each letter
        int[] letterCounts = new int[26];
        for (char letter : word.toCharArray()) {
            if (Character.isLetter(letter)) {
                letterCounts[Character.toLowerCase(letter) - 'a']++;
            } else {
                throw new IllegalArgumentException("Invalid character in word: " + letter);
            }
        }

        // Create points counter and blanks counter
        int points = 0; // Holds the total points for the word
        int blanks = 0; // Holds the number of blanks needed for word to be processed

        // Iterate through the letterCounts array to process each letter and its count
        for (int i = 0; i < letterCounts.length; i++) {
            char letter = (char) (i + 'a');
            for (int j = 0; j < letterCounts[i]; j++) {
                if (j < tileSet.getTileCount(letter)) {
                    points += tileSet.getValue(letter); // Tiles are tracked from the Tile Set for each letter
                } else {
                    blanks++; // If there are not enough tiles, a blank is used
                }
            }
        }

        // If there are enough blanks to cover the excess letters, calculate the points
        if (blanks <= tileSet.getTileCount(' ')) {
            return points;
        }
        else {
            return -1; // Otherwise, return -1 to indicate the word is invalid
        }
    }
}
