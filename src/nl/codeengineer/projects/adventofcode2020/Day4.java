package nl.codeengineer.projects.adventofcode2020;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

public class Day4 {

    public static Set<String> validProperties = Set.of(new String[]{"byr","iyr","eyr","hgt","hcl","ecl","pid"});
    public static String[] optionalProps = {"cid"};

    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("./input/day4.txt"));
        part1(lines);
        part2(lines);
    }

    private static void part1(List<String> lines) {
        Map<String, String> result = new HashMap<>();

        int count = 0;

        for (String line: lines) {
            if (line.isBlank()) {
                if (result.keySet().containsAll(validProperties)) {
                    count++;
                }

                result.clear();
            } else {
                result.putAll(parseLine2(line));
            }
        }

        if (result.keySet().containsAll(validProperties)) {
            count++;
        }


        System.out.println(count);
    }

    private static void part2(List<String> lines) {
        Map<String, String> result = new HashMap<>();

        int count = 0;

        for (String line: lines) {
            if (line.isBlank()) {
                if (propsValid(result)) {
                    count++;
                }

                result.clear();
            } else {
                result.putAll(parseLine2(line));
            }
        }

        if (propsValid(result)) {
            count++;
        }


        System.out.println(count);
    }

    public static Map<String, String> parseLine2(String line) {
        Map<String, String> result = new HashMap<>();
        String[] pairs = line.split(" ");

        for (String pair: pairs) {
            String[] keyVal = pair.split(":");
            result.put(keyVal[0].trim().toLowerCase(), keyVal[1].trim().toLowerCase());
        }
        return result;
    }

    public static boolean propsValid(Map<String, String> props) {
        if (!props.keySet().containsAll(validProperties)) {
            return false;
        }

        int byr = Integer.parseInt(props.get("byr"));
        if (byr < 1920 || byr > 2002) {
            return false;
        }

        int iyr = Integer.parseInt(props.get("iyr"));
        if (iyr < 2010 || iyr > 2020) {
            return false;
        }

        int eyr = Integer.parseInt(props.get("eyr"));
        if (eyr < 2020 || eyr > 2030) {
            return false;
        }

        String hgt = props.get("hgt");
        int height = Integer.parseInt(hgt.substring(0, hgt.length() - 2));
        String unit = hgt.substring(hgt.length() - 2);
        if (!unit.equals("cm") && !unit.equals("in")) {
            return false;
        }

        if (unit.equals("cm") && (height < 150 || height > 193)) {
            return false;
        }
        if (unit.equals("in") && (height < 59 || height > 76)) {
            return false;
        }

        Pattern hex = Pattern.compile("^#[0-9a-z]{6}$");
        if (!hex.asPredicate().test(props.get("hcl"))) {
            return false;
        }

        List<String> colors = List.of("amb", "blu", "brn", "gry", "grn", "hzl", "oth");
        if (!colors.contains(props.get("ecl"))) {
            return false;
        }

        Pattern pidPattern = Pattern.compile("^[0-9]{9}$");
        if (!pidPattern.asPredicate().test(props.get("pid"))) {
            return false;
        }

        return true;
    }
}
