package Jas0nTran;

import java.util.ArrayList;
import java.util.List;

public class day8 {
    public static void main(String[] args) {
        List<String> input = FileUtil.loadFile(System.getProperty("user.dir") + "/src/Jas0nTran/input.txt");
        int accumulator = 0;
        int i = 0;
        partOne(input, accumulator, i);
        partTwo(input, accumulator);   
    }

    public static void partOne(List<String> input, int accumulator, int i) {
        accumulator = readInstructions(input, accumulator, i)[0];
        System.out.println(accumulator);
    }
    public static void partTwo(List<String> input, int accumulator) {
        for(int i = 0; i < input.size(); i++) {
            String instruction = input.get(i);
            String operation = instruction.substring(0,3);
            if(operation.equals("nop")) {
                instruction = instruction.replace("no", "jm");
                input.set(i, instruction);
                int[] output = readInstructions(input, accumulator, 0);
                if(output[1] == input.size()){
                    System.out.println(output[0]);
                    break;
                } else {
                    instruction = instruction.replace("jm", "no");
                    input.set(i, instruction);
                }
            } else if (operation.equals("jmp")) {
                instruction = instruction.replace("jm", "no");
                input.set(i, instruction);
                int[] output = readInstructions(input, accumulator, 0);
                if(output[1] == input.size()){
                    System.out.println(output[0]);
                    break;
                } else {
                    instruction = instruction.replace("no", "jm");
                    input.set(i, instruction);
                }
            }
        }
    }
    public static int[] readInstructions(List<String> input, int accumulator, int i) {
        List<Integer> completedInstructions = new ArrayList<>();
        int[] output = new int[2];
        while(i < input.size()) {
            if(!completedInstructions.contains(i)){
                completedInstructions.add(i);
            } else break;
            String instruction = input.get(i);
            String operation = instruction.substring(0,3);
            int argument = Integer.parseInt(instruction.substring(4, instruction.length()));
            switch(operation) {
                case "acc":
                    accumulator += argument;
                    i++;
                    break;
                case "jmp":
                    i += argument;
                    break;
                case "nop":
                    i++;
                    break;
            }
        }
        output[0] = accumulator;
        output[1] = i;
        return output;
    }
}