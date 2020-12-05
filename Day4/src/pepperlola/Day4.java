package pepperlola;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Day4 {

    public static long part1(List<String> input) {
        List<Passport> passports = parsePassports(input);

        return getValidPassports(passports, true, false);
    }

    public static long part2(List<String> input) {
        List<Passport> passports = parsePassports(input);

        return getValidPassports(passports, true, true);
    }

    public static List<Passport> parsePassports(List<String> input) {
        Queue<String> queue = new LinkedList<>(input);
        List<Passport> passports = new ArrayList<>();
        while (!queue.isEmpty()) {
            Passport currentPassport = new Passport();
            while (queue.peek() != null && !queue.peek().equals("")) {
                String line = queue.remove();
                String[] split = line.split(" ");
                for (String keyValue : split) {
                    if (keyValue.contains(":")) {
                        String[] keyValueSplit = keyValue.split(":");
                        String key = keyValueSplit[0];
                        String value = keyValueSplit[1];
                        currentPassport.set(key, value);
                    }
                }
            }
            while (queue.peek() != null && queue.peek().equals("")) {
                queue.remove();
            }
            passports.add(currentPassport);
        }

        return passports;
    }

    public static int getValidPassports(List<Passport> passports, boolean cidOptional, boolean validateFields) {
        int valid = 0;
        for (Passport passport : passports) {
            if (passport.isValid(cidOptional, validateFields))
                valid ++;
        }
        return valid;
    }

    public static void main(String[] args) {
        String directory = System.getProperty("user.dir");
        List<String> input = FileUtil.loadFile(directory + "/src/pepperlola/input.txt");
        System.out.println(part1(input));
        System.out.println(part2(input));
    }
}
