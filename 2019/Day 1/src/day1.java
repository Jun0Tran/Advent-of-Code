import java.util.List;
import java.lang.Math;

public class day1 {
    public static void main(String[] args) {
        List<String> input = FileUtil.loadFile("C:/Users/Owner/.vscode/Projects/AOC 2019/Day 1/Main/src/input1.txt");
        int totalFuel = 0;
        int inputSize = input.size();
        System.out.println(inputSize);
        for(int i = 0; i < inputSize; i++) {
            int mass = Integer.parseInt(input.get(i));
            int fuel = (int) (Math.floor(mass/3)-2);
            totalFuel = totalFuel + fuel;
            while(fuel > 8){
                fuel = (int) (Math.floor(fuel/3)-2);
                totalFuel = totalFuel + fuel;
            }
        }
        System.out.println(totalFuel);
        //input Size
        //for loop with the input size
            //convert Strings into Integers
            //divide each of the integers by 3 rounding down (math.floor) and then subtract 2
            //add the fuel to a total fuel count
        //system.out.println(total fuel count)
        
    }
}
