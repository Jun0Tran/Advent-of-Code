package pepperlola;

import java.util.*;

public class Day14 {
    public static long part1(List<String> input) {
        Mask mask = new Mask(0, 0);
        Map<Long, Long> memory = new TreeMap<>();
        for (String s : input) {
            if (s.startsWith("mask = ")) {
                mask = Mask.parseMask1(s);
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

    public static long parseValue(String in) {
        String[] split = in.split("");
        long total = 0;
        int length = in.length();
        int power = 0;
        for (int i = length - 1; i >= 0; i--) {
            int value = Integer.parseInt(split[i]);
            total += value == 1 ? Math.pow(2, power) : 0;
            power++;
        }

        return total;
    }

    public static long part2(List<String> input) {
        List<Mask> masks = new ArrayList<>();
        Map<Long, Long> memory = new TreeMap<>();
        for (String s : input) {
            if (s.startsWith("mask = ")) {
                masks = Mask.parseMask2(s);
            } else {
                String[] splitOnEquals = s.split(" = ");
                for (Mask mask : masks) {
                    long result = Long.parseLong(splitOnEquals[1]);
                    String first = splitOnEquals[0];
                    String indexString = first.substring(first.indexOf('[') + 1, first.indexOf(']'));
                    long index = Long.parseLong(indexString);
                    index = mask.apply(index);
                    memory.put(index, result);
                }
            }
        }

        long sum = 0;
        for (long l : memory.values()) {
            sum += l;
        }

        return sum;
    }

    public static void main(String[] args) {
        String directory = System.getProperty("user.dir");
        List<String> input = FileUtil.loadFile(directory + "/src/pepperlola/input.txt");
        System.out.println("PART 1: " + part1(input));
        System.out.println("PART 2: " + part2(input));
    }

    static class Mask {
        private final String orMask;
        private final String andMask;

        public Mask(long orMask, long andMask) {
            this.orMask = Long.toBinaryString(orMask);
            this.andMask = Long.toBinaryString(andMask);
        }

        public static Mask parseMask1(String mask) {
            String maskString = mask.replaceAll("mask = ", "");
            return new Mask(parseValue(maskString.replaceAll("X", "0")), parseValue(maskString.replaceAll("X", "1")));
        }

        public static List<Mask> parseMask2(String maskString) {
            maskString = maskString.replaceAll("mask = ", "").replace("X", "Y").replaceAll("0", "X");
            List<Mask> result = new ArrayList<>();
            Queue<String> toProcess = new LinkedList<>();
            toProcess.add(maskString);

            while (!toProcess.isEmpty()) {
                String processing = toProcess.remove();
                if (processing.contains("Y")) {
                    toProcess.add(processing.replaceFirst("Y", "0"));
                    toProcess.add(processing.replaceFirst("Y", "1"));
                } else {
                    result.add(Mask.parseMask1(processing));
                }
            }

            return result;
        }

        public long apply(long in) {
            in |= parseValue(orMask);
            in &= parseValue(andMask);

            return in;
        }

        public long getOrMask() {
            return parseValue(orMask);
        }

        public long getAndMask() {
            return parseValue(andMask);
        }

        public String getOrMaskString() {
            return orMask;
        }

        public String getAndMaskString() {
            return andMask;
        }
    }
}
