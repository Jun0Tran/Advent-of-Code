package OpioidSalt;
import java.util.List;

public class Day1 {
    public static void main(String[] args){
        List<String> input = FileUtil.loadFile(System.getProperty("user.dir") + "/src/OpioidSalt/input1.txt");
        int inputSize = input.size();
        boolean stop = false;
        for(int i = 0; i < inputSize; i++){
            for(int j = 0; j < inputSize; j++){
                for(int l = 0; l <inputSize; l++) {
                    if(i != j && j != l) {
                        int entry1 = Integer.parseInt(input.get(i));
                        int entry2 = Integer.parseInt(input.get(j));
                        int entry3 = Integer.parseInt(input.get(l));
                        if((entry1 + entry2 + entry3) == 2020) {
                            System.out.println(entry1 * entry2 * entry3);
                            stop = true;
                        }
                    }
                    if(stop) {
                        break;
                    }
                }
            }
        }
        System.out.println(input);
    }
}