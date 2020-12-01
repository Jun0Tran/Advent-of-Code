package OpioidSalt;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
//import java.lang.Math;
import java.util.Map;
//hi
public class day6 {
    public static void main(String[] args) {
        String directory = System.getProperty("user.dir");
        List<String> input = FileUtil.loadFile(directory + "/Day6/src/Jason/input6.txt");
        int totalOrbits = 0;
        int inputSize = input.size();
        LinkedHashMap<String, String> planetOrbitsSatellites = new LinkedHashMap<String, String>();
        Map<String, ArrayList<String>> planetOrbitsTargets = new HashMap<String, ArrayList<String>>();
        int minSteps = Integer.MAX_VALUE;
        System.out.println(inputSize);
        for (int i = 0; i < inputSize; i++) {
            String[] planets = input.get(i).split("[)]");
            String target = planets[0];
            String satellite = planets[1];
            planetOrbitsSatellites.put(satellite,target);
        }
        for (int i = 0; i < inputSize; i++) {
            String[] planets = input.get(i).split("[)]");
            String satellite = planets[0];
            String target = planets[1];
            if(planetOrbitsTargets.get(target) == null) {
                planetOrbitsTargets.put(target, new ArrayList<String>());
                planetOrbitsTargets.get(target).add(satellite);
            } else planetOrbitsTargets.get(target).add(satellite);
        }
        List<String> l = new ArrayList<String>(planetOrbitsSatellites.keySet());
        List<String> j = new ArrayList<String>(planetOrbitsTargets.keySet());
        for (int i = 0; i < inputSize; i++) {
            String satellite = l.get(i);
            while(planetOrbitsSatellites.containsKey(satellite)){
                satellite = planetOrbitsSatellites.get(satellite);
                totalOrbits++;
            }
        }
        while(true) {
            String YOU = "YOU";
            for (int i = 0; i < inputSize; i++) {
                String satellite = l.get(i);
                while(planetOrbitsSatellites.containsKey(satellite)){
                    satellite = planetOrbitsSatellites.get(satellite);
                    totalOrbits++;
                }
            }
            break;
        }
        //System.out.println(planets);
        System.out.println(totalOrbits);
    }
}
