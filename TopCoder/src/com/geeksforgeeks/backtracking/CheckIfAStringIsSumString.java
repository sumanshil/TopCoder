package com.geeksforgeeks.backtracking;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


//http://www.geeksforgeeks.org/check-given-string-sum-string/
public class CheckIfAStringIsSumString {

    public void find (String str){
        List<String> list = new ArrayList<>();
        recursive(str, list, 0);
    }

    private boolean found = false;
    private void recursive(String str, List<String> list, int index) {
        if (found){
            return;
        }
        if (index >= str.length()) {
            if (list.size() > 2) {
                String collectedString = list.stream().collect(Collectors.joining());
                if (collectedString.equals(str)) {
                    found = true;
                    System.out.println("Found");
                }
            }
            return;
        }

        for ( int i = index ; i < str.length() ; i++) {
            boolean found = false;
            for (int length = 1 ; i+length <= str.length() ; length++) {
                String strPart = str.substring(i, (i + length));
                if (list.size() > 0) {
                    String lastSubString = list.get(list.size()-1);
                    int part1 = Integer.parseInt(strPart);
                    int part2 = Integer.parseInt(lastSubString);
                    int part3 = part1 + part2;
                    String strRepresent = Integer.toString(part3);
                    int newLength = strRepresent.length();
                    if ( (i+ length)+newLength <= str.length() ) {
                        String stringCheck = str.substring(i + length, ((i + length) + newLength));
                        if (strRepresent.equals(stringCheck)) {
                            found = true;
                            boolean isFinished = (i+length)+newLength == str.length();
                            if (!isFinished) {
                                list.add(strPart);
                                recursive(str, list, i + length);
                                list.remove(list.size() - 1);
                            } else {
                                list.add(strPart);
                                list.add(stringCheck);
                                recursive(str, list, i + length);
                                list.remove(list.size() - 1);
                                list.remove(list.size()-1);
                            }
                        }
                    }
                } else {
                    list.add(strPart);
                    recursive(str, list, i + length );
                    list.remove(list.size()-1);
                }
            }
            if (!found){
                return;
            }
        }
    }

    public static void main(String[] args) {
        String str = "2368";
        new CheckIfAStringIsSumString().find(str);
    }
}
