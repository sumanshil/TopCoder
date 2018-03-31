package com.mathematical;

import java.util.LinkedList;

/**
 * Created by sshil on 6/1/2016.
 */
//http://www.geeksforgeeks.org/find-all-combinations-that-adds-upto-given-number-2/
public class FindAllCombinationsThatAddsUptoGivenNumber {

    public void find(int number) {
        recursiveUtil(number, new LinkedList<>());
    }

    private void recursiveUtil(int number, LinkedList<Integer> numbers) {
        if (number <= 0) {
            for (int i : numbers){
                System.out.print(i+" ");
            }
            System.out.println();
            return;
        }
        int startNumber = numbers.size() == 0 ? 1 : numbers.get(numbers.size()-1);
        for ( int i = startNumber  ; i <= number ; i++) {
                numbers.add(i);
                recursiveUtil(number - i, numbers);
                numbers.remove(numbers.size() - 1);
        }
    }


    public static void main(String[] args) {
        new FindAllCombinationsThatAddsUptoGivenNumber().find(5);
    }
}
