package pepperlola;

import java.util.*;

public class Day3 {

    public static int part1(String input) {
        int x = 0; // pos x >
        int y = 0; // pos y ^
        Map<Tuple<Integer, Integer>, Integer> houses = new HashMap<>();
        String[] inst = input.split("");

        houses.put(new Tuple<>(x, y), 1);

        int uniqueHousesVisited = 0;
        for (String in : inst) {
            Direction direction = Direction.getDirection(in);
            switch (Objects.requireNonNull(direction)) {
                case UP:
                    y ++;
                    break;
                case DOWN:
                    y --;
                    break;
                case RIGHT:
                    x ++;
                    break;
                case LEFT:
                    x --;
                    break;
            }

            Tuple<Integer, Integer> position = new Tuple<>(x, y);
            int timesVisited = 0;
            if (houses.containsKey(position)) {
                houses.get(position);
            }
            houses.remove(position);
            houses.put(position, timesVisited + 1);
        }

        System.out.println(houses.toString());

        for (Tuple<Integer, Integer> position : houses.keySet()) {
            int timesVisited = houses.get(position);
            if (timesVisited > 0) uniqueHousesVisited ++;
        }

        return uniqueHousesVisited;
    }

    public static int part2(String input) {
        int x = 0; // pos x >
        int y = 0; // pos y ^
        int rx = 0; // robo-santa
        int ry = 0;

        Map<Tuple<Integer, Integer>, Integer> houses = new HashMap<>();

        deliverPresents(input, houses, x, y, 0);
        deliverPresents(input, houses, rx, ry, 1);

        System.out.println(houses.toString());

        int uniqueHousesVisited = 0;
        for (Tuple<Integer, Integer> position : houses.keySet()) {
            int timesVisited = houses.get(position);
            if (timesVisited > 0) uniqueHousesVisited ++;
        }

        return uniqueHousesVisited;
    }

    public static void deliverPresents(String input, Map<Tuple<Integer, Integer>, Integer> houses, int x, int y, int i) {
        String[] inst = input.split("");

        houses.put(new Tuple<>(x, y), 1);

        for (; i < inst.length; i += 2) {
            String in = inst[i];
            Direction direction = Direction.getDirection(in);
            switch (Objects.requireNonNull(direction)) {
                case UP:
                    y ++;
                    break;
                case DOWN:
                    y --;
                    break;
                case RIGHT:
                    x ++;
                    break;
                case LEFT:
                    x --;
                    break;
            }

            Tuple<Integer, Integer> position = new Tuple<>(x, y);
            int timesVisited = 0;
            if (houses.containsKey(position)) {
                houses.get(position);
            }
            houses.remove(position);
            houses.put(position, timesVisited + 1);
        }
    }

    public static void main(String[] args) {
        String directory = System.getProperty("user.dir");
        List<String> input = FileUtil.loadFile(directory + "/src/pepperlola/input.txt");
        System.out.println(part1(input.get(0)));
        System.out.println(part2(input.get(0)));
    }

    public enum Direction {
        UP("^"),
        DOWN("v"),
        LEFT("<"),
        RIGHT(">");

        String character;
        Direction(String character) {
            this.character = character;
        }

        static Direction getDirection(String charIn) {
            switch (charIn) {
                case "^":
                    return UP;
                case "v":
                    return DOWN;
                case "<":
                    return LEFT;
                case ">":
                    return RIGHT;
                default:
                    return null;
            }
        }
    }
}
