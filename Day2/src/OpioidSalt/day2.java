package OpioidSalt;
import java.util.List;
import java.util.ArrayList;

public class day2 {
    public static void main(String[] args) {
        List<String> input = FileUtil.loadFile(System.getProperty("user.dir") + "/src/OpioidSalt/input2.txt");
        int inputSize = input.size();
        List<String> policyAndPasswords = new ArrayList<String>();
        int possiblePasswords = 0;

        for(int i = 0; i < inputSize; i++) {

            String unSplitInput = input.get(i);
            splitComponents(unSplitInput, policyAndPasswords);
        }

        partOne(policyAndPasswords, possiblePasswords);
        partTwo(policyAndPasswords, possiblePasswords);
    }

    public static void partOne(List<String> policyAndPasswords, int possiblePasswords) {
        for(int i = 0; i < policyAndPasswords.size(); i += 3) {

            String[] unSplitRange = policyAndPasswords.get(i).split("-");

            int lowerBound = (Integer.parseInt(unSplitRange[0]));
            int upperBound = (Integer.parseInt(unSplitRange[1]));
            char passLetter = policyAndPasswords.get(i + 1).charAt(0);
            String password = policyAndPasswords.get(i + 2);
            int letterCount = 0;
            for( int j = 0; j < password.length( ); j++ ) {
                char temp = password.charAt( j );

                if( temp == passLetter )
                    letterCount++;
            }
            if(letterCount >= lowerBound && letterCount <= upperBound) {
                possiblePasswords++;
            }
        }
        
        System.out.println(possiblePasswords);
    }

    public static void partTwo(List<String> policyAndPasswords, int possiblePasswords) {
        for(int i = 0; i < policyAndPasswords.size(); i += 3) {

            String[] unSplitRange = policyAndPasswords.get(i).split("-");

            int positionOne = (Integer.parseInt(unSplitRange[0])) - 1;
            int positionTwo = (Integer.parseInt(unSplitRange[1])) - 1;
            char passLetter = policyAndPasswords.get(i + 1).charAt(0);
            String password = policyAndPasswords.get(i + 2);

            if((password.charAt(positionOne) == passLetter && password.charAt(positionTwo) != passLetter) || 
                 (password.charAt(positionOne) != passLetter && password.charAt(positionTwo) == passLetter)) {
                possiblePasswords++;
            }
        }
        
        System.out.println(possiblePasswords);
    }

    public static void splitComponents(String input,List<String> policyAndPasswords) {
        String inputNew = input.replace(":", "");
        String[] components = inputNew.split(" ");
        for(int i = 0; i < 3; i++) {
            policyAndPasswords.add(components[i]);
        }
    }
}