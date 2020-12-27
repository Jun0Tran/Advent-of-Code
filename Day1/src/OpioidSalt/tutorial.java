package OpioidSalt;
import java.util.List;

public class tutorial {
    public static void main(String[] args) {
        List<String> input = FileUtil.loadFile(System.getProperty("user.dir") + "/src/OpioidSalt/input1.txt");
        int inputSize = input.size();
        for(int i = 0; i < inputSize; i++) {
            int number1 = Integer.parseInt(input.get(i));
            for(int j = i + 1; j < inputSize; j++ ){
                int number2 = Integer.parseInt(input.get(j));
                for(int k = j + 1; k < inputSize; k++){
                    int number3 = Integer.parseInt(input.get(k));
                    if(number1 + number2 + number3 == 2020){
                        System.out.println(number1*number2*number3);
                        return;
                    }
                }
            }
        }
        //for loop 
            //read the one of the inputs
            //for loop
                //inputs that's going to be compared by the upper input
                //if they equal to 2020
                    //print out those inputs multiplied
                    //break;

            //break;
    }
}
