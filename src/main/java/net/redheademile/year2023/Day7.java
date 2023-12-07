package net.redheademile.year2023;

import net.redheademile.Main;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;

public class Day7 {
    private static String[] getInput() {
        return Main.getInput("2023_7.txt").split("\r\n");
    }

    public static void partOne() {
        List<Character> values = Arrays.asList('2', '3', '4', '5', '6', '7', '8', '9', 'T', 'J', 'Q', 'K', 'A');

        List<String> hands = Arrays.asList(getInput());
        hands.sort((a, b) -> {
            Map<Character, Integer> amountOfCharA = new HashMap<>();
            Map<Character, Integer> amountOfCharB = new HashMap<>();

            Boolean aIsBetter = null;
            for (int i = 0; i < 5; i++) {
                char charA = a.charAt(i);
                amountOfCharA.put(charA, amountOfCharA.getOrDefault(charA, 0) + 1);
                char charB = b.charAt(i);
                amountOfCharB.put(charB, amountOfCharB.getOrDefault(charB, 0) + 1);

                int aValue = values.indexOf(charA);
                int bValue = values.indexOf(charB);
                if (aIsBetter == null) {
                    if (aValue > bValue) aIsBetter = true;
                    else if (aValue < bValue) aIsBetter = false;
                }
            }

            if (amountOfCharA.containsValue(5) || amountOfCharB.containsValue(5)) {
                if (!amountOfCharA.containsValue(5)) return -1;
                if (!amountOfCharB.containsValue(5)) return 1;
            }

            if (amountOfCharA.containsValue(4) || amountOfCharB.containsValue(4)) {
                if (!amountOfCharA.containsValue(4)) return -1;
                if (!amountOfCharB.containsValue(4)) return 1;
            }

            if (amountOfCharA.containsValue(3) || amountOfCharB.containsValue(3)) {
                if (!amountOfCharA.containsValue(3)) return -1;
                if (!amountOfCharB.containsValue(3)) return 1;

                boolean fullHouseA = amountOfCharA.containsValue(2);
                boolean fullHouseB = amountOfCharB.containsValue(2);

                if (fullHouseA && !fullHouseB) return 1;
                if (!fullHouseA && fullHouseB) return -1;
            }

            if (amountOfCharA.containsValue(2) || amountOfCharB.containsValue(2)) {
                if (!amountOfCharA.containsValue(2)) return -1;
                if (!amountOfCharB.containsValue(2)) return 1;

                boolean doublePairA = Collections.frequency(amountOfCharA.values(), 2) == 2;
                boolean doublePairB = Collections.frequency(amountOfCharB.values(), 2) == 2;

                if (doublePairA && !doublePairB) return 1;
                if (!doublePairA && doublePairB) return -1;
            }

            if (aIsBetter == null)
                throw new IllegalStateException("Cannot compare " + a + " and " + b);

            return aIsBetter ? 1 : -1;
        });

        long total = 0;
        for (int i = 0; i < hands.size(); i++)
            total += (i + 1) * Long.parseLong(hands.get(i).split(" ")[1]);

        System.out.println("[Part 1] Total winnings: " + total);
    }

