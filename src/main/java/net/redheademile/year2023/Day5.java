package net.redheademile.year2023;

import net.redheademile.Main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day5 {
    private static String[] getInput() {
        return Main.getInput("2023_5.txt").split("\r\n");
    }

    public static void partOne() {
        String[] input = getInput();

        String[] rawSeeds = input[0].split(" ");
        long[] seeds = new long[rawSeeds.length - 1];
        boolean[] upgraded = new boolean[seeds.length];
        for (int i = 1; i < rawSeeds.length; i++)
            seeds[i - 1] = Long.parseLong(rawSeeds[i]);

        for (int i = 1; i < input.length; i++) {
            String line = input[i];
            String[] details = line.split(" ");
            if (details.length != 3) {
                Arrays.fill(upgraded, false);
                continue;
            }

            long destinationRangeStart = Long.parseLong(details[0]); // 100
            long sourceRangeStart = Long.parseLong(details[1]); // 10
            long range = Long.parseLong(details[2]); // 5

            for (int j = 0; j < seeds.length; j++) {
                if (!upgraded[j] && seeds[j] >= sourceRangeStart && seeds[j] < sourceRangeStart + range) {
                    seeds[j] = destinationRangeStart + seeds[j] - sourceRangeStart;
                    upgraded[j] = true;
                }
            }
        }

        Arrays.sort(seeds);
        System.out.println("[Part 1] Lowest location number: " + seeds[0]);
    }

    public static void partTwo() {
        String[] input = getInput();

        String[] rawSeeds = input[0].split(" ");
        List<Long> seeds = new ArrayList<>(rawSeeds.length - 1);
        List<Boolean> upgraded = new ArrayList<>((rawSeeds.length - 1) / 2);
        for (int i = 1; i < rawSeeds.length; i++) {
            seeds.add(Long.parseLong(rawSeeds[i]));
            if (i % 2 == 1)
                upgraded.add(false);
        }

        for (int i = 1; i < input.length; i++) {
            String line = input[i];
            String[] details = line.split(" ");
            if (details.length != 3) {
                for (int j = 0; j < upgraded.size(); j++)
                    upgraded.set(j, false);
                continue;
            }

            long destinationRangeStart = Long.parseLong(details[0]); // 100
            long sourceRangeStart = Long.parseLong(details[1]); // 10
            long range = Long.parseLong(details[2]); // 5

            for (int j = 0; j < seeds.size(); j += 2) {
                long seed = seeds.get(j);
                long seedRange = seeds.get(j + 1);

                if (!upgraded.get(j / 2)) {
                    long beginning = Math.max(seed, sourceRangeStart);
                    long ending = Math.min(seed + seedRange, sourceRangeStart + range);

                    if (beginning >= seed + range || ending <= beginning)
                        continue;

                    if (beginning == seed && ending == sourceRangeStart + range) {
                        seeds.set(j, destinationRangeStart + seed - sourceRangeStart);
                        seeds.set(j + 1, Math.abs(range - seedRange));
                        upgraded.set(j / 2, true);

                        seeds.add(sourceRangeStart + range);
                        seeds.add(seed + seedRange - sourceRangeStart + range);
                        upgraded.add(false);
                    }
                    else if (beginning == sourceRangeStart && ending == sourceRangeStart + range) {
                        seeds.set(j, destinationRangeStart);
                        seeds.set(j + 1, range);
                        upgraded.set(j / 2, true);

                        seeds.add(seed);
                        seeds.add(seedRange - Math.abs(seed + seedRange - sourceRangeStart));
                        upgraded.add(false);

                        seeds.add(sourceRangeStart + range);
                        seeds.add(Math.abs(seed + seedRange - sourceRangeStart + range));
                        upgraded.add(false);
                    }
                    else if (beginning == sourceRangeStart && ending == seed + seedRange) {
                        seeds.set(j, destinationRangeStart);
                        seeds.set(j + 1, Math.abs(seed + seedRange - sourceRangeStart));
                        upgraded.set(j / 2, true);

                        seeds.add(seed);
                        seeds.add(seedRange - Math.abs(seed + seedRange - sourceRangeStart));
                        upgraded.add(false);
                    }
                    else if (beginning == seed && ending == seed + seedRange) {
                        seeds.set(j, destinationRangeStart + seed - sourceRangeStart);
                        seeds.set(j + 1, seedRange);
                        upgraded.set(j / 2, true);
                    }
                }
            }
        }

        long minimum = Long.MAX_VALUE;
        for (int i = 0; i < seeds.size(); i += 2) {
            long seed = seeds.get(i);
            if (seed < minimum && seed > 0)
                minimum = seed;
        }
        System.out.println("[Part 2] Lowest location number: " + minimum);
    }
}
