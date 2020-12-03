package pepperlola;

import java.util.List;

public class Day3 {

    public static long part1(List<String> input) {
        return getTrees(input, 3, 1);
    }

    public static long part2(List<String> input) {
        long trees1 = getTrees(input, 1, 1);
        long trees2 = getTrees(input, 3, 1);
        long trees3 = getTrees(input, 5, 1);
        long trees4 = getTrees(input, 7, 1);
        long trees5 = getTrees(input, 1, 2);

        System.out.println(trees1 + " | " + trees2 + " | " + trees3 + " | " + trees4 + " | " + trees5);

        return trees1 * trees2 * trees3 * trees4 * trees5;
    }

    public static long getTrees(List<String> input, int dX, int dY) {
        int trees = 0;
        int x = 0;
        for (int y = 0; y < input.size(); y += dY) {
            String line = input.get(y);
            if (line.charAt(x) == '#')
                trees++;

            x += dX;
            x %= line.length();
        }

        return trees;
    }

    public static void main(String[] args) {
        String directory = System.getProperty("user.dir");
        List<String> input = FileUtil.loadFile(directory + "/src/pepperlola/input.txt");
        System.out.println(part1(input));
        System.out.println(part2(input));
    }
}
