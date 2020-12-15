package pepperlola;

import java.math.BigInteger;
import java.util.*;

public class Day14 {
    static class Mask {
        private long orMask;
        private long andMask;

        public Mask(long orMask, long andMask) {
            this.orMask = orMask;
            this.andMask = andMask;
        }

        public long apply(long in) {
            in |= orMask;
            in &= andMask;

            return in;
        }

        public long getOrMask() {
            return orMask;
        }

        public long getAndMask() {
            return andMask;
        }
    }

    public static long part1(List<String> input) {

        Mask mask = new Mask(0, 0);
        Map<Long, Long> memory = new TreeMap<>();
        for (String s : input) {
            if (s.startsWith("mask = ")) {
                String maskString = s.replaceAll("mask = ", "");
                mask = new Mask(parseValue(maskString.replaceAll("X", "0")), parseValue(maskString.replaceAll("X", "1")));
            } else {
                String[] splitOnEquals = s.split(" = ");
                long result = Long.parseLong(splitOnEquals[1]);
                result = mask.apply(result);
                String first = splitOnEquals[0];
                String indexString = first.substring(first.indexOf('[') + 1, first.indexOf(']'));
                long index = Long.parseLong(indexString);
                memory.put(index, result);
            }
        }

        long sum = 0;
        for (long l : memory.values()) {
            sum += l;
        }

        return sum;
    }

    public static long applyMask(String in, String mask) {
        int length = mask.length();
        while (in.length() < length) {
            in = "0" + in;
        }
        StringBuilder currentValue = new StringBuilder(in);

        for (int i = length - 1; i > 0; i --) {
            if (mask.charAt(i) == 'X') {
                currentValue.setCharAt(i, in.charAt(i));
            } else {
                currentValue.setCharAt(i, mask.charAt(i));
            }
        }

        return parseValue(currentValue.toString());
    }

    public static long parseValue(String in) {
        String[] split = in.split("");
        long total = 0;
        int length = in.length();
        int power = 0;
        for (int i = length - 1; i >= 0; i--) {
            int value = Integer.parseInt(split[i]);
            total += value == 1 ? Math.pow(2, power) : 0;
            power ++;
        }

        return total;
    }


    public static long part2(List<String> input) {
        return 0;
    }

    public static void main(String[] args) {
        String directory = System.getProperty("user.dir");
        List<String> input = FileUtil.loadFile(directory + "/src/pepperlola/input.txt");
        System.out.println("PART 1: " + part1(input));
        System.out.println("PART 2: " + part2(input));
    }
}
