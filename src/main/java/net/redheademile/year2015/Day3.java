package net.redheademile.year2015;

import net.redheademile.Main;

import java.util.HashSet;
import java.util.Set;

public class Day3 {

    /**
     * Get the input data of day 3 of the advent of code 2015.
     */
    private static String getInput() {
        return Main.getInput("2015_3.txt");
    }

    public static void partOne() {
        String input = getInput();

        Set<String> houses = new HashSet<>();

        int santaX = 0;
        int santaY = 0;
        houses.add(santaX + "x" + santaY);

        for (char c : input.toCharArray()) {
            switch (c) {
                case '^':
                    santaY++;
                    break;
                case 'v':
                    santaY--;
                    break;
                case '>':
                    santaX++;
                    break;
                case '<':
                    santaX--;
                    break;
            }
            houses.add(santaX + "x" + santaY);
        }

        System.out.println("[Part 1] Amount of delivered houses: " + houses.size());
    }

    public static void partTwo() {
        String input = getInput();

        Set<String> houses = new HashSet<>();

        int santaX = 0;
        int santaY = 0;
        int robotSantaX = 0;
        int robotSantaY = 0;
        houses.add(santaX + "x" + santaY);

        boolean santaTurn = true;
        for (char c : input.toCharArray()) {
            if (santaTurn) {
                switch (c) {
                    case '^':
                        santaY++;
                        break;
                    case 'v':
                        santaY--;
                        break;
                    case '>':
                        santaX++;
                        break;
                    case '<':
                        santaX--;
                        break;
                }
                houses.add(santaX + "x" + santaY);
            }
            else {
                switch (c) {
                    case '^':
                        robotSantaY++;
                        break;
                    case 'v':
                        robotSantaY--;
                        break;
                    case '>':
                        robotSantaX++;
                        break;
                    case '<':
                        robotSantaX--;
                        break;
                }
                houses.add(robotSantaX + "x" + robotSantaY);
            }
            santaTurn = !santaTurn;
        }

        System.out.println("[Part 2] Amount of delivered presents: " + houses.size());
    }
}
