package pepperlola;

import java.util.ArrayList;
import java.util.List;

public class Day6 {

    public static int part1(List<String> input) {
        List<List<Boolean>> lights = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            for (int j = 0; j < 1000; j++) {
                if (lights.size() <= i) lights.add(new ArrayList<>());
                lights.get(i).add(false);
            }
        }
        for (String in : input) {
            String[] split = in.split(" ");
            String instruction = split[0].equals("toggle") ? split[0] : split[0] + " " + split[1];
            String[] pos1Split = (split[0].equals("toggle") ? split[1] : split[2]).split(",");
            String[] pos2Split = (split[0].equals("toggle") ? split[3] : split[4]).split(",");
            int xFrom = Integer.parseInt(pos1Split[0]);
            int yFrom = Integer.parseInt(pos1Split[1]);
            int xTo = Integer.parseInt(pos2Split[0]) + 1;
            int yTo = Integer.parseInt(pos2Split[1]) + 1;

            switch (instruction) {
                case "turn on":
                    for (int x = xFrom; x < xTo; x++)
                        for (int y = yFrom; y < yTo; y++)
                            lights.get(y).set(x, true);
                    break;
                case "turn off":
                    for (int x = xFrom; x < xTo; x++)
                        for (int y = yFrom; y < yTo; y++)
                            lights.get(y).set(x, false);
                    break;
                case "toggle":
                    for (int x = xFrom; x < xTo; x++)
                        for (int y = yFrom; y < yTo; y++)
                            lights.get(y).set(x, !lights.get(y).get(x));
                    break;
            }
        }

        int litLights = 0;
        for (List<Boolean> listX : lights)
            for (boolean lit : listX)
                if (lit) litLights ++;

        return litLights;
    }

    public static int part2(List<String> input) {
        List<List<Integer>> lights = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            for (int j = 0; j < 1000; j++) {
                if (lights.size() <= i) lights.add(new ArrayList<>());
                lights.get(i).add(0);
            }
        }
        for (String in : input) {
            String[] split = in.split(" ");
            String instruction = split[0].equals("toggle") ? split[0] : split[0] + " " + split[1];
            String[] pos1Split = (split[0].equals("toggle") ? split[1] : split[2]).split(",");
            String[] pos2Split = (split[0].equals("toggle") ? split[3] : split[4]).split(",");
            int xFrom = Integer.parseInt(pos1Split[0]);
            int yFrom = Integer.parseInt(pos1Split[1]);
            int xTo = Integer.parseInt(pos2Split[0]) + 1;
            int yTo = Integer.parseInt(pos2Split[1]) + 1;

            switch (instruction) {
                case "turn on":
                    for (int x = xFrom; x < xTo; x++)
                        for (int y = yFrom; y < yTo; y++)
                            lights.get(y).set(x, lights.get(y).get(x) + 1);
                    break;
                case "turn off":
                    for (int x = xFrom; x < xTo; x++)
                        for (int y = yFrom; y < yTo; y++) {
                            int currentBrightness = lights.get(y).get(x);
                            lights.get(y).set(x, currentBrightness > 0 ? currentBrightness - 1 : currentBrightness);
                        }
                    break;
                case "toggle":
                    for (int x = xFrom; x < xTo; x++)
                        for (int y = yFrom; y < yTo; y++)
                            lights.get(y).set(x, lights.get(y).get(x) + 2);
                    break;
            }
        }

        int totalBrightness = 0;
        for (List<Integer> listX : lights)
            for (int brightness : listX)
                totalBrightness += brightness;

        return totalBrightness;
    }

    public static void main(String[] args) {
        String directory = System.getProperty("user.dir");
        List<String> input = FileUtil.loadFile(directory + "/src/pepperlola/input.txt");
//        System.out.println(part1(input));
        System.out.println(part2(input));
    }
}
