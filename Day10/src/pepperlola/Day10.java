package pepperlola;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Day10 {
    public static int part1(List<String> input) {
        List<Integer> intList = new ArrayList<>();

        for (String s : input) {
            intList.add(Integer.parseInt(s));
        }

        intList = intList.stream().sorted().collect(Collectors.toList());

        List<Integer> dist = new ArrayList<>();

        // first adapter, because charging point counts and has rating of 0
        dist.add(intList.get(0));

        for (int i = 0; i < intList.size() - 1; i++) {
            int val = intList.get(i + 1) - intList.get(i);
            if (0 <= val && val <= 3) {
                while (dist.size() <= val - 1) {
                    dist.add(0);
                }
                dist.set(val - 1, dist.get(val - 1) + 1);
            }
        }

        dist.set(dist.size() - 1, dist.get(dist.size() - 1) + 1); // device's adapter

        System.out.println(dist);

        return dist.get(0) * dist.get(2);
    }

    public static long part2(List<String> input) {
        List<Integer> intList = new ArrayList<>();

        for (String s : input) {
            intList.add(Integer.parseInt(s));
        }

        intList = intList.stream().sorted().collect(Collectors.toList());

        System.out.println(intList);

        long value = possibleArrangements(0, 0, intList, new HashMap<>());
        return value;
    }

    public static long possibleArrangements(int previousJoltage, int index, List<Integer> intList, Map<String, Long> calculatedValues) {
        int joltage = intList.get(index);
        System.out.println(previousJoltage + " | " + joltage);
        String key = previousJoltage + "_" + joltage;
        if (calculatedValues.containsKey(key)) {
            return calculatedValues.get(key);
        }

        if (index == intList.size() - 1) {
            return 1;
        }

        boolean areWeOptional = isValidAdapter(previousJoltage, intList.get(index + 1));
        long arrangementsAfterUs = possibleArrangements(joltage, index + 1, intList, calculatedValues);
        if (!areWeOptional) {
            calculatedValues.put(key, arrangementsAfterUs);
            return arrangementsAfterUs;
        }

        long total = possibleArrangements(previousJoltage, index + 1, intList, calculatedValues) + arrangementsAfterUs;

        calculatedValues.put(key, total);

        return total;
    }

    public static boolean isValidAdapter(int joltage, int nextJoltage) {
        int v = nextJoltage - joltage;
        return 1 <= v && v <= 3;
    }

    public static void main(String[] args) {
        String directory = System.getProperty("user.dir");
        List<String> input = FileUtil.loadFile(directory + "/src/pepperlola/input.txt");
        System.out.println("PART 1: " + part1(input));
        System.out.println("PART 2: " + part2(input));
    }
}
