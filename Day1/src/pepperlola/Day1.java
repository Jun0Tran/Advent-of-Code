package pepperlola;

import java.util.List;

public class Day1 {

    public static int part1(List<String> input) {
        String inst = input.get(0);
        int floor = 0;
        for (String in : inst.split(""))
            floor += in.equals("(") ? 1 : -1;
        return floor;
    }

    public static int part2(List<String> input) {
        String inst = input.get(0);
        int i;
        int floor = 0;
        for (i = 0; i < inst.length(); i++) {
            floor += inst.charAt(i) == '(' ? 1 : -1;
            if (floor < 0) break;
        }
        return i + 1;
    }

    public static void main(String[] args) {
        String directory = System.getProperty("user.dir");
        List<String> input = FileUtil.loadFile(directory + "/src/pepperlola/input.txt");
        System.out.println(part1(input));
        System.out.println(part2(input));

    }
}
