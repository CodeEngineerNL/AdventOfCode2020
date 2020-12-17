package nl.codeengineer.projects.adventofcode2020;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class Day11 {


    public static void main(String[] args) throws IOException {
        //List<String> lines = Files.readAllLines(Paths.get("./input/day11.txt"));
        List<String> lines = getTestInput();

        int width = lines.get(0).length();
        int height = lines.size();
        char[][] map = new char[width][height];

        int y = 0;
        for (String line: lines) {
            for (int x = 0; x < width; x++) {
                map[y][x] = line.charAt(x);
            }

            y++;
        }

        part1(map);
        part2(map);
    }

    private static void part1(char[][] initialMap) {
        char[][] oldMap = initialMap;
        char[][] newMap = permute(initialMap);

        while (!isSameMap(oldMap, newMap )) {
            oldMap = newMap;
            newMap = permute(oldMap);
        }

        System.out.println(countOccupied(newMap));
    }

    private static void part2(char[][] initialMap) {
        char[][] oldMap = initialMap;
        char[][] newMap = permute(initialMap);

        while (!isSameMap(oldMap, newMap )) {
            oldMap = newMap;
            newMap = permute2(oldMap);
        }

        System.out.println(countOccupied(newMap));
    }

    private static int countOccupied(char[][] map) {
        int count = 0;

        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map[0].length; x++) {
                if (map[y][x] == '#') {
                    count++;
                }
            }
        }

        return count;
    }

    private static boolean isSameMap(char[][] map1, char[][] map2) {
        for (int y = 0; y < map1.length; y++) {
            for (int x = 0; x < map1[0].length; x++) {
                if (map1[y][x] != map2[y][x]) {
                    return false;
                }
            }
        }
        return true;
    }

    private static char[][]permute(char[][] map) {
        char[][] newMap = new char[map.length][map[0].length];

        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map[0].length; x++) {
                int num = calcSeatCount(x, y, map);
                if (map[y][x] == '#' && num > 3) {
                    newMap[y][x] ='L';
                } else if (map[y][x] == 'L' && num == 0) {
                    newMap[y][x] ='#';
                } else {
                    newMap[y][x] = map[y][x];
                }
            }
        }

        return newMap;
    }

    private static char[][]permute2(char[][] map) {
        char[][] newMap = new char[map.length][map[0].length];

        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map[0].length; x++) {
                int num = calcSeatCount2(x, y, map);
                if (map[y][x] == '#' && num > 3) {
                    newMap[y][x] ='L';
                } else if (map[y][x] == 'L' && num == 0) {
                    newMap[y][x] ='#';
                } else {
                    newMap[y][x] = map[y][x];
                }
            }
        }

        return newMap;
    }

    private static int calcSeatCount(int x, int y, char[][] map) {
        int count = 0;
        for (int y1 = y - 1 ; y1 <= y + 1; y1++) {
            for (int x1 = x - 1; x1 <= x + 1; x1++) {
                if (!(x == x1 && y == y1) &&
                        y1 >= 0 && y1 < map.length &&
                        x1 >= 0 && x1 < map[0].length &&
                        map[y1][x1] == '#') {
                    count++;
                }
            }
        }
        return count;
    }

    private static int calcSeatCount2(int x, int y, char[][] map) {
        int count = 0;
        for (int y1 = - 1 ; y1 <= 1; y1++) {
            for (int x1 = - 1; x1 <= 1; x1++) {
                if (isRayOccupied(x, y, x1, y1, map)) {
                    count++;
                }
            }
        }
        return count;
    }

    private static boolean isRayOccupied(int x, int y, int dirX, int dirY, char[][] map) {
        if (dirX == 0 && dirY == 0) {
            return false;
        }

        while (x + dirX >= 0 && x + dirX < map[0].length && y + dirY >= 0 && y + dirY < map.length) {
            if (map[y+dirY][x+dirX] == '#') {
                return true;
            }
            x += dirX;
            y += dirY;
        }

        return false;
    }

    private static List<String> getTestInput() {
        String input = "L.LL.LL.LL\n" +
                "LLLLLLL.LL\n" +
                "L.L.L..L..\n" +
                "LLLL.LL.LL\n" +
                "L.LL.LL.LL\n" +
                "L.LLLLL.LL\n" +
                "..L.L.....\n" +
                "LLLLLLLLLL\n" +
                "L.LLLLLL.L\n" +
                "L.LLLLL.LL";

        return Arrays.asList(input.split("\\n"));

    }

}
