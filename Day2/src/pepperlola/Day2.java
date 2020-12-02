package pepperlola;

import java.util.List;

public class Day2 {

    public static int part1(List<String> input) {
        int correct = 0;
        for (String in : input) {
            String[] inSplit = in.replace(":", "").split(" ");
            String policy = inSplit[0];
            String letter = inSplit[1];
            String pass = inSplit[2];

            String[] policySplit = policy.split("-");
            int low = Integer.parseInt(policySplit[0]);
            int high = Integer.parseInt(policySplit[1]);

            int letterOccurrences = 0;
            for (String passLetter : pass.split("")) {
                if (passLetter.equals(letter))
                    letterOccurrences ++;
            }

            if (letterOccurrences >= low && letterOccurrences <= high)
                correct ++;
        }

        return correct;
    }

    public static int part2(List<String> input) {
        int correct = 0;
        for (String in : input) {
            String[] inSplit = in.replace(":", "").split(" ");
            String policy = inSplit[0];
            char letter = inSplit[1].charAt(0);
            String pass = inSplit[2];

            String[] policySplit = policy.split("-");
            int pos1 = Integer.parseInt(policySplit[0]);
            int pos2 = Integer.parseInt(policySplit[1]);

            if (xor(pass.charAt(pos1 - 1) == letter, pass.charAt(pos2 - 1) == letter))
                correct ++;
        }

        return correct;
    }

    public static boolean xor(boolean a, boolean b) {
        return (a || b) && (!a || !b);
    }

    public static void main(String[] args) {
        String directory = System.getProperty("user.dir");
        List<String> input = FileUtil.loadFile(directory + "/src/pepperlola/input.txt");
        System.out.println(part1(input));
        System.out.println(part2(input));
    }
}
