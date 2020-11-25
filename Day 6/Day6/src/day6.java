//import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
//import java.lang.Math;

public class day6 {
    public static void main(String[] args) {
        String directory = System.getProperty("user.dir");
        List<String> input = FileUtil.loadFile(directory + "/Day6/src/input6.txt");
        int totalOrbits = 0;
        int inputSize = input.size();
        HashMap<String, Integer> planets = new HashMap<String, Integer>();
        System.out.println(inputSize);
        for (int i = 0; i < inputSize; i++) {
            String[] orbitPlanets = input.get(i).split("[)]");
            if(planets.containsKey(orbitPlanets[0])) {                          //checks if the orbited planet is already orbiting another planet
                planets.put(orbitPlanets[1], planets.get(orbitPlanets[0]) + 1); //puts new planet in with the # of indirect orbits + 1 (the direct orbit)
                totalOrbits += planets.get(orbitPlanets[1]);                    //adds to total orbits
            } else {
                planets.put(orbitPlanets[1], 2);                                //puts new planet with one orbit
                totalOrbits += planets.get(orbitPlanets[1]);                    //adds to total orbits
            }
        }
        //System.out.println(planets);
        System.out.println(totalOrbits);
    }
}
