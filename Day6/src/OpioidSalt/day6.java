package OpioidSalt;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class day6 {
    public static void main (String[] args) {
        List<String> input = FileUtil.loadFile(System.getProperty("user.dir") + "/src/OpioidSalt/input.txt");
        int sumCounts = 0;
        partOne(input, sumCounts);
        partTwo(input, sumCounts);
        //for loop reading the lines
        //stores all the unique letters
        //when the line is blank, add the # of unique letters to the total
    }

    private static void partOne(List<String> input, int sumCounts) {
        List<Character> uniqueLetters = new ArrayList<>();
        for (int i = 0; i < input.size(); i++) {
            String line = input.get(i);
            if(!line.isBlank()) {
                for(int j = 0; j < line.length(); j++) {
                    char letter = line.charAt(j);
                    if(!uniqueLetters.contains(letter)){
                        uniqueLetters.add(letter);
                    }
                }
                if(i == input.size() - 1) {
                    sumCounts += uniqueLetters.size();
                    uniqueLetters.clear();
                }
            } else {
                sumCounts += uniqueLetters.size();
                uniqueLetters.clear();
            }
        }
        System.out.println(sumCounts);
    }
    private static void partTwo(List<String> input, int sumCounts) {
        HashMap<Character, Boolean> uniqueLetters = new HashMap<>();
        List<Character> lineCompare = new ArrayList<>();
        for (int i = 0; i < input.size(); i++) {
            String line = input.get(i);
            if(!line.isBlank()) {
                if(i == 0) {
                    for(int j = 0; j< line.length(); j++){
                        char letter = line.charAt(j);
                        uniqueLetters.put(letter, false);
                    }
                } else {
                    boolean firstPerson = input.get(i - 1).isBlank();
                    for(int j = 0; j < line.length(); j++) {
                        char letter = line.charAt(j);
                        if(!firstPerson){
                            if(!lineCompare.contains(letter)){
                                lineCompare.add(letter);
                            }
                        } else {
                            if(!uniqueLetters.containsKey(letter)){
                                uniqueLetters.put(letter, false);
                            }
                        }
                    }
                    if(!firstPerson){
                        for(char letter : uniqueLetters.keySet()) {
                            if(lineCompare.contains(letter)){
                                uniqueLetters.replace(letter, true);
                            }
                        }
                        uniqueLetters.entrySet().removeIf(e -> e.getValue() == (false));
                        uniqueLetters.replaceAll((key, value) -> false);
                        lineCompare.clear();
                    }
                    if(i == input.size() - 1) {
                        sumCounts += uniqueLetters.size();
                        uniqueLetters.clear();
                    }
                }
            } else {
                sumCounts += uniqueLetters.size();
                uniqueLetters.clear();
            }
        }
        System.out.println(sumCounts);
    }
}