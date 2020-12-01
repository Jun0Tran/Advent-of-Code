package pepperlola;

import java.util.List;

public class Day1 {
    public static void main(String[] args) {
        String directory = System.getProperty("user.dir");
        List<String> input = FileUtil.loadFile(directory + "/src/pepperlola/input1.txt");
        for (String in1 : input) {
            for (String in2 : input) {
                int n1 = Integer.parseInt(in1);
                int n2 = Integer.parseInt(in2);

                if (n1 + n2 == 2020) {
                    System.out.println(n1 * n2);
                }
            }
        }
    }
}
