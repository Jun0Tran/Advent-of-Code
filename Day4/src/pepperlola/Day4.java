package pepperlola;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Day4 {

    public static int part1(String pw) {
        try {
            int i = 0;
            while (true) {
                String hashed = hash(pw + i);
                if (validate(hashed, 5)) return i;
                i ++;
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static int part2(String pw) {
        try {
            int i = 0;
            while (true) {
                String hashed = hash(pw + i);
                if (validate(hashed, 6)) return i;
                i ++;
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static String hash(String pw) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(pw.getBytes());
        byte[] digest = md.digest();
        return toHexString(digest);
    }

    public static String toHexString(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (byte b : bytes) {
            result.append(byteToString(b));
        }
        return result.toString();
    }

    public static boolean validate(String hashed, int numZero) {
        return hashed.startsWith("0".repeat(numZero));
    }

    public static String byteToString(byte b) {
        char[] hexDigits = new char[2];
        hexDigits[0] = Character.forDigit((b >> 4) & 0xF, 16);
        hexDigits[1] = Character.forDigit((b & 0xF), 16);
        return new String(hexDigits);
    }

    public static void main(String[] args) {
        String pw = "bgvyzdsv";
        System.out.println(part1(pw));
        System.out.println(part2(pw));
    }
}
