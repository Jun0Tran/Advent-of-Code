package pepperlola;

import java.util.List;

public class Day1 {
    public static int part1(List<String> input) {
        for (String in1 : input)
            for (String in2 : input) {
                int n1 = Integer.parseInt(in1);
                int n2 = Integer.parseInt(in2);

                if (n1 + n2 == 2020) {
                    return (n1 * n2);
                }
            }
        return 0;
    }

public static int part2(List<String> input) {
        for (String in1 : input) 
            for (String in2 : input)
                for (String in3 : input) {
                    int n1 = Integer.parseInt(in1);
                    int n2 = Integer.parseInt(in2);
                    int n3 = Integer.parseInt(in3);
                    if (n1 + n2 + n3 == 2020)
                        return n1 * n2 * n3;
                }
        return 0;
    }

    public static void main(String[] args) {
        String directory = System.getProperty("user.dir");
        List<String> input = FileUtil.loadFile(directory + "/src/pepperlola/input1.txt");
        System.out.println(part1(input));
        System.out.println(part2(input));
    }
}
