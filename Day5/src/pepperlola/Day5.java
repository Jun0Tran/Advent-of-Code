package pepperlola;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day5 {

    private static final String[] badStrings = new String[]{"ab", "cd", "pq", "xy"};
    private static final String[] vowels = new String[]{"a", "e", "i", "o", "u"};
    private static final String split2CharRegex = "(?<=\\G..)";

    public static int part1(List<String> input) {
        return (int) input.stream().filter(Day5::validateString1).count();
    }

    public static int part2(List<String> input) {
        return (int) input.stream().filter(Day5::validateString2).count();
//        return validateString2("xxyxx") ? 1 : 0;
    }

    public static boolean validateString1(String string) {
        for (String badString : badStrings) {
            if (string.contains(badString)) return false;
        }
        int totalVowels = 0;
        for (String vowel : vowels) {
            totalVowels += countChars(string, vowel);
        }
        if (totalVowels < 3) return false;
        for (int i = 0; i < string.length() - 1; i++) {
            char currentChar = string.charAt(i);
            char nextChar = string.charAt(i + 1);
            if (currentChar == nextChar) return true;
        }
        return false;
    }

    public static boolean validateString2(String string) {
        String[] split = string.split("");
        boolean containsMultiple = false;
        for (int i = 0; i < split.length - 1; i++) {
            for (int j = 0; j < split.length - 1; j++) {
                if (i == j || Math.abs(i - j) == 1) continue;
                String s1 = split[i] + split[i + 1];
                String s2 = split[j] + split[j + 1];
                if (s1.equals(s2)) {
                    containsMultiple = true;
                    break;
                }
            }
            if (containsMultiple) break;
        }
        if (!containsMultiple) return false;
        for (String c : split) {
            Pattern pattern = Pattern.compile(c + "." + c);
            Matcher matcher = pattern.matcher(string);
            if (matcher.find()) {
                return true;
            }
        }
        return false;
    }

    public static int countChars(String in, String c) {
        int count = 0;
        for (String s : in.split("")) {
            if (s.equals(c)) count++;
        }
        return count;
    }

    public static int countStringInstances(String in, String c) {
        in = in.replaceFirst(c, "");
        return (in.split(c, -1).length) - 1;
    }

    public static void main(String[] args) {
        String directory = System.getProperty("user.dir");
        List<String> input = FileUtil.loadFile(directory + "/src/pepperlola/input.txt");
        System.out.println(part1(input));
        System.out.println(part2(input));
    }
}
