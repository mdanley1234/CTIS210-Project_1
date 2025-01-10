package edu.guilford;

import java.util.Random;

public class Die {
    private int sides;
    private int selectedSide;
    private Random random;

    public Die(int sides) {
        this.sides = sides;
        this.random = new Random();
        roll();
    }

    public void roll() {
        this.selectedSide = random.nextInt(sides) + 1;
    }

    public int getSelectedSide() {
        return selectedSide;
    }

    @Override
    public String toString() {
        return "Die{" +
                "sides=" + sides +
                ", selectedSide=" + selectedSide +
                '}';
    }
}
