package nl.codeengineer.projects.adventofcode2020;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day10 {


    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("./input/day10.txt"));
        //List<String> lines = getTestInput();

        List<Integer> ratings = lines.stream().map(Integer::parseInt).collect(Collectors.toList());
        ratings.add(0, 0);

        ratings.sort(Integer::compareTo);

        part1(ratings);
    }

    private static void part1(List<Integer> ratings) {
        int diff1 = 0;
        int diff3 = 1;

        for (int i = 0; i < ratings.size() - 1; i++) {
            switch (ratings.get(i+1) - ratings.get(i)) {
                case 1: diff1++; break;
                case 3: diff3++; break;
            }
        }

        System.out.println(diff1 * diff3);
    }

    private static List<String> getTestInput() {
        String input = "16\n" +
                "10\n" +
                "15\n" +
                "5\n" +
                "1\n" +
                "11\n" +
                "7\n" +
                "19\n" +
                "6\n" +
                "12\n" +
                "4";

        return Arrays.asList(input.split("\\n"));

    }

}
