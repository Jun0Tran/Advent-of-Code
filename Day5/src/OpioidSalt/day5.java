package OpioidSalt;

import java.util.ArrayList;
import java.util.List;

public class day5 {
    public static void main(String[] args) {
        List<String> input = FileUtil.loadFile(System.getProperty("user.dir") + "/src/OpioidSalt/input5.txt");
        partOne(input);
        partTwo(input);
    }

    public static void partOne(List<String> input) {
        int highSeatID = 0;
        highSeatID = getHighSeatID(input, highSeatID);
        System.out.println(highSeatID);
    }
    public static void partTwo(List<String> input) {
        List<Integer> temp = new ArrayList<>();
        List<Integer> seatIDs = getSeatIDs(input, temp);
        int mySeatID = 0;
        int i = 0;
        while(true) {
            if(seatIDs.contains(i + 1) && seatIDs.contains(i - 1) && !seatIDs.contains(i)){
                mySeatID = i;
                break;
            }
            i++;
        }
        System.out.println(mySeatID);
    }

    private static int getHighSeatID(List<String> input, int highSeatID) {
        for(String boardPass: input) {
            List<Integer> row = new ArrayList<>();
            List<Integer> column = new ArrayList<>();
            for(int i = 0; i < 128; i++){
                row.add(i);
                if(i % 16 == 0){
                    column.add(i/16);
                }
            }
            for(int i = 0; i < boardPass.length(); i++) {
                if(i < 7){
                    int center = row.size() / 2;
                    int rowSize = row.size();
                    if(boardPass.charAt(i) == 'F') {
                        for(int j = center; j < rowSize; j++) {
                            row.remove(center);
                        }
                    } else {
                        for(int j = 0; j < center; j++) {
                            row.remove(0);
                        }
                    }
                } else {
                    int center = column.size() / 2;
                    int columnSize = column.size();
                    if(boardPass.charAt(i) == 'L') {
                        for(int j = center; j < columnSize; j++) {
                            column.remove(center);
                        }
                    } else {
                        for(int j = 0; j < center; j++) {
                            column.remove(0);
                        }
                    }
                }
            }
            int seatID = (row.get(0) * 8) + column.get(0);
            if(seatID > highSeatID) {
                highSeatID = seatID;
            }
        }
        return highSeatID;
    }
    private static List<Integer> getSeatIDs(List<String> input, List<Integer> seatIDs) {
        for(String boardPass: input) {
            List<Integer> row = new ArrayList<>();
            List<Integer> column = new ArrayList<>();
            for(int i = 0; i < 128; i++){
                row.add(i);
                if(i % 16 == 0){
                    column.add(i/16);
                }
            }
            for(int i = 0; i < boardPass.length(); i++) {
                if(i < 7){
                    int center = row.size() / 2;
                    int rowSize = row.size();
                    if(boardPass.charAt(i) == 'F') {
                        for(int j = center; j < rowSize; j++) {
                            row.remove(center);
                        }
                    } else {
                        for(int j = 0; j < center; j++) {
                            row.remove(0);
                        }
                    }
                } else {
                    int center = column.size() / 2;
                    int columnSize = column.size();
                    if(boardPass.charAt(i) == 'L') {
                        for(int j = center; j < columnSize; j++) {
                            column.remove(center);
                        }
                    } else {
                        for(int j = 0; j < center; j++) {
                            column.remove(0);
                        }
                    }
                }
            }
            int seatID = (row.get(0) * 8) + column.get(0);
            seatIDs.add(seatID);
        }
        return seatIDs;
    }
}
