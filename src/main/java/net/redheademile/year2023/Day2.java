package net.redheademile.year2023;

import net.redheademile.Main;

public class Day2 {

    /**
     * Get the input data of day 2 of the advent of code 2023.
     */
    private static String[] getInput() {
        return Main.getInput("2023_2.txt").split("\n");
    }

    /**
     * Sum the ids of the games that have a red, green or blue with a maximal amount of 12, 13 or 14.
     */
    public static void partOne() {
        String[] lines = getInput();

        int maxRed = 12;
        int maxGreen = 13;
        int maxBlue = 14;

        int totalGameIds = 0;
        lineLoop: for (String line : lines) {
            line = line.trim().replace("\r", "");
            String[] lineSegments = line.split(": ");

            int gameId = Integer.parseInt(lineSegments[0].split(" ")[1]);
            for (String subset : lineSegments[1].split("; ")) {
                int red = 0;
                int green = 0;
                int blue = 0;

                for (String color : subset.split(", ")) {
                    String[] colorSegments = color.split(" ");
                    int amount = Integer.parseInt(colorSegments[0]);
                    String colorName = colorSegments[1];

                    switch (colorName) {
                        case "red":
                            red += amount;
                            break;
                        case "green":
                            green += amount;
                            break;
                        case "blue":
                            blue += amount;
                            break;
                        default:
                            throw new RuntimeException("Unknown color: " + colorName);
                    }
                }

                if (red > maxRed || green > maxGreen || blue > maxBlue)
                    continue lineLoop;
            }

            totalGameIds += gameId;
        }

        System.out.println("[Part 1] Sum of game ids: " + totalGameIds);
    }

    /**
     * Sum the powers of the games.<br/>
     * The power of a game is the product of the maximal amount of red, green and blue.
     */
    public static void partTwo() {
        String[] lines = getInput();

        int totalPowers = 0;
        for (String line : lines) {
            line = line.trim().replace("\r", "");
            String[] lineSegments = line.split(": ");

            int minimumRed = 0;
            int minimumGreen = 0;
            int minimumBlue = 0;

            for (String subset : lineSegments[1].split("; ")) {
                for (String color : subset.split(", ")) {
                    String[] colorSegments = color.split(" ");
                    int amount = Integer.parseInt(colorSegments[0]);
                    String colorName = colorSegments[1];

                    switch (colorName) {
                        case "red":
                            minimumRed = Math.max(minimumRed, amount);
                            break;
                        case "green":
                            minimumGreen = Math.max(minimumGreen, amount);
                            break;
                        case "blue":
                            minimumBlue = Math.max(minimumBlue, amount);
                            break;
                        default:
                            throw new RuntimeException("Unknown color: " + colorName);
                    }
                }
            }

            totalPowers += minimumRed * minimumGreen * minimumBlue;
        }

        System.out.println("[Part 2] Sum of powers: " + totalPowers);
    }
}
