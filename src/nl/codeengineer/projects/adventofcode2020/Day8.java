package nl.codeengineer.projects.adventofcode2020;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class Day8 {

    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("./input/day8.txt"));
        part1(lines);
        part2(lines);
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

    private static class HistoryItem {
        Instruction instruction;
        int pc = 0;
        int acc = 0;
    }

    private static void part2(List<String> lines) {
        int acc = 0;
        int pc = 0;

        List<Instruction> instructions = lines.stream().map(Day8::parseLine).collect(Collectors.toList());

        Set<Integer> executedLines = new HashSet<>();
        Stack<HistoryItem> history = new Stack<>();

        int changedLine = -1;

        while (pc < instructions.size()) {
            Instruction instruction = instructions.get(pc);
            executedLines.add(pc);

            HistoryItem historyItem = new HistoryItem();
            historyItem.acc = acc;
            historyItem.pc = pc;
            historyItem.instruction = instruction;
            history.push(historyItem);


            switch (instruction.name) {
                case "acc": acc += instruction.value; pc++; break;
                case "jmp": pc += instruction.value; break;
                default: pc++;
            }

            if (executedLines.contains(pc)) {
                while (changedLine > -1) {
                    // Already changed. Find changed item in history and pop it off
                    HistoryItem lastItem = history.pop();
                    if (changedLine == lastItem.pc) {
                        changedLine = -1;
                        changeInstruction(instructions, lastItem.pc);
                    }
                }

                HistoryItem lastItem = history.pop();
                while (lastItem.instruction.name.equals("acc")) {
                    lastItem = history.pop();
                }

                changeInstruction(instructions, lastItem.pc);
                changedLine = lastItem.pc;
                pc = lastItem.pc;
                acc = lastItem.acc;
            }
        }

        System.out.println(acc);

    }

    private static void changeInstruction(List<Instruction> instructions, int index) {
        Instruction instruction = instructions.remove(index);

        Instruction newInstruction = new Instruction();
        newInstruction.value = instruction .value;
        newInstruction.name = instruction.name.equals("jmp") ? "nop" : "jmp";

        instructions.add(index, newInstruction);
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
