package net.redheademile;

public class TwentyTwentyThree {
    //#region One
    /**
     * Sum the concatenation of the first and last digit of each line.<br/>
     * E.g. for a line "abc123def" -> 1 + 3 = 13<br/>
     * Do it for all lines and sum the results.
     * @param lines The lines to sum
     */
    public static void onePartOne(String[] lines) {
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
     * @param lines The lines to sum
     */
    public static void onePartTwo(String[] lines) {
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

    /**
     * Run the code of day 1 of the advent of code 2023.
     */
    public static void one() {
        String input = Main.getInput("2023_1.txt");
        String[] lines = input.split("\n");

        System.out.println("Day 1 of the advent of code 2023:");
        onePartOne(lines);
        onePartTwo(lines);
    }
    //#endregion


    //#region Two
    public static void twoPartOne(String[] lines) {
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

    public static void twoPartTwo(String[] lines) {
        int totalPowers = 0;
        for (String line : lines) {
            line = line.trim().replace("\r", "");
            String[] lineSegments = line.split(": ");

            int minimumRed = 0;
            int minimumGreen = 0;
            int minimumBlue = 0;

            int gameId = Integer.parseInt(lineSegments[0].split(" ")[1]);
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

    public static void two() {
        String input = Main.getInput("2023_2.txt");
        String[] lines = input.split("\n");

        System.out.println("Day 2 of the advent of code 2023:");
        twoPartOne(lines);
        twoPartTwo(lines);
    }
    //#endregion
}
