package nl.codeengineer.projects.adventofcode2020;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class Day2Part1 {

    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("./input/day2-1.txt"));

        int validCount = 0;

        for (String line : lines) {
            if (isValidLine(line)) {
                validCount++;
            }
        }

        System.out.println(validCount);
    }

    public static boolean isValidLine(String line) {
        try (Scanner scanner = new Scanner(line)) {
            scanner.useDelimiter("-| |: ");

            int min = scanner.nextInt();
            int max = scanner.nextInt();

            String letter = scanner.next();
            String password = scanner.next();

            int pos = -1;
            int count = 0;
            while ((pos = password.indexOf(letter, pos + 1)) > -1) {
                count++;
            }

            return count >= min && count <= max;
        }
    }

    public static boolean isValidLinePart2(String line) {
        try (Scanner scanner = new Scanner(line)) {
            scanner.useDelimiter("-| |: ");

            int min = scanner.nextInt();
            int max = scanner.nextInt();

            String letter = scanner.next();
            String password = scanner.next();

            return
                    ((password.charAt(min + 1) == letter.charAt(0) && password.charAt(max + 1) != letter.charAt(0)) ||
                     (password.charAt(min + 1) != letter.charAt(0) && password.charAt(max + 1) == letter.charAt(0)));
        }

    }
}
