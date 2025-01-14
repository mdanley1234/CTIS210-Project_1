package edu.guilford;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import edu.guilford.TileSet.Language;

public class Main {

    private static ScrabbleSet scrabbleSet; // Standard English Scrabble set
    private static ScrabbleSet randomScrabbleSet; // Randomized Scrabble set
 
    public static void main(String[] args) {
        scrabbleSet = new ScrabbleSet(Language.ENGLISH);
        randomScrabbleSet = new ScrabbleSet();

        // Check scrabble sets
        System.out.println(scrabbleSet.toString());
        System.out.println(randomScrabbleSet.toString());

        // Generate words array from file (Select file to test)
        // String[] words = readWordsFromFile("scrabble\\src\\main\\java\\edu\\guilford\\testFiles\\random_scrabble_words_only.txt");
        String[] words = readWordsFromFile("scrabble\\src\\main\\java\\edu\\guilford\\testFiles\\frankenstein.txt");

        // Select which set for evaluation
        evaluatePoints(words, scrabbleSet); // Evaluate points for each word
        // evaluatePoints(words, randomScrabbleSet); // Evaluate points for each word

        // Notes and observations from testing:

        /*
         * The standardScrabbleSet always returns exquisitely (30 points) as the highest
         * scoring word. However, it does not return any invalid words in the Frankenstein
         * test because the set includes enough blank tiles to cover the normally
         * invalid words. There is no shortest invalid word because in this set, no word
         * in Frankestien is invalid. 
         */

        /*
         * Using the randomScrabbleSet, a lot of words end up being invalid because the
         * set is randomized and sometimes does not contain enough of the necessary tiles
         * to form a word. The highest scoring word changes but typically is 
         * characteristically (30 points) or exquisitely (30 points). The shortest invalid
         * word I have gotten is the word unknown.
         */

        // User input
        Scanner scanner = new Scanner(System.in);
        String inputWord;

        while (true) {
            System.out.print("Enter a word to score (or type 'exit' to quit): ");
            inputWord = scanner.nextLine();

            if (inputWord.equalsIgnoreCase("exit")) {
                break;
            }

            // Calculates and prints the points for the input word catching invalid words
            int score = scrabbleSet.calculatePoints(inputWord);
            int randomScore = randomScrabbleSet.calculatePoints(inputWord);
            if (score == -1) {
                System.out.println("Standard: The word '" + inputWord + "' is invalid.");
            } else {
                System.out.println("Standard: The word '" + inputWord + "' scores " + score + " points.");
            }
            if (randomScore == -1) {
                System.out.println("Random: The word '" + inputWord + "' is invalid.");
            } else {
                System.out.println("Random: The word '" + inputWord + "' scores " + randomScore + " points.");
            }
        }

        scanner.close();
    }

    // Method extracts words from a file and returns them as an array
    public static String[] readWordsFromFile(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            StringBuilder content = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                content.append(line).append(" "); // Combines lines into one string
            }
            String[] words = content.toString().split("\\s+|--|-"); // Seperate words by spaces and hyphens
            return java.util.Arrays.stream(words) // Filter out invalid characters
                    .map(word -> word.replaceAll("[^a-zA-Z]", ""))
                    .filter(word -> !word.isEmpty())
                    .toArray(String[]::new);
        } catch (IOException e) {
            e.printStackTrace();
            return new String[0];
        }
    }

    // Method evaluates the points for each word in the array and prints the results
    public static void evaluatePoints(String[] words, ScrabbleSet thisSet) {
        String maxWord = "";
        int maxPoints = 0;
        java.util.List<String> invalidWords = new java.util.ArrayList<>();

        for (String word : words) {
            int points = thisSet.calculatePoints(word);
            System.out.println(word + ": " + points + " points"); // Print each word

            // Track word with the most points
            if (points > maxPoints) {
                maxPoints = points;
                maxWord = word;
            }

            // Track invalid words
            if (points == -1) {
                invalidWords.add(word);
            }
        }

        // Print statistics
        System.out.println("Word with the most points: " + maxWord + " (" + maxPoints + " points)");
        System.out.println("Invalid words: " + String.join(", ", invalidWords));
    }

}
