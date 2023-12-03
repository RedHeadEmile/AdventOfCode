package net.redheademile.year2015;

import net.redheademile.Main;

public class Day8 {
    private static String[] getInput() {
        return Main.getInput("2015_8.txt").split("\n");
    }

    public static void partOne() {
        String[] input = getInput();

        int literalMemoryDiff = 0;
        for (String line : input) {
            line = line.replace("\r", "");
            if (line.isEmpty())
                continue;

            int literalLength = line.length();
            int memoryLength = 0;
            for (int i = 1; i < line.length() - 1; i++) {
                if (line.charAt(i) == '\\') {
                    if (line.charAt(i + 1) == 'x')
                        i += 3;
                    else
                        i += 1;
                }
                memoryLength++;
            }

            literalMemoryDiff += literalLength - memoryLength;
        }

        System.out.println("[Part 1] Literal length - Memory length = " + literalMemoryDiff);
    }

    public static void partTwo() {
        String[] input = getInput();

        int literalMemoryDiff = 0;
        for (String line : input) {
            line = line.replace("\r", "");
            if (line.isEmpty())
                continue;

            int encodedLength = 2;
            for (char c : line.toCharArray()) {
                if (c == '\\' || c == '"')
                    encodedLength += 2;
                else
                    encodedLength += 1;
            }

            literalMemoryDiff += encodedLength - line.length();
        }

        System.out.println("[Part 2] Literal length - Literal encoded length = " + literalMemoryDiff);
    }
}
