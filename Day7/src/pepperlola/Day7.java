package pepperlola;

import java.util.*;

public class Day7 {
    public static int part1(List<String> input) {
        Map<String, Bag> bags = getBags(input);

        String targetColor = "shiny gold";
        Set<String> possibleParents = new HashSet<>();
        Queue<Bag> toProcess = new LinkedList<>(bags.get(targetColor).getParents());

        while (!toProcess.isEmpty()) {
            Bag parent = toProcess.remove();
            if (!possibleParents.contains(parent.getColor())) {
                possibleParents.add(parent.getColor());
                toProcess.addAll(parent.getParents());
            }
        }

        return possibleParents.size();
    }

    public static int part2(List<String> input) {
        Map<String, Bag> bags = getBags(input);

        String targetColor = "shiny gold";
        Queue<BagChild> toProcess = new LinkedList<>(bags.get(targetColor).getChildren());

        int totalChildren = 0;
        while (!toProcess.isEmpty()) {
            BagChild child = toProcess.remove();
            for (int i = 0; i < child.getCount(); i++) {
                toProcess.addAll(child.getBag().getChildren());
            }
            totalChildren += child.getCount();
        }

        return totalChildren;
    }

    public static String join(String[] arr, String delimiter) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < arr.length - 1; i++) {
            builder.append(arr[i]).append(delimiter);
        }
        builder.append(arr[arr.length - 1]);
        return builder.toString();
    }

    public static String[] sliceArray(String[] arr, int idx) {
        int newLength = arr.length - idx;
        String[] newArr = new String[newLength];
        for (int i = 0; i < newArr.length; i++) {
            newArr[i] = arr[idx + i];
        }
        return newArr;
    }

    public static Map<String, Bag> getBags(List<String> input) {
        Map<String, Bag> bags = new HashMap<>();
        for (String orbit : input) {
            String[] split = orbit.split(" contain ");
            String targetColor = split[0].replaceAll(" bags?.?", "");
            Bag target = bags.get(targetColor);
            if (target == null) {
                target = new Bag(targetColor);
                bags.put(targetColor, target);
            }

            String targetsString = split[1];
            String[] targetsStrings = targetsString.split(", ");
            for (String targetString : targetsStrings) {
                if (!targetString.contains("no other bags.")) {
                    String[] targetSplit = targetString.split(" ");
                    int quantity = Integer.parseInt(targetSplit[0]);
                    String bagColor = join(sliceArray(targetSplit, 1), " ").replaceAll(" bags?.?", "");
                    Bag child;
                    if (bags.containsKey(bagColor)) {
                        child = bags.get(bagColor);
                    } else {
                        child = new Bag(bagColor);
                        bags.put(bagColor, child);
                    }
                    target.addChild(child, quantity);
                    child.addParent(target);
                }
            }
        }

        return bags;
    }

    public static void main(String[] args) {
        String directory = System.getProperty("user.dir");
        List<String> input = FileUtil.loadFile(directory + "/src/pepperlola/input.txt");
        System.out.println(part1(input));
        System.out.println(part2(input));
    }
}
