package pepperlola;

import java.util.*;

public class Day8 {
    public static int part1(List<String> input) {
        return runCommand(input, 0, new TreeSet<>(), 0);
    }

    public static int runCommand(List<String> input, int i, TreeSet<Integer> visited, int acc) {
        if (i == input.size() || visited.contains(i))
            return acc;
        visited.add(i);
        String in = input.get(i);
        String[] split = in.split(" ");
        String op = split[0];
        String valueString = split[1];
        int sign = valueString.charAt(0) == '+' ? 1 : -1;
        int value = Integer.parseInt(valueString.replaceAll("[^0-9]", ""));

        int dI = 1;

        switch (op.toUpperCase()) {
            case "ACC":
                acc += value * sign;
                break;
            case "JMP":
                dI = value * sign;
                break;
            case "NOP":
                break;
        }

        return runCommand(input, i + dI, visited, acc);
    }

    public static int runCommand2(List<String> input, int i, Set<Integer> visited, boolean swap) {
        if (i == input.size())
            return -1;
        if (visited.contains(i))
            return -2;
        visited.add(i);
        String in = input.get(i);
        String[] split = in.split(" ");
        String op = split[0];
        String valueString = split[1];
        int sign = valueString.charAt(0) == '+' ? 1 : -1;
        int value = Integer.parseInt(valueString.replaceAll("[^0-9]", ""));

        int returnValue = -1;
        switch (op.toUpperCase()) {
            case "ACC":
                // acc += value * sign;
                returnValue = runCommand2(input, i + 1, visited, swap);
                break;
            case "JMP":
                returnValue = runCommand2(input, i + value * sign, visited, swap);

                if (returnValue == -2 && !swap) {
                    returnValue = runCommand2(input, i + 1, visited, true);

                    if (returnValue == -1) {
                        returnValue = i;
                    } else {
                        returnValue = -2;
                    }
                }

                break;
            case "NOP":
                returnValue = runCommand2(input, i + 1, visited, swap);

                if (returnValue == -2 && !swap) {
                    returnValue = runCommand2(input, i + value * sign, visited, true);

                    if (returnValue == -1) {
                        returnValue = i;
                    } else {
                        returnValue = -2;
                    }
                }
                break;
        }

        visited.remove(i);
        return returnValue;
    }

    public static int part2(List<String> input) {
        int i = runCommand2(input, 0, new TreeSet<>(), false);

        if (i == -2) {
            return -1;
        } else if (i == -1) {
            return runCommand(input, 0, new TreeSet<>(), 0);
        }

        List<String> newInput = new ArrayList<>(input);
        String instruction = newInput.get(i);
        if (instruction.contains("jmp")) {
            newInput.set(i, instruction.replaceAll("jmp", "nop"));
        } else if (instruction.contains("nop")) {
            newInput.set(i, instruction.replaceAll("nop", "jmp"));
        }

        return part1(newInput);
    }

    public static void main(String[] args) {
        String directory = System.getProperty("user.dir");
        List<String> input = FileUtil.loadFile(directory + "/src/pepperlola/input.txt");
        System.out.println("PART 1: " + part1(input));
        System.out.println("PART 2: " + part2(input));
    }
}
