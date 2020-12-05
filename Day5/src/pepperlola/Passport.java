package pepperlola;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Pattern;

public class Passport {

    private Map<String, String> values = new HashMap<>();

    public Passport() {
        values.put("BYR", "");
        values.put("IYR", "");
        values.put("EYR", "");
        values.put("HGT", "");
        values.put("HCL", "");
        values.put("ECL", "");
        values.put("PID", "");
        values.put("CID", "");
    }

    public boolean isValid(boolean cidOptional, boolean validateFields) {
        for (String key : values.keySet()) {
            if (values.get(key).equals("")) {
                if (key.equals("CID") && !(cidOptional || !values.get(key).equals(""))) return false;
                if (!key.equals("CID") && values.get(key).equals("")) return false;
            }
            if (validateFields && !validateField(key, values.get(key))) return false;
        }
        return true;
    }

    public void set(String key, String value) {
        values.put(key.toUpperCase(), value);
    }

    private boolean validateField(String field, String value) {
        System.out.println(field + " | " + value);
        switch (field.toUpperCase()) {
            case "BYR":
                int byr = Integer.parseInt(value);
                return 1920 <= byr && byr <= 2002;
            case "IYR":
                int iyr = Integer.parseInt(value);
                return 2010 <= iyr && iyr <= 2020;
            case "EYR":
                int eyr = Integer.parseInt(value);
                return 2020 <= eyr && eyr <= 2030;
            case "HGT":
                boolean centimeters = value.endsWith("cm");
                int hgt = Integer.parseInt(value.replaceAll("(in|cm)", ""));
                return centimeters ? 150 <= hgt && hgt <= 193 : 59 <= hgt && hgt <= 76;
            case "HCL":
                return Pattern.compile("#[0-9a-fA-F]{6}").matcher(value).matches();
            case "ECL":
                return Pattern.compile("(amb|blu|brn|gry|grn|hzl|oth)").matcher(value).matches();
            case "PID":
                return Pattern.compile("[0-9]{9}").matcher(value).matches();
            case "CID":
                return true;
        }

        return false;
    }
}
