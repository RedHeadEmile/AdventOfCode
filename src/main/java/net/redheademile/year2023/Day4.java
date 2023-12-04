package net.redheademile.year2023;

import net.redheademile.Main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day4 {
    private static String[] getInput() {
        return Main.getInput("2023_4.txt").split("\r\n");
    }

    public static void partOne() {
        String[] cards = getInput();

        int totalPoints = 0;
        for (String card : cards) {
            String[] detail = card.replaceAll(" +", " ").split(" ");
            
            List<Integer> winningNumbers = new ArrayList<>();
            for (int i = 2; i <= 11; i++)
                winningNumbers.add(Integer.parseInt(detail[i]));

            int points = 0;
            for (int i = 13; i < detail.length; i++) {
                Integer number = Integer.parseInt(detail[i]);
                if (winningNumbers.contains(number)) {
                    if (points == 0) points = 1;
                    else points *= 2;
                }
            }

            totalPoints += points;
        }

        System.out.println("[Part 1] Total points: " + totalPoints);
    }

    public static void partTwo() {
        List<String> cards = new ArrayList<>(Arrays.asList(getInput()));
        int[] amounts = new int[cards.size()];
        for (int i = 0; i < cards.size(); i++)
            amounts[i] = 1;

        for (int cardIndex = 0; cardIndex < cards.size(); cardIndex++) {
            int myAmount = amounts[cardIndex];
            String[] detail = cards.get(cardIndex).replaceAll(" +", " ").split(" ");

            List<Integer> winningNumbers = new ArrayList<>();
            for (int i = 2; i <= 11; i++)
                winningNumbers.add(Integer.parseInt(detail[i]));

            int winningNumbersAmount = 0;
            for (int i = 13; i < detail.length; i++) {
                Integer number = Integer.parseInt(detail[i]);
                if (winningNumbers.contains(number))
                    ++winningNumbersAmount;
            }

            for (int i = 0; i < winningNumbersAmount; i++)
                amounts[cardIndex + i + 1] += myAmount;
        }

        int totalScratchcards = 0;
        for (int amount : amounts)
            totalScratchcards += amount;
        System.out.println("[Part 2] Total scratchcards: " + totalScratchcards);
    }
}
