package net.redheademile.year2023;

public class Year2023 {
    public static void dayOne() {
        System.out.println("Day 1 of the advent of code 2023:");
        Day1.partOne();
        Day1.partTwo();
    }

    public static void dayTwo() {
        System.out.println("Day 2 of the advent of code 2023:");
        Day2.partOne();
        Day2.partTwo();
    }

    public static void showAll() {
        System.out.println();
        System.out.println("Advent of code 2023:");
        System.out.println();
        dayOne();
        System.out.println();
        dayTwo();
    }
}
