package net.redheademile.year2023;

import net.redheademile.Main;

import java.util.Arrays;

public class Day6 {
    private static String[] getInput() {
        return Main.getInput("2023_6.txt").replaceAll(" +", " ").split("\r\n");
    }

    public static void partOne() {
        String[] data = getInput();
        String[] times = data[0].split(" ");
        String[] records = data[1].split(" ");

        int[] races = new int[(times.length - 1) * 2];
        for (int i = 0; i < races.length; i += 2) {
            races[i] = Integer.parseInt(times[i / 2 + 1]);
            races[i + 1] = Integer.parseInt(records[i / 2 + 1]);
        }

        int total = 1;
        for (int i = 0; i < races.length; i += 2) {
            int timeLimit = races[i];
            int record = races[i + 1];

            int winningPossibilities = 0;
            for (int speed = 1; speed < timeLimit; ++speed) {
                int distance = speed * (timeLimit - speed);
                if (distance > record)
                    ++winningPossibilities;
            }

            total *= winningPossibilities;
        }

        System.out.println("[Part 1] Result: " + total);
    }

    public static void partTwo() {
        String[] data = getInput();

        long timeLimit = Long.parseLong(data[0].substring(data[0].indexOf(' ')).replace(" ", ""));
        long record = Long.parseLong(data[1].substring(data[1].indexOf(' ')).replace(" ", ""));

        long winningPossibilities = 0;
        for (long speed = 1; speed < timeLimit; ++speed) {
            long distance = speed * (timeLimit - speed);
            if (distance > record)
                ++winningPossibilities;
        }

        System.out.println("[Part 2] Number of winning races: " + winningPossibilities);
    }
}
