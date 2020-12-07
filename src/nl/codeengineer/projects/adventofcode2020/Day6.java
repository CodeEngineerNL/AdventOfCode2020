package nl.codeengineer.projects.adventofcode2020;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day6 {

    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("./input/day6.txt"));
        part1(lines);
        part2(lines);
    }

    private static void part1(List<String> lines) {
        Set<String> answers = new HashSet<>();

        int sum = 0;

        for(String line: lines) {
            if (line.isBlank()) {
                sum += answers.size();
                answers.clear();
            } else {
                for (char c: line.toCharArray()) {
                    answers.add("" + c);
                }
            }
        }

        sum += answers.size();
        System.out.println(sum);
    }

    private static void part2(List<String> lines) {
        Set<String> answers = null;

        int sum = 0;

        for(String line: lines) {
            if (line.isBlank()) {
                sum += answers.size();
                answers = null;
            } else {
                answers = retain(answers, line);
            }
        }

        sum += answers.size();
        System.out.println(sum);
    }

    private static Set<String> retain(Set<String> answers, String line) {
        Set<String> currentAnswers = new HashSet<>();
        for (char c: line.toCharArray()) {
            currentAnswers.add("" + c);
        }
        if (answers == null) {
            answers = currentAnswers;
        } else {
            answers.retainAll(currentAnswers);
        }
        return answers;
    }

}
