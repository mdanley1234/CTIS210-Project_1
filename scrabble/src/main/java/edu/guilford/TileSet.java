package edu.guilford;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

/*
 * The TileSet class is used to create and hold a set of tiles that can be used
 * by the ScrabbleSet class to calculate word scores.
 */

public class TileSet {

    // Supported languages
    enum Language {
        ENGLISH
    }
    
    private Map<Character, Integer> LanguageMap = new HashMap<>(); // Maps letters to a location index in the count array
    private Map<Character, Integer> ValueMap = new HashMap<>(); // Maps letters to their value
    private int[] letterCount; // Holds the count of each letter in the tile set
    private ArrayList<Tile> tileSet = new ArrayList<>(); // Holds the tile set

    // Constructor selects language
    public TileSet(Language language) {
        switch (language) {
            case ENGLISH -> generateEnglish();
        }
    }

    // Create the English language map and counter
    public void generateEnglish() {
        // Create counting array (a-z + (space))
        letterCount = new int[27];
        clearCount();

        // Create language map
        for (char c = 'a'; c <= 'z'; c++) {
            LanguageMap.put(c, c - 'a');
        }
        LanguageMap.put(' ', 26);

        // Create value map
        ValueMap.put('a', 1);
        ValueMap.put('b', 3);
        ValueMap.put('c', 3);
        ValueMap.put('d', 2);
        ValueMap.put('e', 1);
        ValueMap.put('f', 4);
        ValueMap.put('g', 2);
        ValueMap.put('h', 4);
        ValueMap.put('i', 1);
        ValueMap.put('j', 8);
        ValueMap.put('k', 5);
        ValueMap.put('l', 1);
        ValueMap.put('m', 3);
        ValueMap.put('n', 1);
        ValueMap.put('o', 1);
        ValueMap.put('p', 3);
        ValueMap.put('q', 10);
        ValueMap.put('r', 1);
        ValueMap.put('s', 1);
        ValueMap.put('t', 1);
        ValueMap.put('u', 1);
        ValueMap.put('v', 4);
        ValueMap.put('w', 4);
        ValueMap.put('x', 8);
        ValueMap.put('y', 4);
        ValueMap.put('z', 10);
        ValueMap.put(' ', 0);
    }
    
    // Add tile (Catches invalid characters)
    public void addTile(char letter) {
        try {
            int value = ValueMap.get(letter);
            tileSet.add(new Tile(letter, value));
            letterCount[LanguageMap.get(letter)]++;
        } catch (NullPointerException e) {
            System.out.println("Error: Character " + letter + " is not defined in the LanguageSet.");
        }
    }
    
    // Remove tile (Catches invalid characters & no such element)
    public void removeTile(char letter) {
        try {
            boolean tileRemoved = false;
            for (int i = 0; i < tileSet.size(); i++) {
                if (tileSet.get(i).getLetter() == letter) {
                    tileSet.remove(i);
                    letterCount[LanguageMap.get(letter)]--;
                    tileRemoved = true;
                    break;
                }
            }
            if (!tileRemoved) {
                throw new NoSuchElementException("No tiles left for character " + letter);
            }
        } catch (NullPointerException e) {
            System.out.println("Error: Character " + letter + " is not defined in the LanguageSet.");
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
        }
    }

    // Get number of tiles of a certain letter
    public int getTileCount(char letter) {
        return letterCount[LanguageMap.get(letter)];
    }

    // Clear the count of all letters
    public void clearCount() {
        for (int i = 0; i < letterCount.length; i++) {
            letterCount[i] = 0;
        }
    }

    // Get the tile set arrayList
    public ArrayList<Tile> getTileSet() {
        return tileSet;
    }

    // Get the value of a letter
    public int getValue(char letter) {
        return ValueMap.get(letter);
    }
}
