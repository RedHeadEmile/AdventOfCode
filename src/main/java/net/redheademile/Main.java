package net.redheademile;

import java.io.IOException;
import java.io.InputStream;

public class Main {
    public static String getInput(String fileName) {
        if (fileName == null)
            throw new IllegalArgumentException("File name cannot be null");

        if (!fileName.startsWith("/"))
            fileName = "/" + fileName;

        try (InputStream in = Main.class.getResourceAsStream(fileName)) {
            if (in == null)
                throw new IllegalArgumentException("File not found: " + fileName);

            return new String(in.readAllBytes());
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        System.out.println();
        TwentyTwentyThree.one();
        System.out.println();
        TwentyTwentyThree.two();
    }
}