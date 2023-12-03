package net.redheademile.year2015;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.stream.Stream;

public class Day4 {
    private static String getInput() {
        return "iwrupvqb";
    }

    public static void partOne() {
        String input = getInput();

        MessageDigest md;
        try {
            md = MessageDigest.getInstance("MD5");
        }
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        int i = 0;
        byte[] digested;
        do {
            md.update((input + i++).getBytes());
            digested = md.digest();
        }
        while (digested[0] != 0 || digested[1] != 0 || Byte.toUnsignedInt(digested[2]) >= 0x10);

        System.out.println("[Part 1] Answer: " + (i - 1));
    }

    public static void partTwo() {
        String input = getInput();

        MessageDigest md;
        try {
            md = MessageDigest.getInstance("MD5");
        }
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        int i = 0;
        byte[] digested;
        do {
            md.update((input + i++).getBytes());
            digested = md.digest();
        }
        while (digested[0] != 0 || digested[1] != 0 || digested[2] != 0);

        System.out.println("[Part 2] Answer: " + (i - 1));
    }
}
