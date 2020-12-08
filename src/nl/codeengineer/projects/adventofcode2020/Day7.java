package nl.codeengineer.projects.adventofcode2020;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Day7 {

    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("./input/day7.txt"));
        part1(lines);
        part2(lines);
    }

    private static void part1(List<String> lines) {
        final Map<String, List<String>> bagRules = new HashMap<>();

        lines.forEach(l -> bagRules.putAll(parseLine(l)));

        Set<String> containers = new HashSet<>();
        getContainers("shinygold", bagRules, containers);
        System.out.println(containers.size());
    }

    private static void part2(List<String> lines) {
        final Map<String, List<String>> bagRules = new HashMap<>();

        lines.forEach(l -> bagRules.putAll(parseLine(l)));

        int count = getItemCount("shinygold", bagRules);
        System.out.println(count);
    }


    private static void getContainers(String bagColor,  Map<String, List<String>> rules, Set<String> containers) {
        rules.forEach((rule, items) -> {
            if (items.contains(bagColor) && !containers.contains(rule)) {
                containers.add(rule);
                getContainers(rule, rules, containers);
            }
        });
    }

    private static int getItemCount(String bagColor,  Map<String, List<String>> rules) {
        List<String> currentRules = rules.get(bagColor);
        int count = currentRules.size();
        for(String rule: currentRules) {
            count += getItemCount(rule, rules);
        }
        return count;
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

    public static List<String> testInput2() {
        String input = "shiny gold bags contain 2 dark red bags.\n" +
                "dark red bags contain 2 dark orange bags.\n" +
                "dark orange bags contain 2 dark yellow bags.\n" +
                "dark yellow bags contain 2 dark green bags.\n" +
                "dark green bags contain 2 dark blue bags.\n" +
                "dark blue bags contain 2 dark violet bags.\n" +
                "dark violet bags contain no other bags.";

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
                    int count = scanner.nextInt();
                    String contained = scanner.next() + scanner.next();

                    for (int i =0; i < count; i++){
                        items.add(contained);
                    }

                    scanner.next("bags?[,.]");
                }
            }

            containers.put(containerBag, items);
        }

        return containers;
    }

}
