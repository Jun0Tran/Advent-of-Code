package OpioidSalt;
import java.util.List;

public class Day3 {
    public static void main(String[] args) {
        List<String> input = FileUtil.loadFile(System.getProperty("user.dir") + "/src/OpioidSalt/input3.txt");
        int patternMax = input.get(0).length();
        int xCoord = 0;
        int inputSize = input.size();

        partOne(input, patternMax, xCoord, inputSize);
        partTwo(input, patternMax, xCoord, inputSize);
    }

    public static Integer treeHits(List<String> input, int patternMax, int xCoord, int inputSize, int rize,
            int run) {
        int treeHits = 0;
        for(int i = 0; i < inputSize; i += rize) {
            char currentPlace = input.get(i).charAt(xCoord);
            if(currentPlace == '#') {
                treeHits++;
            }
            xCoord = (xCoord + run) % patternMax;
        }
        return treeHits;
    }

    public static void partOne(List<String> input, int patternMax, int xCoord, int inputSize) {
        int totalTreeHits = treeHits(input, patternMax, xCoord, inputSize, 1, 3);
        System.out.println(totalTreeHits);
    }

    public static void partTwo(List<String> input, int patternMax, int xCoord, int inputSize) {
        int slopeOne = treeHits(input, patternMax, xCoord, inputSize, 1, 1);
        int slopeTwo = treeHits(input, patternMax, xCoord, inputSize, 1, 3);
        int slopeThree = treeHits(input, patternMax, xCoord, inputSize, 1, 5);
        int slopeFour = treeHits(input, patternMax, xCoord, inputSize, 1, 7);
        int slopeFive = treeHits(input, patternMax, xCoord, inputSize, 2, 1);
                
        Long totalTreeHits = Long.valueOf(slopeOne) * slopeTwo * slopeThree * slopeFour * slopeFive;

        System.out.println(totalTreeHits);
    }
}