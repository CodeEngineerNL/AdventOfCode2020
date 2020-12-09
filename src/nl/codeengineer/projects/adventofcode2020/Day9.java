package nl.codeengineer.projects.adventofcode2020;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day9 {

    private static final int PREAMBLELEN = 25;

    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("./input/day9.txt"));

        int num = part1(lines);
        part2(lines, num);
    }

    private static int part1(List<String> lines) {
        int[] preamble = new int[PREAMBLELEN];

        for (int i = 0; i < PREAMBLELEN; i++) {
            preamble[i] = Integer.parseInt(lines.get(i));
        }

        int arrayIndex = 0;
        for (int i = PREAMBLELEN; i < lines.size(); i++) {
            int checkNum = Integer.parseInt(lines.get(i));
            if (!isSumIn(preamble, checkNum)) {
                System.out.println(checkNum);
                return checkNum;
            }

            preamble[arrayIndex] = checkNum;
            arrayIndex = (arrayIndex + 1) % PREAMBLELEN;
        }

        return -1;
    }

    private static void part2(List<String> lines, int sumNum) {
        List<Long> foundNumbers = new ArrayList<>();

        for (int i = 0; i < lines.size() - 1; i++) {
            int j = i + 1;
            long currentNum = Long.parseLong(lines.get(i));

            if (currentNum != sumNum) {
                long currentSum = currentNum;

                while (currentSum < sumNum) {
                    currentSum = currentSum + Long.parseLong(lines.get(j));
                    j++;
                }

                if (currentSum == sumNum) {
                    for (int k = i; k < j; k++) {
                        foundNumbers.add(Long.parseLong(lines.get(k)));
                    }
                    foundNumbers.sort(Long::compareTo);
                    System.out.println(foundNumbers.get(0) + foundNumbers.get(foundNumbers.size() - 1));
                    break;
                }
            }

        }
    }


    private static boolean isSumIn(int[] list, int check) {
        for (int i = 0; i < list.length - 1; i++) {
            for (int j = i; j < list.length; j++) {
                if (list[i] + list[j] == check) {
                    return true;
                }
            }
        }
        return false;
    }

    private static List<String> getTestInput() {
        String input = "35\n" +
                "20\n" +
                "15\n" +
                "25\n" +
                "47\n" +
                "40\n" +
                "62\n" +
                "55\n" +
                "65\n" +
                "95\n" +
                "102\n" +
                "117\n" +
                "150\n" +
                "182\n" +
                "127\n" +
                "219\n" +
                "299\n" +
                "277\n" +
                "309\n" +
                "576";

        return Arrays.asList(input.split("\\n"));

    }

}
