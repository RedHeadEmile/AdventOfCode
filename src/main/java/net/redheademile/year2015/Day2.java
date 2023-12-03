package net.redheademile.year2015;

import net.redheademile.Main;

public class Day2 {

    /**
     * Get the input data of day 2 of the advent of code 2015.
     */
    private static String[] getInput() {
        return Main.getInput("2015_2.txt").split("\n");
    }

    public static void partOne() {
        String[] lines = getInput();

        int totalSurface = 0;
        for (String line : lines) {
            String[] dimensions = line.replace("\r", "").split("x");

            int length = Integer.parseInt(dimensions[0]);
            int width = Integer.parseInt(dimensions[1]);
            int height = Integer.parseInt(dimensions[2]);

            totalSurface += 2 * length * width + 2 * width * height + 2 * height * length
                    + Math.min(length * width, Math.min(width * height, height * length));

        }

        System.out.println("[Part 1] Total surface: " + totalSurface);
    }

    public static void partTwo() {
        String[] lines = getInput();

        int totalRibbon = 0;
        for (String line : lines) {
            String[] dimensions = line.replace("\r", "").split("x");

            int length = Integer.parseInt(dimensions[0]);
            int width = Integer.parseInt(dimensions[1]);
            int height = Integer.parseInt(dimensions[2]);

            int secondSmaller;
            int firstSmaller = Math.min(length, Math.min(width, height));

            if (firstSmaller == length) secondSmaller = Math.min(width, height);
            else if (firstSmaller == width) secondSmaller = Math.min(length, height);
            else secondSmaller = Math.min(length, width);

            totalRibbon += firstSmaller * 2 + secondSmaller * 2 + length * width * height;
        }

        System.out.println("[Part 2] Total ribbon: " + totalRibbon);
    }
}
