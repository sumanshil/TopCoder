package com.hackerrank;

import static com.geeksforgeeks.backtracking.TugOfWar.arr;

/**
 * Created by sshil on 6/4/2016.
 */
public class CheckForSortedString {
    public static boolean ascending(String str) {
        for (int i = 0 ; i < str.length()-1 ; i++){
            if ( str.charAt(i+1) - str.charAt(i) != 1) {
                return false;
            }
        }
        return true;
    }

    public static boolean ascending(int[] arr) {
        if (arr[1]-arr[0] == 1 && arr[2]-arr[1] == 1){
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        String str = "1 2 3 5 4";//bufferedReader.readLine();
        String[] arr1 = str.split("\\s+");
        boolean found = false;
        if (str.length() == 3){
            arr[0] = Integer.parseInt(str.charAt(0) + "");
            arr[1] = Integer.parseInt(str.charAt(1) + "");
            arr[2] = Integer.parseInt(str.charAt(2) + "");
            for (int rotation = 0; rotation < 3; rotation++) {
                int temp = arr[0];
                arr[0] = arr[1];
                arr[1] = arr[2];
                arr[2] = temp;
                if (ascending(arr)) {
                    System.out.println("YES");
                    found = true;
                    break;
                }
            }
        } else {
            for (int j = 0; j < str.length() - 3; j++) {
                if (str.charAt(j + 1) - str.charAt(j) != 1) {

                    if (j + 4 < str.length()) {
                        String subString = str.substring(j + 4);
                        if (ascending(subString)) {
                            int[] arr = new int[3];
                            arr[0] = Integer.parseInt(str.charAt(j + 1) + "");
                            arr[1] = Integer.parseInt(str.charAt(j + 2) + "");
                            arr[2] = Integer.parseInt(str.charAt(j + 3) + "");
                            for (int rotation = 0; rotation < 3; rotation++) {
                                int temp = arr[0];
                                arr[0] = arr[1];
                                arr[1] = arr[2];
                                arr[2] = temp;
                                if (ascending(arr)) {
                                    System.out.println("YES");
                                    found = true;
                                    break;
                                }
                            }
                        }
                    } else {
                        int[] arr = new int[3];
                        arr[0] = Integer.parseInt(str.charAt(j + 1) + "");
                        arr[1] = Integer.parseInt(str.charAt(j + 2) + "");
                        arr[2] = Integer.parseInt(str.charAt(j + 3) + "");
                        for (int rotation = 0; rotation < 3; rotation++) {
                            int temp = arr[0];
                            arr[0] = arr[1];
                            arr[1] = arr[2];
                            arr[2] = temp;
                            if (ascending(arr)) {
                                System.out.println("YES");
                                found = true;
                                break;
                            }
                        }
                    }
                }
            }
        }
    }
}
