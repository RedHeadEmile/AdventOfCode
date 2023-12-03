package net.redheademile.year2015;

public class Day10 {
    private static String getInput() {
        return "1113222113";
    }

    public static void partOne() {
        String input = getInput();

        for (int k = 0; k < 40; k++) {
            String result = "";

            char lastChar = input.charAt(0);
            int amountOfThisChar = 1;
            for (int i = 1; i < input.length(); i++) {
                char c = input.charAt(i);
                if (c == lastChar)
                    ++amountOfThisChar;
                else {
                    result += Integer.toString(amountOfThisChar) + lastChar;
                    lastChar = c;
                    amountOfThisChar = 1;
                }
            }
            result += Integer.toString(amountOfThisChar) + lastChar;
            input = result;
        }

        System.out.println("[Part 1] Result: " + input.length());
    }

    public static void partTwo() {
        String input = getInput();

        for (int k = 0; k < 50; k++) {
            StringBuilder result = new StringBuilder();

            char lastChar = input.charAt(0);
            int amountOfThisChar = 1;
            for (int i = 1; i < input.length(); i++) {
                char c = input.charAt(i);
                if (c == lastChar)
                    ++amountOfThisChar;
                else {
                    result.append(amountOfThisChar).append(lastChar);
                    lastChar = c;
                    amountOfThisChar = 1;
                }
            }
            result.append(amountOfThisChar).append(lastChar);
            input = result.toString();
        }

        System.out.println("[Part 2] Result: " + input.length());
    }
}
