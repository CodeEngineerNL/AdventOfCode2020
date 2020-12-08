package nl.codeengineer.projects.adventofcode2020;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Day7 {

    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("./input/day7.txt"));
        part1(lines);
    }

    private static void part1(List<String> lines) {
        final Map<String, List<String>> bagRules = new HashMap<>();

        lines.forEach(l -> bagRules.putAll(parseLine(l)));

        Set<String> containers = new HashSet<>();
        getContainers("shinygold", bagRules, containers);
        System.out.println(containers.size());
    }


    private static void getContainers(String bagColor,  Map<String, List<String>> rules, Set<String> containers) {
        rules.forEach((rule, items) -> {
                  if (items.contains(bagColor) && !containers.contains(rule)) {
                      containers.add(rule);
                      getContainers(rule, rules, containers);
                  }
        });
    }

    public static List<String> testInput1() {
        String input = "light red bags contain 1 bright white bag, 2 muted yellow bags.\n" +
                "dark orange bags contain 3 bright white bags, 4 muted yellow bags.\n" +
                "bright white bags contain 1 shiny gold bag.\n" +
                "muted yellow bags contain 2 shiny gold bags, 9 faded blue bags.\n" +
                "shiny gold bags contain 1 dark olive bag, 2 vibrant plum bags.\n" +
                "dark olive bags contain 3 faded blue bags, 4 dotted black bags.\n" +
                "vibrant plum bags contain 5 faded blue bags, 6 dotted black bags.\n" +
                "faded blue bags contain no other bags.\n" +
                "dotted black bags contain no other bags.";

        return Arrays.asList(input.split("\\n"));
    }

    public static Map<String, List<String>> parseLine(String line) {
        Map<String, List<String>> containers = new HashMap<>();

        try (Scanner scanner = new Scanner(line)) {
            String containerBag = scanner.next() + scanner.next();
            scanner.next("bags");
            scanner.next("contain");

            List<String> items = new ArrayList<>();

            if (!scanner.hasNext("no")) {
                while (scanner.hasNext()) {
                    scanner.next("\\d");
                    String contained = scanner.next() + scanner.next();
                    items.add(contained);
                    scanner.next("bags?[,.]");
                }
            }

            containers.put(containerBag, items);
        }

        return containers;
    }

}
