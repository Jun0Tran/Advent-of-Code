package Jas0nTran;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class day7 {
    public static void main(String[] args) {
        List<String> input = FileUtil.loadFile(System.getProperty("user.dir") + "/src/Jas0nTran/input.txt");
        HashMap<String, List<String>> bagList = new HashMap<String, List<String>>();
        for(String bagLine: input) {
            bagLine = bagLine.replace(" ","-");
            String[] bagSplit = bagLine.split("-contain-");
            String bagParent = bagSplit[0];
            List<String> bagChild = Arrays.asList(bagSplit[1].split(",-"));
            bagChild.set(bagChild.size() - 1, bagChild.get(bagChild.size() - 1).replace(".", ""));
            bagList.put(bagParent, bagChild);           
        }
        partOne(bagList);
        partTwo(bagList);
    }

    public static void partOne(HashMap<String, List<String>> bagList) {
        List<String> hasGBC = new ArrayList<>();
        List<String> alreadyChecked = new ArrayList<>();
        int SGBContainers = 0;
        for(Map.Entry<String,List<String>> me: bagList.entrySet()) {
            List<String> noNumberChild = removeNumbers(me);
            if(noNumberChild.contains("shiny-gold-bag")){
                SGBContainers++;
                hasGBC.add(me.getKey());
            }
        }
        while(hasGBC.isEmpty() == false) {
            List<String> currentGBList = createCopy(hasGBC);
            alreadyChecked.addAll(currentGBList);
            hasGBC.clear();
            for(Map.Entry<String,List<String>> me: bagList.entrySet()) {
                List<String> noNumberChild = removeNumbers(me);
                for(String bagWithGoldChild: currentGBList){
                    bagWithGoldChild = bagWithGoldChild.substring(0, bagWithGoldChild.length() - 1);
                    if(noNumberChild.contains(bagWithGoldChild) && !alreadyChecked.contains(me.getKey()) 
                        && !hasGBC.contains(me.getKey())) {
                        SGBContainers++;
                        hasGBC.add(me.getKey());
                    }
                }
            }
        }
        System.out.println(SGBContainers);
    }

    public static void partTwo(HashMap<String, List<String>> bagList) {
        int insideSGB = partTwoCount(bagList, 0, "shiny-gold-bags");
        System.out.println(insideSGB);
    }

    public static int partTwoCount(HashMap<String, List<String>> bagList, int cnt, String colorBag) {
        //turns colorBag into readable key
        if(colorBag.charAt(1) == '-'){
            colorBag = colorBag.substring(2);
        }
        if(colorBag.charAt(colorBag.length() - 1) != 's') {
            colorBag = colorBag + 's';
        }
        List<String> inBag = bagList.get(colorBag);
        //
        if(inBag.contains("no-other-bags")) {
            return 1;
        } else {
            for(String bag : inBag) {
                cnt += Character.getNumericValue(bag.charAt(0)) * partTwoCount(bagList, 1, bag);
            }
        }
        return cnt;
    }

    private static List<String> removeNumbers(Map.Entry<String, List<String>> me) {
        List<String> bagChild = me.getValue();
        List<String> noNumberChild = new ArrayList<>();
        for(String child: bagChild){
            if(child.charAt(1) == '-'){
                child = child.substring(2);
            }
            if(child.charAt(child.length() - 1) == 's') {
                child = child.substring(0, child.length() - 1);
            }
            noNumberChild.add(child);
        }
        return noNumberChild;
    }
    public static ArrayList<String> createCopy (List<String> orginal) {
        ArrayList<String> copy = new ArrayList<String>();
        for (String s:orginal) {
            copy.add(s);
        }
        return copy;
    }
}
