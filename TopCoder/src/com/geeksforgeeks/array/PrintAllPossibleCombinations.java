package com.geeksforgeeks.array;

import java.util.*;


public class PrintAllPossibleCombinations {

    private static Map<String, List<String>> map = new HashMap<>();

    static {
        List<String> list = new ArrayList<>();
        list.add("AA");
        list.add("BB");
        list.add("CC");

        map.put("1",list);
        list = new ArrayList<>();
        list.add("AAA");
        list.add("BBB");
        list.add("CCC");
        map.put("2", list);

        list = new ArrayList<>();
        map.put("3", list);

        list = new ArrayList<>();
        list.add("A");
        list.add("B");
        list.add("C");
        map.put("4", list);
    }

    // get the current Index.
    // get from the map last list index used for this index
    // if this is the first time
    //    initialize the map with current list index
    //    add the character to output
    //    increase current index by 1
    // else if we have reached the last list index
    //    if current index is not 0
    //        remove the last character from output
    //        decrease current index by 1
    //        reset to 0 for all the indexes on or above current index
    //    else
    //        break;
    // else (we are revisiting)
    //    remove last char from output
    //    increase list index for cuurent index and store it in map
    //    add
    int currentIndex = 0;
    int listIndex = 0;
    Map<Integer, Integer> listIndexMap = new HashMap<>();
    boolean shouldRemoveElementFromOutput = false;
    List<String> output = new LinkedList<>();

    public void find (String input) {
        for ( int i = 0 ; i < input.length() ; i++) {
            listIndexMap.put(i, 0);
        }

        while (true) {
            listIndex = listIndexMap.get(currentIndex);
            if (shouldRemoveElementFromOutput){
                output.remove(output.size()-1);
            }

            String key = input.charAt(currentIndex) + "";
            if (listIndex == map.get(key).size()){
                if (currentIndex == 0){
                    break;
                } else {
                    if (map.get(key).size() == 0) {
                        if (shouldRemoveElementFromOutput){
                       //     output.remove(output.size()-1);
                            currentIndex--;
                        } else {
                            output.add("");
                            currentIndex++;
                        }
                    } else {
                       // output.remove(output.size() - 1);
                        shouldRemoveElementFromOutput = true;
                        listIndexMap.put(currentIndex, 0);
                        currentIndex--;
                    }
                }
            } else {
                if (shouldRemoveElementFromOutput){
                    shouldRemoveElementFromOutput = false;
                }
                String k = map.get(key).get(listIndex);
                listIndexMap.put(currentIndex, listIndex+1);
                output.add(k);
                if (currentIndex == input.length()-1) {
                    printOutput();
                    shouldRemoveElementFromOutput = true;
                } else {
                    currentIndex++;
                }
            }
        }
    }

    private void printOutput() {
        output.stream().forEach(System.out::print);
        System.out.println();
    }


    public static void main(String[] args) {
        String str = "1234";
        new PrintAllPossibleCombinations().find(str);
    }
}
