package nl.codeengineer.projects.adventofcode2020;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class Day5 {

    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("./input/day5.txt"));
        part1(lines);
        part2(lines);
    }

    private static void part1(List<String> lines) {
        int high = 0;
        for (String line: lines) {
            int id = calcId(line);
            if (id > high) {
                high = id;
            }
        }

        System.out.println(high);
    }

    private static void part2(List<String> lines) {
        List<Integer> ids = lines.stream().map(Day5::calcId).collect(Collectors.toList());
        ids.sort(Integer::compareTo);

        for (int i = 0; i < ids.size() - 1; i++) {
            if (!(ids.get(i) == ids.get(i+1) -1)) {
                System.out.println(ids.get(i) + 1);
            }
        }

    }


    private static int calcId(String line) {
        String binLine = line.replaceAll("F", "0")
                .replaceAll("B", "1")
                .replaceAll("R", "1")
                .replaceAll("L", "0");

        return Integer.parseInt(binLine, 2);
    }

}
