package pepperlola;

import java.lang.reflect.Array;
import java.util.*;

public class Day14 {
    public static long part1(List<String> input) {
        return calculateNth(2020, input);
    }

    public static long part2(List<String> input) {
        return calculateNth(30000000, input);
    }

    public static int calculateNth(int nth, List<String> input) {
        Map<Integer, List<Integer>> posMap = new HashMap<>(); // tuple is (last index, occurrences)
        String[] split = input.get(0).split(",");

        for (int i = 0; i < split.length; i++) {
            int n = Integer.parseInt(split[i]);
            List<Integer> list = posMap.containsKey(n) ? posMap.get(n) : new ArrayList<>();
            list.add(i);
            posMap.put(n, list);
        }

        int lastNumber = Integer.parseInt(split[split.length - 1]);

        for (int i = split.length; i < nth; i++) {
            int result = 0;
            List<Integer> list = posMap.containsKey(lastNumber) ? posMap.get(lastNumber) : new ArrayList<>();

            if (list.size() > 1) {
                result = i - 1 - list.get(list.size() - 2);
            }

            List<Integer> resultList = posMap.containsKey(result) ? posMap.get(result) : new ArrayList<>();
            resultList.add(i);

            posMap.put(result, resultList);
            lastNumber = result;
        }

        for (int key : posMap.keySet()) {
            if (posMap.get(key).get(posMap.get(key).size() - 1) == nth - 1) {
                return key;
            }
        }

        System.out.println(posMap.toString());

        return 0;
    }

    public static void main(String[] args) {
        String directory = System.getProperty("user.dir");
        List<String> input = FileUtil.loadFile(directory + "/src/pepperlola/input.txt");
        System.out.println("PART 1: " + part1(input));
        System.out.println("PART 2: " + part2(input));
    }
}
