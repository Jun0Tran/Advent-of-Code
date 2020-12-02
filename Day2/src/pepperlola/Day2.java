package pepperlola;

import java.util.List;

public class Day2 {

    public static int part1(List<String> input) {
        int totalPaper = 0;
        for (String in : input) {
            String[] inSplit = in.split("x");
            int l = Integer.parseInt(inSplit[0]);
            int w = Integer.parseInt(inSplit[1]);
            int h = Integer.parseInt(inSplit[2]);

            totalPaper += getWrappingPaper(l, w, h);
        }
        return totalPaper;
    }

    public static int part2(List<String> input) {
        int totalRibbon = 0;

        for (String in : input) {
            String[] inSplit = in.split("x");
            int l = Integer.parseInt(inSplit[0]);
            int w = Integer.parseInt(inSplit[1]);
            int h = Integer.parseInt(inSplit[2]);

            int perim1 = 2*l + 2*h;
            int perim2 = 2*h + 2*w;
            int perim3 = 2*l + 2*w;

            int minPerimeter = Math.min(Math.min(perim1, perim2), perim3);

            int volume = l * w * h;
            totalRibbon += volume + minPerimeter;
        }

        return totalRibbon;
    }

    public static int getWrappingPaper(int l, int w, int h) {
        int side1 = l*w;
        int side2 = w*h;
        int side3 = h*l;
        return 2*side1 + 2*side2 + 2*side3 + Math.min(Math.min(side1, side2), side3);
    }

    public static void main(String[] args) {
        String directory = System.getProperty("user.dir");
        List<String> input = FileUtil.loadFile(directory + "/src/pepperlola/input.txt");
        System.out.println(part1(input));
        System.out.println(part2(input));
    }
}
