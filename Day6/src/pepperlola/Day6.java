package pepperlola;

import java.util.*;
import java.util.stream.Collectors;

public class Day6 {

    public static long part1(List<String> input) {
        List<List<String>> groupsQuestions = new ArrayList<>(parsePassports(input).values());

        // removing duplicates
        for (int i = 0; i < groupsQuestions.size(); i++) {
            groupsQuestions.set(i, removeDuplicates(groupsQuestions.get(i)));
        }

        // sum of the sizes of all the lists
        return groupsQuestions.stream().mapToInt(List::size).sum();
    }

    public static long part2(List<String> input) {
        int totalYes = 0;

        Queue<String> queue = new LinkedList<>(input);


        while (!queue.isEmpty()) {
            List<String> answers = new LinkedList<>(Arrays.asList("abcdefghijklmnopqrstuvwxyz".split("")));
            List<String> toReturn = new LinkedList<>(answers);

            while (queue.peek() != null && !queue.peek().equals("")) {
                String line = queue.remove();
                for (String answer : answers) {
                    if (!line.contains(answer)) {
                        toReturn.remove(answer);
                    }
                }
            }
            totalYes += toReturn.size();
            while (queue.peek() != null && queue.peek().equals("")) {
                queue.remove();
            }
        }

        return totalYes;
    }

    public static Map<Group, List<String>> parsePassports(List<String> input) {
        // essentially the same as day 4
        Queue<String> queue = new LinkedList<>(input);
        Map<Group, List<String>> groupsQuestions = new HashMap<>();
        while (!queue.isEmpty()) {
            List<String> currentGroup = new ArrayList<>();
            int groupSize = 0;
            while (queue.peek() != null && !queue.peek().equals("")) {
                String line = queue.remove();
                currentGroup.addAll(Arrays.asList(line.split("")));
                groupSize ++;
            }
            while (queue.peek() != null && queue.peek().equals("")) {
                queue.remove();
            }
            groupsQuestions.put(new Group(groupSize), currentGroup);
        }

        return groupsQuestions;
    }

    public static List<String> removeDuplicates(List<String> in) {
        Set<String> linkedSet = new LinkedHashSet<>(in);
        in.clear();
        in.addAll(linkedSet);

        return in;
    }

    public static long countOccurrences(String string, List<String> in) {
        return in.stream().filter(p -> p.equals(string)).count();
    }

    public static void main(String[] args) {
        String directory = System.getProperty("user.dir");
        List<String> input = FileUtil.loadFile(directory + "/src/pepperlola/input.txt");
        System.out.println(part1(input));
        System.out.println(part2(input));
    }
}
