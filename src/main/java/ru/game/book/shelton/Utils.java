package ru.game.book.shelton;

import java.util.Random;

public class Utils {
    private static final Random random = new Random();

    public static int rollDice() {
        return random.nextInt(6) + 1;
    }

    public static int[] rollTwoDice() {
        return new int[]{rollDice(), rollDice()};
    }
}
