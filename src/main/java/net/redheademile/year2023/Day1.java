package net.redheademile.year2023;

import net.redheademile.Main;

public class Day1 {

    /**
     * Get the input data of day 1 of the advent of code 2023.
     */
    private static String[] getInput() {
        return Main.getInput("2023_1.txt").split("\n");
    }

    /**
     * Sum the concatenation of the first and last digit of each line.<br/>
     * E.g. for a line "abc123def" -> 1 + 3 = 13<br/>
     * Do it for all lines and sum the results.
     */
    public static void partOne() {
        String[] lines = getInput();

        int total = 0;
        for (String line : lines) {
            char firstDigit = 0;
            char lastDigit = 0;

            for (char c : line.toCharArray()) {
                if (Character.isDigit(c)) {
                    if (firstDigit == 0)
                        firstDigit = c;
                    lastDigit = c;
                }
            }

            total += Integer.parseInt("" + firstDigit + lastDigit);
        }

        System.out.println("[Part 1] Sum of first and last digit of each line: " + total);
    }

    /**
     * Sum the concatenation of the first and last digit of each line but count digit writen with letters.<br/>
     * E.g. for a line "nine123def" -> 9 + 3 = 93<br/>
     * Do it for all lines and sum the results.
     */
    public static void partTwo() {
        String[] lines = getInput();
        String[] numbers = new String[] { "zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine" };

        int total = 0;
        for (String line : lines) {
            int firstDigit = -1;
            int lastDigit = -1;

            for (int startIndex = 0; startIndex < line.length(); startIndex++) {
                for (int number = 0; number < numbers.length; number++) {
                    if (!line.startsWith(numbers[number], startIndex))
                        continue;

                    if (firstDigit == -1)
                        firstDigit = number;
                    lastDigit = number;
                    break;
                }

                if (Character.isDigit(line.charAt(startIndex))) {
                    if (firstDigit == -1)
                        firstDigit = Character.getNumericValue(line.charAt(startIndex));
                    lastDigit = Character.getNumericValue(line.charAt(startIndex));
                }
            }

            if (firstDigit == -1 || lastDigit == -1)
                throw new RuntimeException("Invalid line: " + line);

            total += Integer.parseInt(firstDigit + "" + lastDigit);
        }

        System.out.println("[Part 2] Sum of first and last digit of each line: " + total);
    }
}
