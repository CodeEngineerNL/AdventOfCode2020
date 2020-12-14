package nl.codeengineer.projects.adventofcode2020;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class Day10 {


    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("./input/day10.txt"));

        List<Integer> ratings = lines.stream().map(Integer::parseInt).collect(Collectors.toList());
        ratings.sort(Integer::compareTo);

        ratings.add(0, 0);
        ratings.add(ratings.get(ratings.size() - 1) + 3);


        part1(ratings);
        part2(ratings);
    }

    private static void part1(List<Integer> ratings) {
        int diff1 = 0;
        int diff3 = 0;

        for (int i = 0; i < ratings.size() - 1; i++) {
            switch (ratings.get(i+1) - ratings.get(i)) {
                case 1: diff1++; break;
                case 3: diff3++; break;
            }
        }

        System.out.println(diff1 * diff3);
    }

    private static class GraphItem {
        public int num;
        public List<GraphItem> next = new ArrayList<>();
    }

    private static void part2(List<Integer> ratings) {
        Map<Integer, List<Integer>> graph = new HashMap<>();

        for (int i = 0; i < ratings.size(); i++) {
            int num = ratings.get(i);
            List<Integer> items = new ArrayList<>();
            for (int j = i + 1; j < ratings.size() && (ratings.get(j) - num < 4) ; j++) {
                items.add(ratings.get(j));
            }
            graph.put(num, items);
        }

        System.out.println(countPaths(ratings, graph));
    }

    private static long countPaths(List<Integer> ratings,  Map<Integer, List<Integer>> graph) {
        Map<Integer, Long> calculated = new HashMap<>();

        for (int i = ratings.size() - 1; i >= 0; i--) {
            int num = ratings.get(i);
            if (!calculated.containsKey(num)) {

                if (graph.get(num).size() < 1) {
                    calculated.put(num, 1l);
                } else {
                    long count = 0;
                    for (Integer nextNum: graph.get(num)) {
                        count = count + calculated.get(nextNum);
                    }
                    calculated.put(num, count);
                }
            }
        }

        return calculated.get(0);
    }


    private static List<String> getTestInput2() {
        String input = "28\n" +
                "33\n" +
                "18\n" +
                "42\n" +
                "31\n" +
                "14\n" +
                "46\n" +
                "20\n" +
                "48\n" +
                "47\n" +
                "24\n" +
                "23\n" +
                "49\n" +
                "45\n" +
                "19\n" +
                "38\n" +
                "39\n" +
                "11\n" +
                "1\n" +
                "32\n" +
                "25\n" +
                "35\n" +
                "8\n" +
                "17\n" +
                "7\n" +
                "9\n" +
                "4\n" +
                "2\n" +
                "34\n" +
                "10\n" +
                "3\n";

        return Arrays.asList(input.split("\\n"));

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
