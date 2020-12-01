package pepperlola;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
//import java.lang.Math;

public class day6 {
    public static int getTotalOrbits(List<String> input) {
        int totalOrbits = 0;
        HashMap<String, List<String>> planets = new HashMap<>();
        for (String orbit : input) {
            String[] split = orbit.split("[)]");
            String target = split[0];
            String satellite = split[1];

            if (!planets.containsKey(target)) {
                planets.put(target, new ArrayList<>());
            }
            planets.get(target).add(satellite);

        }
        for (String target : planets.keySet()) {
            for (String savedTarget : planets.keySet()) {
                List<String> savedSatellites = planets.get(savedTarget);
                if (savedSatellites.contains(target)) {
                    planets.get(savedTarget).addAll(planets.get(target));
                }
            }
        }
        /**
         * C)D
         * B)C
         * A)B
         */
        System.out.println(planets.toString());
        for (List<String> satellites : planets.values()) {
            totalOrbits += satellites.size();
        }
        //System.out.println(planets);
        // System.out.println(totalOrbits);
        return totalOrbits;
    }

    public static int orbitalTransfers(List<String> input) {
        List<Planet> planets = new ArrayList<>();
        for (String orbit : input) {
            String[] split = orbit.split("[)]");
            Planet target = new Planet(split[0]);
            Planet satellite = new Planet(split[1]);

            if (planets.contains(target)) {
                planets.get(planets.indexOf(target)).addSatellite(satellite);
            } else {
                target.addSatellite(satellite);
                planets.add(target);
            }

            if (planets.contains(satellite)) {
                planets.get(planets.indexOf(satellite)).addTarget(target);
            } else {
                satellite.addTarget(target);
                planets.add(satellite);
            }
        }

        for (Planet planet : planets) {
            List<Planet> savedTargets = new ArrayList<>(planet.getTargets());
            for (Planet savedTarget : savedTargets) {
                if (planets.contains(savedTarget)) {
                    planet.replaceTarget(planets.get(planets.indexOf(savedTarget)));
                }
            }
            List<Planet> savedSatellites = new ArrayList<>(planet.getSatellites());
            for (Planet savedSatellite : savedSatellites) {
                if (planets.contains(savedSatellite)) {
                    planet.replaceSatellite(planets.get(planets.indexOf(savedSatellite)));
                }
            }
        }

        Planet startingPlanet = new Planet("YOU");
        Planet endingPlanet = new Planet("SAN");

        for (Planet planet : planets) {
            if (planet.getName().equals("YOU"))
                startingPlanet = planet;
            if (planet.getName().equals("SAN"))
                endingPlanet = planet;
        }

        System.out.println(planets.toString());

        return find(startingPlanet, endingPlanet, new ArrayList<>());
    }

    public static int find(Planet src, Planet dest, List<Planet> visited) {
        if (src.equals(dest)) return 0;

        int minTransfers = Integer.MAX_VALUE;

        for (Planet target : src.getTargets()) {
            if (!visited.contains(target)) {
                visited.add(target);
                int numTransfers = find(target, dest, new ArrayList<>(visited));
                if (numTransfers < minTransfers)
                    minTransfers = numTransfers;
            }
        }

        for (Planet satellite : src.getSatellites()) {
            if (!visited.contains(satellite)) {
                visited.add(satellite);
                int numTransfers = find(satellite, dest, new ArrayList<>(visited));
                if (numTransfers < minTransfers)
                    minTransfers = numTransfers;
            }
        }

        if (minTransfers == Integer.MAX_VALUE)
            return Integer.MAX_VALUE;
        return minTransfers + 1;
    }

    public static void main(String[] args) {
        String directory = System.getProperty("user.dir");
        List<String> input = FileUtil.loadFile(directory + "/Day6/src/input6.txt");
        System.out.println(orbitalTransfers(input) - 2);
    }
}
