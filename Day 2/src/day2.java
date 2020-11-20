import java.util.ArrayList;
//import java.lang.Math;

public class day2 {

    public static int evaluate(ArrayList<Integer> numbers) {
        boolean stop = false;
        for (int i = 0; i < numbers.size(); i += 4) {
            int opcode = numbers.get(i);
            int input1 = numbers.get(numbers.get(i + 1));
            int input2 = numbers.get(numbers.get(i + 2));
            int location = numbers.get(i + 3);

            switch (opcode) {
                case 1: // add noun + verb, store at position location
                    numbers.set(location, input1 + input2);
                    break;
                case 2: // multiply noun * verb, store at position location
                    numbers.set(location, input1 * input2);
                    break;
                case 99:
                    stop = true;
                    break;
            }

            if (stop) {
                // 99 is usually the last opcode, but just in case
                return numbers.get(0);
            }
        }
        return -1;
    }
    public static void main(String[] args) { 
        String input =  FileUtil.loadFile("C:/Users/Owner/.vscode/Projects/Advent-of-Code-2019/Day 2/src/input2.txt").get(0);
        String[] numbersString = input.split(",");
        int numberSize = numbersString.length;
        
        //numbers.set(1, 12);
        //numbers.set(2, 2);
        /*while (numbers.get(0) != 19690720) {
            noun++;
            if (noun == 100) {
                noun = 0;
                verb++;
            }
        }
        */
        int target = 19690720;
        for (int noun = 0; noun < 100; noun++) { // so manly!!
            for (int verb = 0; verb < 100; verb++) { // brute forcing!!!
                ArrayList<Integer> numbers = new ArrayList<Integer>();
                for (int i = 0; i < numberSize; i++) {
                    numbers.add(Integer.parseInt(numbersString[i]));
                }
                numbers.set(1, noun);
                numbers.set(2, verb);
                int result = evaluate(numbers);
                if (result == target){
                    System.out.println(String.format("RESULT: %d | NOUN: %d | VERB: %d", 100 * noun + verb, noun, verb));
                    return;
                }
            }
        }
        // position one as 12
        // position two as 2
        //
        
    }
}
