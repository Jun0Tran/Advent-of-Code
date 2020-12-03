package pepperlola;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day7 {

    private static Map<String, Integer> wires = new HashMap<>();
    private static Map<String, String> parsedWires = new HashMap<>();

    public static int part1(List<String> input) {
        Map<String, Integer> wireValues = new HashMap<>();
        for (String in : input) {
            String[] split = in.split(" ");
            String target = split[split.length - 1];
            String command = joinArray(removeElement(split, indexOfString(split, "->")), " ");
            parsedWires.put(target, command);
        }

        return parseWire(parsedWires.keySet().iterator().next());
    }

    public static int part2(List<String> input) {
        return 0;
    }

    public static int parseWire(String wireName) {
        if (wires.containsKey("a")) return wires.get("a");
        if (wires.containsKey(wireName)) return wires.get(wireName);
        WireCommand command = WireCommand.parse(wireName, parsedWires.get(wireName));
        if (command.getOperation() != WireCommand.Operation.SET) {
            switch (command.getOperation()) {
                case AND:
                    wires.put(wireName, parseWire(command.getName1()) & parseWire(command.getName2()));
                    break;
                case OR:
                    wires.put(wireName, parseWire(command.getName1()) | parseWire(command.getName2()));
                    break;
                case NOT:
                    wires.put(wireName, ~parseWire(command.getName1()));
                    break;
                case LSHIFT:
                    wires.put(wireName, parseWire(command.getName1()) << command.getShiftAmount());
                    break;
                case RSHIFT:
                    wires.put(wireName, parseWire(command.getName1()) >> command.getShiftAmount());
                    break;
            }
        } else {
            if (isInteger(command.getName1())) {
                wires.put(wireName, Integer.parseInt(command.getName1()));
            } else {
                wires.put(wireName, parseWire(command.getName1()));
            }
        }

        return parseWire(command.getName1());
    }

    public static String[] removeElement(String[] array, int index) {
        String[] newArray = new String[array.length - 1];
        for (int i = 0, j = 0; i < array.length; i++) {
            if (i == index) continue;
            newArray[j++] = array[i];
        }
        return newArray;
    }

    public static int indexOfString(String[] array, String substring) {
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(substring)) return i;
        }

        return -1;
    }

    public static String joinArray(String[] array, String delimiter) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < array.length - 1; i ++) {
            builder.append(array[i]).append(delimiter);
        }
        builder.append(array[array.length - 1]);
        return builder.toString();
    }

    public static boolean isInteger(String s) {
        return isInteger(s,10);
    }

    public static boolean isInteger(String s, int radix) {
        if(s.isEmpty()) return false;
        for(int i = 0; i < s.length(); i++) {
            if(i == 0 && s.charAt(i) == '-') {
                if(s.length() == 1) return false;
                else continue;
            }
            if(Character.digit(s.charAt(i),radix) < 0) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        String directory = System.getProperty("user.dir");
        List<String> input = FileUtil.loadFile(directory + "/src/pepperlola/input.txt");
        System.out.println(part1(input));
        System.out.println(part2(input));
    }
}
