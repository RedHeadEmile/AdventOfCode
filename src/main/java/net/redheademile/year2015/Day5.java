package net.redheademile.year2015;

import net.redheademile.Main;

public class Day5 {
    private static String[] getInput() {
        return Main.getInput("2015_5.txt").split("\n");
    }

    public static void partOne() {
        String[] input = getInput();

        int niceStringsAmount = 0;
        for (String string : input) {
            if (string.contains("ab") || string.contains("cd") || string.contains("pq") || string.contains("xy"))
                continue;

            int vowels = 0;
            boolean doubleAppearance = false;

            char previousChar = 0;
            for (char c : string.toCharArray()) {
                if (c == previousChar)
                    doubleAppearance = true;

                if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u')
                    ++vowels;

                if (vowels >= 3 && doubleAppearance) {
                    ++niceStringsAmount;
                    break;
                }

                previousChar = c;
            }
        }

        System.out.println("[Part 1] Amount of good strings: " + niceStringsAmount);
    }

    public static void partTwo() {
        String[] input = getInput();

        int niceStringsAmount = 0;
        for (String string : input) {
            boolean containsDoublePair = false;

            for (int i = 0; i < string.length() - 2; i++) {
                String pair = string.substring(i, i + 2);
                containsDoublePair = string.indexOf(pair, i + 2) != -1;
                if (containsDoublePair)
                    break;
            }

            if (!containsDoublePair)
                continue;

            for (int i = 2; i < string.toCharArray().length; i++) {
                char first = string.charAt(i - 2);
                char third = string.charAt(i);

                if (first == third) {
                    ++niceStringsAmount;
                    break;
                }
            }
        }

        System.out.println("[Part 2] Amount of good strings: " + niceStringsAmount);
    }
}
