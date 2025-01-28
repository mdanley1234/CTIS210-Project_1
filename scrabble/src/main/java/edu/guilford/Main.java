package edu.guilford;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // // Time the reading process
        // long readStartTime = System.nanoTime();
        // Word[] words = readWordsFromFile("scrabble\\src\\main\\java\\edu\\guilford\\testFiles\\frankenstein.txt");
        // long readEndTime = System.nanoTime();
        // long readDuration = (readEndTime - readStartTime) / 1_000_000; // Convert to milliseconds

        // // Print words and their scores before sorting
        // System.out.println("Before sorting:");
        // for (Word word : words) {
        //     System.out.println(word.getString() + ": " + word.calculateScore() + " points");
        // }

        // // Time the sorting process
        // long sortStartTime = System.nanoTime();
        // Arrays.sort(words);
        // long sortEndTime = System.nanoTime();
        // long sortDuration = (sortEndTime - sortStartTime) / 1_000_000; // Convert to milliseconds

        // // Print words and their scores after sorting
        // System.out.println("After sorting:");
        // for (Word word : words) {
        //     System.out.println(word.getString() + ": " + word.calculateScore() + " points");
        // }

        // // Print the sorting duration and number of words
        // System.out.println("Number of words: " + words.length);
        // System.out.println("Reading took " + readDuration + " milliseconds");
        // System.out.println("Sorting took " + sortDuration + " milliseconds");

        // PROJECT 3 TESTING DEMO (SCRABBLE SECTION)
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of words: ");
        int numWords = scanner.nextInt();
        scanner.close();

        // Create random word array
        Word[] words = new Word[numWords];
        for (int i = 0; i < numWords; i++) {
            words[i] = new Word();
        }

        // Print the unsorted word array
        System.out.println("Unsorted words:");
        for (Word word : words) {
            System.out.println(word.toString());
        }

        // Testing 3 different sorting methods

        // Selection Sort
        mergeSort(words);
        // Print the sorted word array
        System.out.println("Sorted words (Merge):");
        for (Word word : words) {
            System.out.println(word.toString());
        }

        scrambleArray(words);

        selectionSort(words);
        // Print the sorted word array
        System.out.println("Sorted words (Selection):");
        for (Word word : words) {
            System.out.println(word.toString());
        }

        scrambleArray(words);

        arraySort(words);
        // Print the sorted word array
        System.out.println("Sorted words (Array):");
        for (Word word : words) {
            System.out.println(word.toString());
        }

        // Search methods

    }

    
    // Sort Method 1 (Selection Sort)
    static public void selectionSort(Word[] words) {
        long startTime = System.nanoTime();

        for (int i = 0; i < words.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < words.length; j++) {
                if (words[j].compareTo(words[minIndex]) < 0) {
                    minIndex = j;
                }
            }
            Word temp = words[minIndex];
            words[minIndex] = words[i];
            words[i] = temp;
        }

        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1_000_000; // Convert to milliseconds

        System.out.println("Selection sort took " + duration + " milliseconds");
    }

    // Sort Method 2 (Merge Sort)
    static public void mergeSort(Word[] words) {
        long startTime = System.nanoTime();

        if (words.length > 1) {
            Word[] left = java.util.Arrays.copyOfRange(words, 0, words.length / 2);
            Word[] right = java.util.Arrays.copyOfRange(words, words.length / 2, words.length);

            mergeSort(left);
            mergeSort(right);

            merge(words, left, right);
        }

        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1_000_000; // Convert to milliseconds

        // System.out.println("Merge sort took " + duration + " milliseconds");
    }

    private static void merge(Word[] result, Word[] left, Word[] right) {
        int i = 0, j = 0, k = 0;
        while (i < left.length && j < right.length) {
            if (left[i].compareTo(right[j]) <= 0) {
                result[k++] = left[i++];
            } else {
                result[k++] = right[j++];
            }
        }
        while (i < left.length) {
            result[k++] = left[i++];
        }
        while (j < right.length) {
            result[k++] = right[j++];
        }
    }

    // Sort Method 3 (Array Sort)
    static public void arraySort(Word[] words) {
        long startTime = System.nanoTime();

        java.util.Arrays.sort(words);

        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1_000_000; // Convert to milliseconds

        System.out.println("Array sort took " + duration + " milliseconds");
    }

    // Scrambles Array
    static public void scrambleArray(Word[] words) {
        java.util.Random random = new java.util.Random();
        for (int i = words.length - 1; i > 0; i--) {
            int index = random.nextInt(i + 1);
            Word temp = words[index];
            words[index] = words[i];
            words[i] = temp;
        }
    }

    // Testing 3 different searching methods

    // Linear Search Method
    static public int linearSearch(Word[] words, String target) {
        long startTime = System.nanoTime();

        for (int i = 0; i < words.length; i++) {
            if (words[i].getString().equals(target)) {
            long endTime = System.nanoTime();
            long duration = (endTime - startTime) / 1_000_000; // Convert to milliseconds
            System.out.println("Linear search took " + duration + " milliseconds");
            return i;
            }
        }

        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1_000_000; // Convert to milliseconds
        System.out.println("Linear search took " + duration + " milliseconds");
        return -1;
    }

    // Binary Search Method
    static public int binarySearch(Word[] words, String target) {
        long startTime = System.nanoTime();

        int left = 0;
        int right = words.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            int comparison = words[mid].getString().compareTo(target);

            if (comparison == 0) {
            long endTime = System.nanoTime();
            long duration = (endTime - startTime) / 1_000_000; // Convert to milliseconds
            System.out.println("Binary search took " + duration + " milliseconds");
            return mid;
            } else if (comparison < 0) {
            left = mid + 1;
            } else {
            right = mid - 1;
            }
        }

        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1_000_000; // Convert to milliseconds
        System.out.println("Binary search took " + duration + " milliseconds");
        return -1;
    }

    // Built-in Binary Search Method
    static public int builtInBinarySearch(Word[] words, String target) {
        arraySort(words);
        long startTime = System.nanoTime();

        int index = java.util.Arrays.binarySearch(words, new Word(target));

        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1_000_000; // Convert to milliseconds
        System.out.println("Built-in binary search took " + duration + " milliseconds");

        return index;
    }

    // private static ScrabbleSet scrabbleSet; // Standard English Scrabble set
    // private static ScrabbleSet randomScrabbleSet; // Randomized Scrabble set
 
    // public static void main(String[] args) {
    //     scrabbleSet = new ScrabbleSet(Language.ENGLISH);
    //     randomScrabbleSet = new ScrabbleSet();

    //     // Check scrabble sets
    //     System.out.println(scrabbleSet.toString());
    //     System.out.println(randomScrabbleSet.toString());

    //     // Generate words array from file (Select file to test)
    //     // String[] words = readWordsFromFile("scrabble\\src\\main\\java\\edu\\guilford\\testFiles\\random_scrabble_words_only.txt");
    //     String[] words = readWordsFromFile("scrabble\\src\\main\\java\\edu\\guilford\\testFiles\\frankenstein.txt");

    //     // Select which set for evaluation
    //     evaluatePoints(words, scrabbleSet); // Evaluate points for each word
    //     // evaluatePoints(words, randomScrabbleSet); // Evaluate points for each word

    //     // Notes and observations from testing:

    //     /*
    //      * The standardScrabbleSet always returns exquisitely (30 points) as the highest
    //      * scoring word. However, it does not return any invalid words in the Frankenstein
    //      * test because the set includes enough blank tiles to cover the normally
    //      * invalid words. There is no shortest invalid word because in this set, no word
    //      * in Frankestien is invalid. 
    //      */

    //     /*
    //      * Using the randomScrabbleSet, a lot of words end up being invalid because the
    //      * set is randomized and sometimes does not contain enough of the necessary tiles
    //      * to form a word. The highest scoring word changes but typically is 
    //      * characteristically (30 points) or exquisitely (30 points). The shortest invalid
    //      * word I have gotten is the word unknown.
    //      */

    //     // User input
    //     Scanner scanner = new Scanner(System.in);
    //     String inputWord;

    //     while (true) {
    //         System.out.print("Enter a word to score (or type 'exit' to quit): ");
    //         inputWord = scanner.nextLine();

    //         if (inputWord.equalsIgnoreCase("exit")) {
    //             break;
    //         }

    //         // Calculates and prints the points for the input word catching invalid words
    //         int score = scrabbleSet.calculatePoints(inputWord);
    //         int randomScore = randomScrabbleSet.calculatePoints(inputWord);
    //         if (score == -1) {
    //             System.out.println("Standard: The word '" + inputWord + "' is invalid.");
    //         } else {
    //             System.out.println("Standard: The word '" + inputWord + "' scores " + score + " points.");
    //         }
    //         if (randomScore == -1) {
    //             System.out.println("Random: The word '" + inputWord + "' is invalid.");
    //         } else {
    //             System.out.println("Random: The word '" + inputWord + "' scores " + randomScore + " points.");
    //         }
    //     }

    //     scanner.close();
    // }

    // Method extracts words from a file and returns them as an array of word objects
    public static Word[] readWordsFromFile(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            StringBuilder content = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                content.append(line).append(" "); // Combines lines into one string
            }
            return java.util.Arrays.stream(content.toString().split("\\s+|--|-"))
                .map(word -> word.replaceAll("[^a-zA-Z]", ""))
                .filter(word -> !word.isEmpty())
                .map(word -> new Word(word)) // Assume English
                .toArray(Word[]::new);
        } catch (IOException e) {
            e.printStackTrace();
            return new Word[0];
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