    public static void partTwo() {
        List<Character> values = Arrays.asList('J', '2', '3', '4', '5', '6', '7', '8', '9', 'T', 'Q', 'K', 'A');

        List<String> hands = Arrays.asList(getInput());
        hands.sort((a, b) -> {
            Map<Character, Integer> amountOfCharA = new HashMap<>();
            Map<Character, Integer> amountOfCharB = new HashMap<>();

            Boolean aIsBetter = null;
            int aAmountOfJ = 0;
            int bAmountOfJ = 0;
            for (int i = 0; i < 5; i++) {
                char charA = a.charAt(i);
                amountOfCharA.put(charA, amountOfCharA.getOrDefault(charA, 0) + 1);
                char charB = b.charAt(i);
                amountOfCharB.put(charB, amountOfCharB.getOrDefault(charB, 0) + 1);

                if (charA == 'J') ++aAmountOfJ;
                if (charB == 'J') ++bAmountOfJ;

                int aValue = values.indexOf(charA);
                int bValue = values.indexOf(charB);
                if (aIsBetter == null) {
                    if (aValue > bValue) aIsBetter = true;
                    else if (aValue < bValue) aIsBetter = false;
                }
            }

            Function<Map<Character, Integer>, Integer> highestValue = map -> {
                int highest = Integer.MIN_VALUE;
                for (Map.Entry<Character, Integer> characterIntegerEntry : map.entrySet()) {
                    if (characterIntegerEntry.getKey() == 'J') {
                        if (0 > highest)
                            highest = 0;
                    }
                    else {
                        if (characterIntegerEntry.getValue() > highest)
                            highest = characterIntegerEntry.getValue();
                    }
                }
                return highest;
            };

            BiFunction<Map<Character, Integer>, Integer, Character> findKeyByValue = (map, value) -> {
                for (Map.Entry<Character, Integer> characterIntegerEntry : map.entrySet())
                    if (characterIntegerEntry.getValue().equals(value)) return characterIntegerEntry.getKey();
                return null;
            } ;

            int highestValueA = highestValue.apply(amountOfCharA);
            int highestValueB = highestValue.apply(amountOfCharB);
            if (highestValueA + aAmountOfJ >= 5 || highestValueB + bAmountOfJ >= 5) {
                if (highestValueA + aAmountOfJ < 5) return -1;
                if (highestValueB + bAmountOfJ < 5) return 1;
            }

            else if (highestValueA + aAmountOfJ >= 4 || highestValueB + bAmountOfJ >= 4) {
                if (highestValueA + aAmountOfJ < 4) return -1;
                if (highestValueB + bAmountOfJ < 4) return 1;
            }

            else if (highestValueA + aAmountOfJ >= 3 || highestValueB + bAmountOfJ >= 3) {
                if (highestValueA + aAmountOfJ < 3) return -1;
                if (highestValueB + bAmountOfJ < 3) return 1;

                amountOfCharA.put('J', amountOfCharA.getOrDefault('J', 0) - (3 - highestValueA));
                amountOfCharA.remove(findKeyByValue.apply(amountOfCharA, highestValueA));
                amountOfCharB.put('J', amountOfCharB.getOrDefault('J', 0) - (3 - highestValueB));
                amountOfCharB.remove(findKeyByValue.apply(amountOfCharB, highestValueB));

                for (Character key : new HashSet<>(amountOfCharA.keySet()))
                    amountOfCharA.put(key, amountOfCharA.get(key) + aAmountOfJ - 3 + highestValueA);

                for (Character key : new HashSet<>(amountOfCharB.keySet()))
                    amountOfCharB.put(key, amountOfCharB.get(key) + bAmountOfJ - 3 + highestValueB);

                boolean fullHouseA = highestValue.apply(amountOfCharA) >= 2;
                boolean fullHouseB = highestValue.apply(amountOfCharB) >= 2;

                if (fullHouseA && !fullHouseB) return 1;
                if (!fullHouseA && fullHouseB) return -1;
            }

            else if (highestValueA + aAmountOfJ >= 2 || highestValueB + bAmountOfJ >= 2) {
                if (highestValueA + aAmountOfJ < 2) return -1;
                if (highestValueB + bAmountOfJ < 2) return 1;

                amountOfCharA.put('J', amountOfCharA.getOrDefault('J', 0) - (2 - highestValueA));
                amountOfCharA.remove(findKeyByValue.apply(amountOfCharA, highestValueA));
                amountOfCharB.put('J', amountOfCharB.getOrDefault('J', 0) - (2 - highestValueB));
                amountOfCharB.remove(findKeyByValue.apply(amountOfCharB, highestValueB));

                for (Character key : new HashSet<>(amountOfCharA.keySet()))
                    amountOfCharA.put(key, amountOfCharA.get(key) + aAmountOfJ - 2 + highestValueA);

                for (Character key : new HashSet<>(amountOfCharB.keySet()))
                    amountOfCharB.put(key, amountOfCharB.get(key) + bAmountOfJ - 2 + highestValueB);

                boolean doublePairA = highestValue.apply(amountOfCharA) >= 2;
                boolean doublePairB = highestValue.apply(amountOfCharB) >= 2;

                if (doublePairA && !doublePairB) return 1;
                if (!doublePairA && doublePairB) return -1;
            }

            if (aIsBetter == null)
                throw new IllegalStateException("Cannot compare " + a + " and " + b);

            return aIsBetter ? 1 : -1;
        });

        long total = 0;
        for (int i = 0; i < hands.size(); i++)
            total += (i + 1) * Long.parseLong(hands.get(i).split(" ")[1]);

        System.out.println("[Part 2] Total winnings: " + total);
    }
}
