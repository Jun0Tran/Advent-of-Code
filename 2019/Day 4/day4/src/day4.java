import java.util.List;

import java.util.ArrayList;
//import java.lang.Math;

public class day4 {
    public static void main(String[] args) {
        String input = FileUtil.loadFile("C:/Users/Owner/.vscode/Projects/AOC 2019/Day 4/day4/src/input4.txt").get(0); //gets input2.txt as a string
        System.out.println(input);
        String[] range = input.split("-");
        int lower = Integer.parseInt(range[0]);
        int upper = Integer.parseInt(range[1]);
        int possiblePasswords = 0;
        for (int i = lower; i < upper; i++) {
            int password = i;
            List<Integer> digits = digits(password);
            //System.out.println(digits);
            if(hasPair(digits) && isNotDecreasing(digits)){
                possiblePasswords++;
            }
        }
        System.out.println(possiblePasswords);   
    }
    public static List<Integer> digits(int i) {
        List<Integer> digits = new ArrayList<Integer>();
        while(i > 0) {
            digits.add(0, i % 10);
            i /= 10;
        }
        return digits;
    }
    public static boolean hasPair(List<Integer> digits) {
        for(int i = 0; i < digits.size() - 1; i++) {
            int digit = digits.get(i);
            switch(i) {
                case 0:
                    int nextDigit = digits.get(i + 1);
                    int nextDigit2 = digits.get(i + 2);
                    if(digit == nextDigit && digit != nextDigit2) {
                        return true;
                    }
                    break;
                case 4:
                    int prevDigit = digits.get(i - 1);
                    nextDigit = digits.get(i + 1);
                    if(digit == nextDigit && digit != prevDigit){
                        return true;
                    } 
                    break;
                default:
                    prevDigit = digits.get(i - 1);
                    nextDigit = digits.get(i + 1);
                    nextDigit2 = digits.get(i + 2);
                    if(digit == nextDigit && digit != nextDigit2 && digit != prevDigit){
                        return true;
                    }
                    break;
                
            }
        }
        return false;
    }
    public static boolean isNotDecreasing(List<Integer> digits) {
        for(int i = 0; i < digits.size() - 1; i++) {
            int digit = digits.get(i);
            int nextDigit = digits.get(i + 1);
            if(digit > nextDigit){
                return false;
            }
        }
        return true;
    }
}
