package pepperlola;

import java.util.*;

public class Day13 {
    public static int part1(List<String> input) {
        int timestamp = Integer.parseInt(input.get(0));
        List<String> busIds = Arrays.asList(input.get(1).split(","));

        List<Integer> departures = new ArrayList<>();
        List<Integer> divs = new ArrayList<>();

        for (String busIdString : busIds) {
            if (busIdString.equals("x")) continue;
            int busId = Integer.parseInt(busIdString);
            int div = (int) Math.ceil((float) timestamp / busId);
            departures.add(div * busId);
            divs.add(div);
        }

        int minTime = minValue(departures);
        int div = divs.get(departures.indexOf(minTime));

        int waitingTime = minTime - timestamp;

        int busId = (int) Math.ceil((float) timestamp / div);

        return waitingTime * busId;
    }

    public static class Bus {
        long busId;
        long offset;
        long nBar;
        long u;

        public Bus(int busId, int offset) {
            this.busId = busId;
            this.offset = offset;
        }
    }

    public static long calculateU(Bus bus) {
        long u = 1;
        long nRemainder = bus.nBar % bus.busId;
        while (true) {
            if ((nRemainder * u) % bus.busId == 1) {
                return u;
            } else {
                u ++;
            }
        }
    }

    public static long part2(List<String> input) {
        List<Bus> buses = new ArrayList<>();
        String[] split = input.get(1).split(",");
        for (int i = 0; i < split.length; i++) {
            String busIdString = split[i];
            if (!busIdString.equals("x")) {
                buses.add(new Bus(Integer.parseInt(busIdString), split.length - i - 1));
            }
        }

        long product = 1;

        for (Bus bus : buses) {
            product *= bus.busId;
        }

        for (Bus bus : buses) {
            bus.nBar = product / bus.busId;
        }

        for (Bus bus : buses) {
            bus.u = calculateU(bus);
        }

        long result = 0;

        for (Bus bus : buses) {
            result += bus.offset * bus.nBar * bus.u;
        }

        result %= product;

        return result - (split.length - 1);
    }

    public static int minValue(List<Integer> set) {
        int min = Integer.MAX_VALUE;
        for (int i : set) {
            if (i < min) min = i;
        }

        return min;
    }

    public static Tuple<Integer, Integer> max(List<Tuple<Integer, Integer>> set, boolean lowestX) {
        Tuple<Integer, Integer> max = new Tuple<>(0, 0);
        for (Tuple<Integer, Integer> t : set) {
            if ((lowestX ? t.getX() : t.getY()) > (lowestX ? max.getX() : max.getY())) max = t;
        }

        return max;
    }

    public static List<Tuple<Integer, Integer>> sort(List<Tuple<Integer, Integer>> list, boolean sortX) {
        List<Tuple<Integer, Integer>> newArray = new ArrayList<>(list);
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.size() - i - 1; j++) {
                Tuple<Integer, Integer> t = list.get(j);
                Tuple<Integer, Integer> t2 = list.get(j + 1);
                int value = sortX ? t.getX() : t.getY();
                int nextValue = sortX ? t2.getX() : t2.getY();

                if (value > nextValue) {
                    newArray.set(j, t2);
                    newArray.set(j + 1, t);
                }
            }
        }

        return newArray;
    }

    public static void main(String[] args) {
        String directory = System.getProperty("user.dir");
        List<String> input = FileUtil.loadFile(directory + "/src/pepperlola/input.txt");
        System.out.println("PART 1: " + part1(input));
        System.out.println("PART 2: " + part2(input));
    }
}
