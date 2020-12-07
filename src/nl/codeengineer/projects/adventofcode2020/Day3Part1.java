package nl.codeengineer.projects.adventofcode2020;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Day3Part1 {

    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("./input/day3-1.txt"));

        part1(lines);
        part2(lines);
    }

    public static void part1(List<String> lines) {
        char[][] map = generateMap(lines);
        int count = checkSlope(map, 3, 1);
        System.out.println(count);
    }

    public static void part2(List<String> lines) {
        char[][] map = generateMap(lines);

        int count = checkSlope(map, 1, 1);
        count = count * checkSlope(map, 3, 1);
        count = count * checkSlope(map, 5, 1);
        count = count * checkSlope(map, 7, 1);
        count = count * checkSlope(map, 1, 2);

        System.out.println(count);
    }

    private static char[][] generateMap(List<String> lines) {
        int width = lines.get(0).trim().length();
        int height = lines.size();

        char[][] map = new char[width][height];

        for (int y = 0; y < height; y++) {
            String line = lines.get(y);
            for (int x = 0; x < width; x++) {
                map[x][y] = line.charAt(x);
            }
        }
        return map;
    }


    private static int checkSlope(char[][] map, int slopeX, int slopeY) {
        int width = map.length;
        int height = map[0].length;

        int x = 0;
        int y = 0;
        int count = 0;

        while (y < height) {
            if (map[x][y] == '#') {
                count++;
            }

            x = (x + slopeX) % width;
            y = y + slopeY;
        }
        return count;
    }


}
