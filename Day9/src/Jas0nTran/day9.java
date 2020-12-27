package Jas0nTran;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class day9 {
    public static void main(String[] args) {

        List<String> stringInput = FileUtil.loadFile(System.getProperty("user.dir") + "/src/Jas0nTran/input.txt");
        List<Long> input = new ArrayList<>();

        for(String stringLine: stringInput){
            long line = Long.parseLong(stringLine);
            input.add(line);
        }

        int noSum = partOne(input);
        partTwo(noSum,input);
    }

    private static void partTwo(int noSum, List<Long> input) {
        for(int i = 0; i < input.size(); i++){
            for(int j = i + 1; j < input.size(); j++){

                List<Long> contSet = input.subList(i, j);
                long sumOfSet = 0;
                
                for(long number: contSet){
                    sumOfSet += number;
                }
                
                if(sumOfSet == noSum){
                    
                    int minIndex = contSet.indexOf(Collections.min(contSet));
                    int maxIndex = contSet.indexOf(Collections.max(contSet));
                    System.out.println(contSet.get(minIndex) + contSet.get(maxIndex));
                    return;
                
                } else if (sumOfSet > noSum){
                    break; //this 
                }
                
            }
        }
    }

    private static int partOne(List<Long> input) {
        for(int i = 25;i < input.size(); i++){
            
            long sum = input.get(i);
            boolean isSummable = isSummable(input, sum, i);
            
            if(!isSummable){

                System.out.println(sum);
                return (int) sum;
            }
        }
        return 0;
    }

    private static boolean isSummable(List<Long> input, long sum, int i) {

        int lowerPreamble = i - 25;
        int upperPreamble = i - 1;

        for(int j = lowerPreamble; j <= upperPreamble; j++) {
            for(int k = j + 1;k <= upperPreamble; k++) {

                long termOne = input.get(j);
                long termTwo = input.get(k);

                if(termOne + termTwo == sum){
                    return true;
                }

            }
        }
        return false;
    }
}
