package nl.codeengineer.projects.adventofcode2020;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class Day1Part2 {

    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("./input/day1-1.txt"));
        List<Integer> numbers = lines.stream().map(Integer::parseUnsignedInt).collect(Collectors.toList());

        for (int x = 0; x < numbers.size() - 2; x++) {
            for (int i = x; i < numbers.size() - 1; i++) {
                for (int k = i; k < numbers.size(); k++) {

                    if (numbers.get(i) + numbers.get(x) + numbers.get(k) == 2020) {
                        System.out.println(numbers.get(i) * numbers.get(x)  * numbers.get(k));
                        return;
                    }
                }
            }
        }
    }
}
