package OpioidSalt;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Day4 {
    private static final String HEX_PATTERN = "^#([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})$";
    public static void main(String[] args) {
        List<String> input = FileUtil.loadFile(System.getProperty("user.dir") + "/src/OpioidSalt/input4.txt");
        int validPass = 0;
        HashMap<String,String> passportFields = new HashMap<>();
        for(int i = 0; i < input.size(); i++) {
            String currentLine = input.get(i);
            
            if(currentLine.isEmpty()) {
                boolean validFields = validFields(passportFields);
                if(((passportFields.size() == 7 && !passportFields.containsKey("cid")) || passportFields.size() == 8) && validFields) {
                    validPass++;
                    passportFields.clear();
                } else passportFields.clear();
            } else {
                String[] fields = currentLine.split(" ");

                for(int j = 0; j < fields.length ; j++) {
                    String[] fieldAndValue = fields[j].split(":");
                    passportFields.put(fieldAndValue[0],fieldAndValue[1]);
                }
            }
        }
        System.out.println(validPass);
    }

    public static boolean validFields(HashMap<String,String> passportFields){
        for(String key : passportFields.keySet()) {
            String value = passportFields.get(key);
            switch(key) {
                case "byr":
                    int intValue = Integer.parseInt(value);
                    if(!(value.length() == 4 && intValue >= 1920 && intValue <= 2002)) {
                        return false;
                    }
                    break;
                case "iyr":
                    intValue = Integer.parseInt(value);
                    if(!(value.length() == 4 && intValue >= 2010 && intValue <= 2020)) {
                        return false;
                    }
                    break;
                case "eyr":                    
                    intValue = Integer.parseInt(value);
                    if(!(value.length() == 4 && intValue >= 2020 && intValue <= 2030)) {
                        return false;
                    }
                    break;
                case "hgt":                   
                    char cmOrIn = value.charAt(value.length() - 2);
                    if(cmOrIn == 'c') {
                        value = value.replace("cm", "");
                        intValue = Integer.parseInt(value);
                        if(!(intValue >= 150 && intValue <= 193)){
                            return false;
                        }
                    } else{
                        value = value.replace("in", "");
                        intValue = Integer.parseInt(value);
                        if(!(intValue >= 59 && intValue <= 76)){
                            return false;
                        }
                    } 
                    break;
                case "hcl":                    
                    boolean isValid = validate(value);
                    if(!isValid) {
                        return false;
                    }
                    break;
                case "ecl":                   
                    List<String> validColors = Arrays.asList("amb", "blu" ,"brn", "gry" ,"grn" ,"hzl", "oth");
                    if(!validColors.contains(value)){
                        return false;
                    }
                    break;
                case "pid":                   
                    if(value.length() != 9){
                        return false;
                    }
                    break;
                case "cid":
                    break;
            }
        }
        return true;
    }
    public static boolean validate(final String hex) {

        Pattern pattern = Pattern.compile(HEX_PATTERN);

        Matcher matcher = pattern.matcher(hex);

        return matcher.matches();
    }
}
