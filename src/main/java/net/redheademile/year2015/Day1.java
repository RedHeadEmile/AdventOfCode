package net.redheademile.year2015;

import net.redheademile.Main;

public class Day1 {

    /**
     * Get the input data of day 1 of the advent of code 2015.
     */
    private static String getInput() {
        return Main.getInput("2015_1.txt");
    }

    /**
     * Count the amount of floors Santa has to go up or down.
     */
    public static void partOne() {
        String input = getInput();

        int floor = 0;
        for (char c : input.toCharArray()) {
            switch (c) {
                case '(':
                    floor++;
                    break;
                case ')':
                    floor--;
                    break;
            }
        }

        System.out.println("[Part 1] Floor: " + floor);
    }

    /**
     * Find the position of the first character that causes Santa to enter the basement.
     */
    public static void partTwo() {
        String input = getInput();

        int i = 0;
        int floor = 0;
        for (; i < input.length() && floor >= 0; i++) {
            switch (input.charAt(i)) {
                case '(':
                    floor++;
                    break;
                case ')':
                    floor--;
                    break;
            }
        }

        System.out.println("[Part 2] Position: " + i);
    }
}
