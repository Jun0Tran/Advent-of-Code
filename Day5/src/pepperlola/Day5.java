package pepperlola;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day5 {

    public static long part1(List<String> input) {
        int highestId = 0;
        for (String line : input) {
            int low = 0;
            int high = 127;
            int row = 0;

            char[] chars = line.toCharArray();
            for (int i = 0; i < chars.length - 4; i++) {
                char character = chars[i];

                if (character == 'B') {
                    low = (low + high) / 2 + 1;
                } else if (character == 'F') {
                    high = (low + high) / 2;
                }
            }
            if (chars[chars.length - 4] == 'B') {
                row = high;
            } else if (chars[chars.length - 4] == 'F') {
                row = low;
            }
            int column = 0;
            low = 0;
            high = 7;
            for (int i = chars.length - 3; i < chars.length - 1; i++) {
                char character = chars[i];

                if (character == 'R') {
                    low = (low + high) / 2 + 1;
                } else if (character == 'L') {
                    high = (low + high) / 2;
                }
            }
            if (chars[chars.length - 1] == 'R') {
                column = high;
            } else if (chars[chars.length - 1] == 'L') {
                column = low;
            }

            int seatId = row * 8 + column;
            if (seatId > highestId)
                highestId = seatId;
        }
        return highestId;
    }

    public static long part2(List<String> input) {
        int highestId = 0;
        List<Integer> idList = new ArrayList<>();
        for (String line : input) {
            int low = 0;
            int high = 127;
            int row = 0;

            char[] chars = line.toCharArray();
            for (int i = 0; i < chars.length - 4; i++) {
                char character = chars[i];

                if (character == 'B') {
                    low = (low + high) / 2 + 1;
                } else if (character == 'F') {
                    high = (low + high) / 2;
                }
            }
            if (chars[chars.length - 4] == 'B') {
                row = high;
            } else if (chars[chars.length - 4] == 'F') {
                row = low;
            }
            int column = 0;
            low = 0;
            high = 7;
            for (int i = chars.length - 3; i < chars.length - 1; i++) {
                char character = chars[i];

                if (character == 'R') {
                    low = (low + high) / 2 + 1;
                } else if (character == 'L') {
                    high = (low + high) / 2;
                }
            }
            if (chars[chars.length - 1] == 'R') {
                column = high;
            } else if (chars[chars.length - 1] == 'L') {
                column = low;
            }

            int seatId = row * 8 + column;
            idList.add(seatId);
        }

        Integer[] array = idList.toArray(new Integer[0]);
        Arrays.sort(array);

        int low = 0;
        int high = array.length - 1;

        int index = 0;
        while (low < high) {
            index = (low + high) / 2;
            if (array[index] == array[low] + (index - low)) {
                low = index + 1;
            } else {
                high = index;
            }
        }

        return array[index] + 1;
    }

    public static void main(String[] args) {
        String directory = System.getProperty("user.dir");
        List<String> input = FileUtil.loadFile(directory + "/src/pepperlola/input.txt");
        System.out.println(part1(input));
        System.out.println(part2(input));
    }
}
