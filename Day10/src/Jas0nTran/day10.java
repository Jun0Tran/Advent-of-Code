package Jas0nTran;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class day10 {
    public static void main(String[] args){
        List<String> stringInput = FileUtil.loadFile(System.getProperty("user.dir") + "/src/Jas0nTran/input.txt");
        List<Integer> input = new ArrayList<>();

        for(String s: stringInput){
            int e = Integer.parseInt(s);
            input.add(e);
        }

        partOne(input);
        partTwo(input);
    }

    private static void partOne(List<Integer> input) {
        int oneJoltDiff = 0;
        int threeJoltDiff = 1;
        int i = 0;
        while(i < Collections.max(input)){
            for(int j = i + 1; j < i + 4; j++){
                if(input.contains(j)){
                    if(j == i + 1){
                        oneJoltDiff++;
                    } else if(j == i + 3){
                        threeJoltDiff++;
                    }
                    i = j;
                    break;
                }
            }
        }
        System.out.println(oneJoltDiff * threeJoltDiff);
    }
    private static void partTwo(List<Integer> input){
        input.add(0);
        Collections.sort(input);
        input.add(input.get(input.size() - 1) + 3);
        System.out.println(input);
        long adapterArrangements = 1;
        int i = 2;
        int j = 0;
        while(i < input.size() + 1) {
            List<Integer> adapterBlock = input.subList(j, i);
            if(hasJumped(adapterBlock)){
                int combinations = (int) Math.pow(2, (i - 3 - j));
                if(combinations == 0) {
                    combinations = 1;
                } 
                if (combinations >= 8) {
                    combinations--;
                }
                adapterArrangements *= combinations;
                j = i - 1;
                i++;
            } else i++;
        }
        System.out.println(adapterArrangements);
    }

    private static boolean hasJumped(List<Integer> adapterBlock) {
        if(adapterBlock.get(adapterBlock.size() - 1) == 
            adapterBlock.get(adapterBlock.size() - 2) + 3){
            return true;
        } else return false;
    }

}
