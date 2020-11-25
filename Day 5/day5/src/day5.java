import java.util.ArrayList;
//import java.lang.Math;

public class day5 {
    public static void main(String[] args) {
        String diagProg = FileUtil.loadFile("C:/Users/Owner/.vscode/Projects/AOC 2019/Day 5/day5/src/input5.txt").get(0); //gets input2.txt as a string
        String[] stringInput = diagProg.split(",");
        int inputSize = stringInput.length;
        System.out.println(stringInput);
        System.out.println(inputSize);

        ArrayList<Integer> intCode = new ArrayList<Integer>();
        
        for (int i = 0; i < inputSize; i++) {
            intCode.add(Integer.parseInt(stringInput[i]));  
        }

        boolean stop = false;

        int input = 5;
        int i = 0;
        System.out.println(intCode.get(225));

        while(i < inputSize) {
            int opAndParam = intCode.get(i);
            int param1;
            int param2;
            int param3 = intCode.get(i + 3);
            int opCode;

            int[] mode = new int [2];
            int cParam;

            if (opAndParam > 99) {
                opCode = opAndParam % 100;
                cParam = opAndParam / 100;
                for (int j = 0; j < 2; j++) {
                    mode[j] = cParam % 10;
                    cParam = cParam / 10;
                }
            } else {
                opCode = opAndParam;
            } 

            switch(opCode){
                case 1:
                    param1 = posOrImmediate(intCode, i, 1, mode);
                    param2 = posOrImmediate(intCode, i, 2, mode);

                    intCode.set(param3, param1 + param2);
                    
                    i += 4;
                    break;
                case 2:
                    param1 = posOrImmediate(intCode, i, 1, mode);
                    param2 = posOrImmediate(intCode, i, 2, mode);
                    
                    intCode.set(param3, param1 * param2);
                    
                    i += 4;
                    break;
                case 3:
                    param1 = intCode.get(i + 1);
                    intCode.set(param1, input);
                    
                    i += 2;
                    break;
                case 4:
                    param1 = posOrImmediate(intCode, i, 1, mode);
                    System.out.println(param1);
                    
                    i += 2;
                    break;
                case 5:
                    param1 = posOrImmediate(intCode, i, 1, mode);
                    param2 = posOrImmediate(intCode, i, 2, mode);
                    
                    if (param1 != 0) {
                        i = param2;
                    } else i += 3;
                    
                    break;
                case 6:
                    param1 = posOrImmediate(intCode, i, 1, mode);
                    param2 = posOrImmediate(intCode, i, 2, mode);
                    
                    if (param1 == 0) {
                        i = param2;
                    } else i += 3;
                    
                    break;
                case 7:
                    param1 = posOrImmediate(intCode, i, 1, mode);
                    param2 = posOrImmediate(intCode, i, 2, mode);
                    
                    if (param1 < param2) {
                        intCode.set(param3, 1);
                    } else intCode.set(param3, 0);
                    
                    i += 4;
                    break;
                case 8:
                    param1 = posOrImmediate(intCode, i, 1, mode);
                    param2 = posOrImmediate(intCode, i, 2, mode);

                    if (param1 == param2) {
                        intCode.set(param3, 1);
                    } else intCode.set(param3, 0);
                    
                    i += 4;
                    break;
                case 99:
                    stop = true;
                    break;
                default:
                    stop = true;
                    System.out.println(intCode);
                    System.out.println(i);
                    break;
            }
            if(stop){
                break;
            }
        }
    }

    public static int posOrImmediate(ArrayList<Integer> intCode, int i, int craig, int[] param) {
        int input;
        if(param[craig - 1] == 0) {
            input = intCode.get(intCode.get(i + craig));
        } else input = intCode.get(i + craig);
        return input;
    }
}
