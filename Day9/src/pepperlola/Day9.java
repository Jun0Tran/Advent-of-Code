package pepperlola;

import com.sun.source.tree.Tree;

import java.util.*;

public class Day9 {
    public static long part1(List<String> input) {
        int preamble = 25;
        int i = preamble; // preamble length
        for (; i < input.size(); i++) {
            if (!validateNumber(input, i, preamble))
               break;
        }

        return Long.parseLong(input.get(i));
    }

    public static long part2(List<String> input) {
        long invalid = part1(input);

        Set<Long> numbers = new TreeSet<>();
        for (int i = 0; i < input.size(); i++) {
            long sum = Long.parseLong(input.get(i));
            numbers.add(sum);

            int index = i + 1;

            while (sum < invalid) {
                long n = Long.parseLong(input.get(index));
                numbers.add(n);
                sum += n;
                index ++;
            }

            if (sum == invalid && numbers.size() > 1) {
                return addMinAndMax(numbers);
            }

            numbers.clear();
        }

        return -1;
    }

    public static boolean validateNumber(List<String> input, int i, int lookBack) {
        long numberToValidate = Long.parseLong(input.get(i));
        for (int j = Math.max(0, i - lookBack); j < i; j++) {
            for (int k = Math.max(0, i - lookBack); k < i; k++) {
                if (j == k) continue;
                long n1 = Long.parseLong(input.get(j));
                long n2 = Long.parseLong(input.get(k));

                if (n1 + n2 == numberToValidate) return true;
            }
        }

        return false;
    }

    public static long sumSet(Set<Long> set) {
        long sum = 0;
        for (long value : set) {
            sum += value;
        }

        return sum;
    }

    public static long addMinAndMax(Set<Long> set) {
        long min = Integer.MAX_VALUE;

        for (long n : set) {
            if (n < min) min = n;
        }

        long max = 0;
        for (long n : set) {
            if (n > max) max = n;
        }

        return min + max;
    }

    public static void main(String[] args) {
        String directory = System.getProperty("user.dir");
        List<String> input = FileUtil.loadFile(directory + "/src/pepperlola/input.txt");
        System.out.println("PART 1: " + part1(input));
        System.out.println("PART 2: " + part2(input));
    }
}
