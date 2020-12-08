package nl.codeengineer.projects.adventofcode2020;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Day8 {

    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("./input/day8.txt"));
        part1(lines);

    }

    private static class Instruction {
        public String name;
        public int value;
    }

    private static void part1(List<String> lines) {
        int acc = 0;
        int pc = 0;

        Set<Integer> executedLines = new HashSet<>();

        while (!executedLines.contains(pc) && pc < lines.size()) {
            Instruction instruction = parseLine(lines.get(pc));
            executedLines.add(pc);

            switch (instruction.name) {
                case "acc": acc += instruction.value; pc++; break;
                case "jmp": pc += instruction.value; break;
                default: pc++;
            }
        }

        System.out.println(acc);
    }

    private static Instruction parseLine(String line) {
        Scanner scanner = new Scanner(line);

        Instruction instruction = new Instruction();
        instruction.name = scanner.next();
        instruction.value = scanner.nextInt();
        scanner.close();
        return instruction;
    }

    private static List<String> testInput1() {
        String input = "nop +0\n" +
                "acc +1\n" +
                "jmp +4\n" +
                "acc +3\n" +
                "jmp -3\n" +
                "acc -99\n" +
                "acc +1\n" +
                "jmp -4\n" +
                "acc +6";

        return Arrays.asList(input.split("\\n"));
    }


}
