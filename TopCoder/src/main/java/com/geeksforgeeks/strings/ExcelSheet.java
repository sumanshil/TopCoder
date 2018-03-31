package com.geeksforgeeks.strings;


//https://www.careercup.com/question?id=6139456847347712
public class ExcelSheet {

    private void printArray(int[] arr) {
        for (int i = 0 ; i < arr.length ; i++) {
            System.out.print((char)arr[i]);
        }
        System.out.println();
    }
    public void count (int n) {
        int index = 0;
        int[] arr = new int[1];
        arr[0] = 65;
        while (index++ < n) {
            printArray(arr);
            int currentIndex = arr.length - 1;
            while (currentIndex >= 0){
                if (arr[currentIndex] == 90){
                    currentIndex--;
                } else {
                   arr[currentIndex] = arr[currentIndex] + 1;
                   for ( int i = currentIndex + 1; i < arr.length ; i++){
                       arr[i] = 65;
                   }
                   break;
                }
            }
            if (currentIndex < 0){
                arr = new int[arr.length + 1];
                for ( int i = 0 ; i < arr.length ; i++){
                    arr[i] = 65;
                }
            }
        }
    }
    public static void main(String[] args) {
        new ExcelSheet().count(5500);
    }
}
