package net.redheademile.year2023;

import net.redheademile.Main;

import java.util.List;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.stream.Stream;

public class Day3 {
    private static String[] getInput() {
        return Main.getInput("2023_3.txt").split("\r\n");
    }

    public static void partOne() {
        String[] lines = getInput();

        long numberSum = 0;
        String currentNumber = "";
        for (int lineIndex = 0; lineIndex < lines.length; lineIndex++) {
            String line = lines[lineIndex];
            for (int i = 0; i < line.length(); i++) {
                char c = line.charAt(i);
                if (Character.isDigit(c))
                    currentNumber += c;

                if (!currentNumber.isEmpty() && (!Character.isDigit(c) || i == line.length() - 1)) {
                    int number = Integer.parseInt(currentNumber);
                    int correction = Character.isDigit(c) && i == line.length() - 1 ? 1 : 0;

                    boolean partNumber = false;
                    for (int j = 0; j < currentNumber.length() && !partNumber; j++) {
                        int x = i - currentNumber.length() + j + correction;

                        if (j == 0 && x - 1 >= 0 && line.charAt(x - 1) != '.')
                            partNumber = true;

                        if (!partNumber && lineIndex > 0) {
                            if (x - 1 >= 0 && lines[lineIndex - 1].charAt(x - 1) != '.' && !Character.isDigit(lines[lineIndex - 1].charAt(x - 1)))
                                partNumber = true;

                            else if (lines[lineIndex - 1].charAt(x) != '.' && !Character.isDigit(lines[lineIndex - 1].charAt(x)))
                                partNumber = true;

                            else if (x + 1 < line.length() && lines[lineIndex - 1].charAt(x + 1) != '.' && !Character.isDigit(lines[lineIndex - 1].charAt(x + 1)))
                                partNumber = true;
                        }

                        if (!partNumber && lineIndex < lines.length - 1) {
                            if (x - 1 >= 0 && lines[lineIndex + 1].charAt(x - 1) != '.' && !Character.isDigit(lines[lineIndex + 1].charAt(x - 1)))
                                partNumber = true;

                            else if (lines[lineIndex + 1].charAt(x) != '.')
                                partNumber = true;

                            else if (x + 1 < line.length() && lines[lineIndex + 1].charAt(x + 1) != '.' && !Character.isDigit(lines[lineIndex + 1].charAt(x + 1)))
                                partNumber = true;
                        }

                        if (!partNumber && j == currentNumber.length() - 1 && x + 1 < line.length() && line.charAt(x + 1) != '.')
                            partNumber = true;
                    }

                    currentNumber = "";
                    if (partNumber)
                        numberSum += number;
                }
            }
        }

        System.out.println("[Part 1] Sum of numbers adjacent from a symbol: " + numberSum);
    }

    public static void partTwo() {
        String[] lines = getInput();

        BiFunction<Integer, Integer, Integer> tryFindNumber = (lineIndex, charIndex) -> {
            if (!Character.isDigit(lines[lineIndex].charAt(charIndex)))
                return null;

            while (charIndex > 0 && Character.isDigit(lines[lineIndex].charAt(charIndex - 1)))
                --charIndex;

            int charIndexEnd = charIndex;
            while (charIndexEnd + 1 < lines[lineIndex].length() && Character.isDigit(lines[lineIndex].charAt(charIndexEnd + 1)))
                ++charIndexEnd;

            return Integer.parseInt(lines[lineIndex].substring(charIndex, charIndexEnd + 1));
        };

        int numberSum = 0;
        for (int lineIndex = 0; lineIndex < lines.length; lineIndex++) {
            String line = lines[lineIndex];
            for (int charIndex = 0; charIndex < line.length(); charIndex++) {
                if (line.charAt(charIndex) != '*')
                    continue;

                Integer topLeft = lineIndex > 0 && charIndex > 0 ? tryFindNumber.apply(lineIndex - 1, charIndex - 1) : null;
                Integer topMiddle = topLeft == null && lineIndex > 0 ? tryFindNumber.apply(lineIndex - 1, charIndex) : null;
                Integer topRight = lineIndex > 0 && charIndex + 1 < line.length() && !Character.isDigit(lines[lineIndex - 1].charAt(charIndex)) ? tryFindNumber.apply(lineIndex - 1, charIndex + 1): null;
                Integer left = charIndex > 0 ? tryFindNumber.apply(lineIndex, charIndex - 1) : null;
                Integer right = charIndex + 1 < line.length() ? tryFindNumber.apply(lineIndex, charIndex + 1) : null;
                Integer bottomLeft = lineIndex + 1 < lines.length && charIndex > 0 ? tryFindNumber.apply(lineIndex + 1, charIndex - 1) : null;
                Integer bottomMiddle = bottomLeft == null && lineIndex + 1 < lines.length ? tryFindNumber.apply(lineIndex + 1, charIndex) : null;
                Integer bottomRight = lineIndex + 1 < lines.length && charIndex + 1 < line.length() && !Character.isDigit(lines[lineIndex + 1].charAt(charIndex)) ? tryFindNumber.apply(lineIndex + 1, charIndex + 1): null;

                List<Integer> partNumbers = Stream.of(topLeft, topMiddle, topRight, left, right, bottomLeft, bottomMiddle, bottomRight)
                        .filter(Objects::nonNull)
                        .toList();

                if (partNumbers.size() == 2)
                    numberSum += partNumbers.get(0) * partNumbers.get(1);
            }
        }

        System.out.println("[Part 2] Sum of gears: " + numberSum);
    }
}
