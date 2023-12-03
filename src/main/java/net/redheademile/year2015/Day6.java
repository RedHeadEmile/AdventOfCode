package net.redheademile.year2015;

import net.redheademile.Main;

public class Day6 {
    private static String[] getInput() {
        return Main.getInput("2015_6.txt").split("\n");
    }

    public static void partOne() {
        String[] instructions = getInput();

        boolean[] lights = new boolean[1000 * 1000];

        for (String instruction : instructions) {
            instruction = instruction.replace("\r", "");
            if (instruction.isBlank())
                continue;

            String[] details = instruction.split(" ");

            boolean isTurn = details[0].equals("turn");
            String[] firstCorner = details[isTurn ? 2 : 1].split(",");
            String[] secondCorner = details[isTurn ? 4 : 3].split(",");

            int ox = Integer.parseInt(firstCorner[0]);
            int oy = Integer.parseInt(firstCorner[1]);
            int dx = Integer.parseInt(secondCorner[0]);
            int dy = Integer.parseInt(secondCorner[1]);

            boolean isTurnOn = isTurn && details[1].equals("on");
            boolean isTurnOff = isTurn && details[1].equals("off");
            boolean isToggle = !isTurn;

            for (int x = ox; x <= dx; ++x)
                for (int y = oy; y <= dy; ++y) {
                    if (isTurnOn)
                        lights[x + 1000 * y] = true;
                    else if (isTurnOff)
                        lights[x + 1000 * y] = false;
                    else if (isToggle)
                        lights[x + 1000 * y] = !lights[x + 1000 * y];
                }
        }

        int totalEnabledLights = 0;
        for (boolean light : lights)
            if (light) totalEnabledLights++;

        System.out.println("[Part 1] Total amount of enabled lights: " + totalEnabledLights);
    }

    public static void partTwo() {
        String[] instructions = getInput();

        int[] brightnesses = new int[1000 * 1000];

        for (String instruction : instructions) {
            instruction = instruction.replace("\r", "");
            if (instruction.isBlank())
                continue;

            String[] details = instruction.split(" ");

            boolean isTurn = details[0].equals("turn");
            String[] firstCorner = details[isTurn ? 2 : 1].split(",");
            String[] secondCorner = details[isTurn ? 4 : 3].split(",");

            int ox = Integer.parseInt(firstCorner[0]);
            int oy = Integer.parseInt(firstCorner[1]);
            int dx = Integer.parseInt(secondCorner[0]);
            int dy = Integer.parseInt(secondCorner[1]);

            boolean isTurnOn = isTurn && details[1].equals("on");
            boolean isTurnOff = isTurn && details[1].equals("off");
            boolean isToggle = !isTurn;

            for (int x = ox; x <= dx; ++x)
                for (int y = oy; y <= dy; ++y) {
                    if (isTurnOn)
                        ++brightnesses[x + 1000 * y];
                    else if (isTurnOff)
                        brightnesses[x + 1000 * y] = Math.max(0, brightnesses[x + 1000 * y] - 1);
                    else if (isToggle)
                        brightnesses[x + 1000 * y] += 2;
                }
        }

        int totalAmountOfBrightness = 0;
        for (int brightness : brightnesses)
            totalAmountOfBrightness += brightness;

        System.out.println("[Part 2] Total amount of brightness: " + totalAmountOfBrightness);
    }
}
